/*
 * Program: LoginController.java
 * Author: Davis Nguyen
 * Description: LoginController class used to control LoginView.fxml
 */

package Controller;

import Model.Login;
import Resources.ChangeScene;
import Resources.Formatter;
import Resources.LoginActivity;
import Utilities.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class controls LoginView FXML.
 *
 * @author Davis Nguyen
 */
public class LoginController implements Initializable {
    //references to view
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private Button loginBTN;
    @FXML
    private Label headerLBL, usernameLBL, passwordLBL, zoneIdLBL, userLocationLBL;

    //setting region and language
    private final ZoneId zoneId = ZoneId.systemDefault();
    private Locale locale = Locale.getDefault();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("Resources/Nat", locale);

    //database connection statements
    private Connection conn = DBConnection.startConnection();
    private Statement statement;
    ResultSet resultSet = null;

    ZonedDateTime currentDateTime;
    int appointmentID;
    LocalDateTime dateTime;

    /**
     * Calls ChangeScene to load and display MainView fxml.
     *
     * @param actionEvent The action event.
     * @throws IOException If an input or output exception occurred.
     * @throws SQLException If a SQL exception occurred.
     */
    public void loginBTNOA(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {
        //grab user input for username and password
        String username = usernameTF.getText();
        String password = passwordTF.getText();

        //check if username or password is empty
        if (username.equals("") || password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("errorTitle"));
            alert.setHeaderText(resourceBundle.getString("errorHeaderBlank"));
            alert.setContentText(resourceBundle.getString("errorContentBlank"));
            alert.showAndWait();
        }
        //check if username and password are valid, if invalid, throw error and erase passwordTF
        else if (validLogin(username, password) == false) {
            LoginActivity.track(username, "Fail");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("errorTitle"));
            alert.setHeaderText(resourceBundle.getString("errorHeaderInvalid"));
            alert.setContentText(resourceBundle.getString("errorContentInvalid"));
            alert.showAndWait();

            passwordTF.setText("");
        }
        //if username and password are valid, store username and change to main scene
        else {
            //store username value
            LoginActivity.track(username, "Pass");
            //Login.setUsername(username);

            ChangeScene.getMainScene(actionEvent);

            //grab current time
            currentDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));

            //SQL query to grab any appointments, if any, within 15 mins of user's log-in
            String appointmentQuery = "SELECT Appointment_ID, Start FROM appointments " +
                    "WHERE CAST(Start as DATE) = '" + currentDateTime.toLocalDate() + "' AND ('"
                    + Formatter.dateTimeFormatter(currentDateTime) + "' <= Start AND '" +
                    Formatter.dateTimeFormatter(currentDateTime.plusMinutes(15)) + "' >= Start);";

            //excute SQL query
            resultSet = statement.executeQuery(appointmentQuery);
            //if there is an appointment, grab appointment ID and time
            if(resultSet.next()) {
                appointmentID = resultSet.getInt("Appointment_ID");
                dateTime = resultSet.getTimestamp("Start").toLocalDateTime();

                //alert user of appointment
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment");
                alert.setHeaderText("Upcoming Appointment");
                alert.setContentText("Appointment ID: " + appointmentID +
                        "\nDate: " + dateTime.toString().substring(0, 10) +
                        "\nTime: " + dateTime.toString().substring(11,16));
                alert.showAndWait();
            }
            //alert user there is no appointment
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Appointment");
                alert.setHeaderText("Upcoming Appointment");
                alert.setContentText("No upcoming appointments");
                alert.showAndWait();
            }
        }
    }

    /**
     * Validates if a password matches with the database, based on username.
     *
     * @param username A string containing the username.
     * @param password A string containing the password.
     * @return <code>true</code> If the password matches with database.
     *         <code>false</code> Otherwise.
     * @throws SQLException If a SQL exception occurred.
     */
    private boolean validLogin(String username, String password) throws SQLException {
        statement = conn.createStatement();

        //SQL query to grab username and password if it matches the database
        String userPassQuery = "SELECT * FROM users WHERE User_Name ='" + username + "' AND " +
                "Password = '" + password + "';";
        resultSet = statement.executeQuery(userPassQuery);

        //if results match, return true, else return false
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Initializes the LoginController class.
     * Sets all labels based on user's computer's language (English or French).
     *
     * @param url The location used to resolve relative paths for the root object,
     *            or null if the location is not known.
     * @param rb The resources used to localize the root object,
     *           or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userLocationLBL.setText(String.valueOf(zoneId));
        headerLBL.setText(resourceBundle.getString("titleLBL"));
        usernameLBL.setText(resourceBundle.getString("usernameLBL"));
        passwordLBL.setText(resourceBundle.getString("passwordLBL"));
        loginBTN.setText(resourceBundle.getString("loginBTN"));
        zoneIdLBL.setText(resourceBundle.getString("zoneLBL"));
    }
}

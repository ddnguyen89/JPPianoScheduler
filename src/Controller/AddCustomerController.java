/*
 * Program: AddCustomerController.java
 * Author: Davis Nguyen
 * Description: AddCustomerController class used to control AddCustomerView.fxml
 */


package Controller;

import Model.Login;
import Resources.ChangeScene;
import Resources.List;
import Utilities.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static Resources.Formatter.numberValidationFormatter;

/**
 * This class controls AddCustomerView FXML.
 *
 * @author Davis Nguyen
 */
public class AddCustomerController implements Initializable {
    //references to view
    @FXML
    private TextField nameTF, addressTF, postalTF, phoneTF;
    @FXML
    private ComboBox stPvCB, countryCB;
    @FXML
    private Button addBTN, cancelBTN;
    @FXML
    private Label headerLBL, customerIDLBL, customerDBLBL, nameLBL,
            addressLBL, stPvLBL, countryLBL, postalLBL, phoneLBL, errorLBL;

    //instantiating variables
    String autoIncrementIDvalue = "";
    String phoneNumberFormat = "";
    int divisionIDvalue = 0;
    String uservalue = "";

    //database connection statements
    private Connection conn = DBConnection.startConnection();
    private Statement statement;
    ResultSet resultSet = null;

    /**
     * Sets the state/province spinner based on country spinner selection.
     * Sets cityCCB once user selects countryCCB.
     *
     * @param actionEvent The action event.
     */
    public void countryOA(javafx.event.ActionEvent actionEvent) {
        //if country is US, set stPvCB to US cities and label to state
        if (countryCB.getValue().equals("U.S")) {
            stPvCB.setItems(List.usCities);
            stPvLBL.setText("State");
        }

        if (countryCB.getValue().equals("UK")) {
            stPvCB.setItems(List.ukCities);
            stPvLBL.setText("State");
        }

        if (countryCB.getValue().equals("Canada")) {
            stPvCB.setItems(List.canadaProvinces);
            stPvLBL.setText("Province");
        }
    }

    /**
     * Calls ChangeScene to load and display MainView fxml.
     *
     * @param actionEvent The action event.
     * @throws IOException If an input or output exception occurred.
     */
    public void cancelBTNOA(javafx.event.ActionEvent actionEvent) throws IOException {
        ChangeScene.getMainScene(actionEvent);
    }

    /**
     * Adds new customer to database and calls ChangeScene to load and display MainView fxml.
     *
     * @param actionEvent The action event.
     * @throws IOException If an input or output exception occurred.
     * @throws SQLException If a SQL exception occurred.
     */
    public void addBTNOA(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {
        statement = conn.createStatement();

        //check if values are empty/null, return error message
        if (nameTF.getText().equals("") || addressTF.getText().equals("") || stPvCB.getValue() == null ||
                countryCB.getValue() == null || postalTF.getText().equals("") || phoneTF.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing fields");
            alert.setContentText("All fields must be filled");

            alert.showAndWait();
        }
        //check if country value is UK and phone.length is not 12, return error message
        else if (countryCB.getValue() == "UK" && phoneTF.getText().length() != 12) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incorrect number of digits");
            alert.setContentText("UK phone numbers contain 12 digits" +
                    "\nCurrent number of digits: " + phoneTF.getText().length() +
                    "\nPhone number Input: " + phoneTF.getText());

            alert.showAndWait();
        }
        //check if country value is not UK and phone.length is not 10, return error message
        else if (countryCB.getValue() != "UK" && phoneTF.getText().length() != 10) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incorrect number of phone digits");
            alert.setContentText("US and Canadian phone numbers contain 10 digits" +
                    "\nCurrent number of digits: " + phoneTF.getText().length() +
                    "\nPhone number input: " + phoneTF.getText());

            alert.showAndWait();
        } else {
            //formatting phone for database insert
            if (countryCB.getValue() == "UK") {
                phoneNumberFormat = phoneTF.getText().replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3-$4");
            } else {
                phoneNumberFormat = phoneTF.getText().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
            }

            //SQL query to change countryCCB to Division_ID
            String divisionIDQuery = "SELECT Division_ID from first_level_divisions " +
                    "WHERE Division='" + stPvCB.getValue() + "';";
            resultSet = statement.executeQuery(divisionIDQuery);
            while (resultSet.next()) {
                divisionIDvalue = resultSet.getInt("Division_ID");
            }

            //grab user login name
            uservalue = Login.getUsername();

            //SQL insert statement
            String SQLInsert = "INSERT INTO customers (Customer_Name, Address, Postal_Code, " +
                    "Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                    "VALUES('" + nameTF.getText() + "', " +
                    "'" + addressTF.getText() + "', " +
                    "'" + postalTF.getText() + "', " +
                    "'" + phoneNumberFormat + "', " +
                    "NOW(), " +
                    "'" + uservalue + "', " +
                    "NOW(), " +
                    "'" + uservalue + "', " +
                    "'" + divisionIDvalue + "')";

            statement.executeUpdate(SQLInsert);

            ChangeScene.getMainScene(actionEvent);
        }
    }

    /**
     * Initializes the AddCustomerController class.
     * Sets all combobox variables, auto incrementing appointment ID, and textformatter for spinners.
     *
     * @param url The location used to resolve relative paths for the root object,
     *            or null if the location is not known.
     * @param rb The resources used to localize the root object,
     *           or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            statement = conn.createStatement();

            //SQL query to retrieve next autoIncrement id
            String autoIncrementQuery = "SELECT AUTO_INCREMENT " +
                    "FROM information_schema.TABLES " +
                    "WHERE (TABLE_NAME = 'customers')";
            resultSet = statement.executeQuery(autoIncrementQuery);
            while (resultSet.next()) {
                autoIncrementIDvalue = resultSet.getString("AUTO_INCREMENT");
            }

            //set customerDBLBL to autoIncrementIDvalue
            customerDBLBL.setText(autoIncrementIDvalue);

            //set countryCB with array list of countries
            countryCB.setItems(List.countries);

            //set phone formatter
            phoneTF.setTextFormatter(new TextFormatter<Object>(numberValidationFormatter));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}

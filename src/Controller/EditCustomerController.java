/*
 * Program: EditCustomerController.java
 * Author: Davis Nguyen
 * Description:  EditCustomerController class used to control EditCustomerView.fxml
 */

package Controller;

import Model.Customer;
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
 * This class controls EditCustomerView FXML.
 *
 * @author Davis Nguyen
 */
public class EditCustomerController implements Initializable {
    //references to view
    @FXML
    private TextField nameTF, addressTF, postalTF, phoneTF;
    @FXML
    private ComboBox stPvCBB, countryCBB;
    @FXML
    private Button cancelBTN, updateBTN;
    @FXML
    private Label headerLBL, customerIDLBL, customerDBLBL, nameLBL,
            addressLBL, stPvLBL, countryLBL, postalLBL, phoneLBL, errorLBL;

    //instantiating variables
    private Customer customer;
    private String countryValue;
    String phoneNumber;
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
        if (countryCBB.getValue().equals("U.S")) {
            stPvCBB.setItems(List.usCities);
            stPvLBL.setText("State");
        }
        //if country is UK, set stPvCB to uk cities and label to state
        if (countryCBB.getValue().equals("UK")) {
            stPvCBB.setItems(List.ukCities);
            stPvLBL.setText("State");
        }
        //if country is Canada, set stPvCB to Canada cities and label to province
        if (countryCBB.getValue().equals("Canada")) {
            stPvCBB.setItems(List.canadaProvinces);
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
     * Updates customer to database and calls ChangeScene to load and display MainView fxml.
     *
     * @param actionEvent The action event.
     * @throws IOException If an input or output exception occurred.
     * @throws SQLException If a SQL exception occurred.
     */
    public void updateBTNOA(javafx.event.ActionEvent actionEvent) throws SQLException, IOException {
        statement = conn.createStatement();

        //check if values are empty/null, return error message
        if (nameTF.getText().equals("") || addressTF.getText().equals("") || stPvCBB.getValue() == null ||
                countryCBB.getValue() == null || postalTF.getText().equals("") || phoneTF.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing fields");
            alert.setContentText("All fields must be filled");

            alert.showAndWait();
        }
        //check if country value is UK and phone.length is not 12, return error message
        else if (countryCBB.getValue() == "UK" && phoneTF.getText().length() != 12) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Phone Length");
            alert.setContentText("UK phone numbers contain 12 digits" +
                    "\nCurrent number of digits: " + phoneTF.getText().length() +
                    "\nCurrent Input: " + phoneTF.getText());

            alert.showAndWait();
        }
        //check if country value is not UK and phone.length is not 10, return error message
        else if (countryCBB.getValue() != "UK" && phoneTF.getText().length() != 10) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Phone Length");
            alert.setContentText("US and Canadian phone numbers contain 10 digits" +
                    "\nCurrent number of digits: " + phoneTF.getText().length() +
                    "\nCurrent Input: " + phoneTF.getText());

            alert.showAndWait();
        } else {
            //formatting phone for database insert
            if (countryCBB.getValue() == "UK") {
                phoneNumberFormat = phoneTF.getText().replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3-$4");
            } else {
                phoneNumberFormat = phoneTF.getText().replaceFirst("(\\d{3})(\\d{3})(\\d+)", "$1-$2-$3");
            }

            //SQL query to change countryCCB to Division_ID
            String divisionIDQuery = "SELECT Division_ID from first_level_divisions " +
                    "WHERE Division='" + stPvCBB.getValue() + "';";
            resultSet = statement.executeQuery(divisionIDQuery);
            while (resultSet.next()) {
                divisionIDvalue = resultSet.getInt("Division_ID");
            }

            //grab user login name
            uservalue = Login.getUsername();

            //SQL insert statement
            String SQLUpdate = "UPDATE customers SET " +
                    "Customer_Name = '" + nameTF.getText() + "', " +
                    "Address = '" + addressTF.getText() + "', " +
                    "Postal_Code = '" + postalTF.getText() + "', " +
                    "Phone = '" + phoneNumberFormat + "', " +
                    "Last_Update = NOW(), " +
                    "Last_Updated_By = '" + uservalue + "', " +
                    "Division_ID = '" + divisionIDvalue + "' " +
                    "WHERE Customer_ID = '" + customerDBLBL.getText() + "';";

            statement.executeUpdate(SQLUpdate);

            ChangeScene.getMainScene(actionEvent);
        }
    }

    /**
     * Sets selected customer values.
     *
     * @param value The customer selected
     * @throws SQLException If a SQL exception occurred.
     */
    public void setCustomer(Customer value) throws SQLException {
        //store selected value into customer
        customer = value;

        statement = conn.createStatement();

        //SQL query to grab country from country ID
        String countryQuery = "SELECT Country from countries " +
                "WHERE Country_ID='" + customer.getCountryID() + "';";
        resultSet = statement.executeQuery(countryQuery);

        while (resultSet.next()) {
            countryValue = resultSet.getString("Country");
        }

        //if country is US, set stPvCB to US cities and label to state
        if (countryValue.equals("U.S")) {
            stPvCBB.setItems(List.usCities);
            stPvLBL.setText("State");
        }
        //if country is UK, set stPvCB to uk cities and label to state
        if (countryValue.equals("UK")) {
            stPvCBB.setItems(List.ukCities);
            stPvLBL.setText("State");
        }
        //if country is Canada, set stPvCB to Canada cities and label to province
        if (countryValue.equals("Canada")) {
            stPvCBB.setItems(List.canadaProvinces);
            stPvLBL.setText("Province");
        }

        //remove all of the dashes in phone number
        phoneNumber = customer.getPhone().replaceAll("-", "");

        //set all selected database variables in textfields, label, and comboboxes
        customerDBLBL.setText(String.valueOf(customer.getCustomerID()));
        nameTF.setText(customer.getCustomerName());
        addressTF.setText(customer.getAddress());
        stPvCBB.setValue(customer.getDivision());
        countryCBB.setValue(countryValue);
        postalTF.setText(customer.getPostal());
        phoneTF.setText(phoneNumber);
    }

    /**
     * Initializes the EditCustomerController class.
     * Sets all combobox variables, auto incrementing appointment ID, and textformatter for spinners.
     *
     * @param url The location used to resolve relative paths for the root object,
     *            or null if the location is not known.
     * @param rb The resources used to localize the root object,
     *           or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set countryCB with array list of countries
        countryCBB.setItems(List.countries);

        //set phone formatter
        phoneTF.setTextFormatter(new TextFormatter<Object>(numberValidationFormatter));
    }
}

/*
 * Program: MainController.java
 * Author: Davis Nguyen
 * Description: MainController class used to control MainView.fxml
 */

package Controller;

import Model.*;
import Resources.ChangeScene;
import Resources.Formatter;
import Utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * This class controls MainView FXML.
 *
 * @author Davis Nguyen
 */
public class MainController implements Initializable {

    //references to view
    @FXML
    private Button addCustomerBTN, editCustomerBTN, addAppointmentBTN, editAppointmentBTN;
    @FXML
    private TabPane tabPane;

    //references to appointment table
    @FXML
    private TableView<Appointment> appointmentTV;
    @FXML
    private TableColumn<Appointment, Integer> ATappointmentIDCOL, ATcustomerIDCOL, ATcontactCOL;
    @FXML
    private TableColumn<Appointment, String> ATtitleCOL, ATdescriptionCOL, ATlocationCOL, ATtypeCOL;
    @FXML
    private TableColumn<Appointment, Date> ATstartCOL, ATendCOL;

    //references to customer table
    @FXML
    private TableView<Customer> customerTV;
    @FXML
    private TableColumn<Customer, Integer> CTcustomerIDCOL, CTcountryIDCOL;
    @FXML
    private TableColumn<Customer, String> CTnameCOL, CTaddressCOL, CTpostalCodeCOL, CTphoneCOL, CTcreatedByCOL, CTupdatedByCOL, CTdivisionCOL;
    @FXML
    private TableColumn<Customer, Date> CTcreateDateCOL;
    @FXML
    private TableColumn<Customer, Timestamp> CTupdateCOL;

    //references to reports table
    @FXML
    private TableView reportsTV;

    //table columns for appointment type by month
    private TableColumn month = new TableColumn("Month");
    private TableColumn type = new TableColumn("Type");
    private TableColumn monthTypeTotal = new TableColumn("Total Type");

    //table columns for appointment schedules by customer
    private TableColumn contactName = new TableColumn("Contact Name");
    private TableColumn appointmentID = new TableColumn("Appointment ID");
    private TableColumn title = new TableColumn("Title");
    private TableColumn description = new TableColumn("Description");
    private TableColumn date = new TableColumn("Date");
    private TableColumn startTime = new TableColumn("Start Time");
    private TableColumn endTime = new TableColumn("End Time");
    private TableColumn customerID = new TableColumn("Customer ID");

    //table columns for total appointments for contact by month
    private TableColumn appTotal = new TableColumn("Total Number of Appointments");

    //observable list for storing model data
    private ObservableList<Appointment> appointments;
    private ObservableList<Customer> customers;
    private ObservableList<ReportsMonth> reportsMonth;
    private ObservableList<ReportsContact> reportsContact;
    private ObservableList<ReportsCMTotal> reportsCMTotal;

    //user selected item
    Customer customerSelectedItem;
    Appointment appointmentSelectedItem;

    //database connection statements
    private Connection conn = DBConnection.startConnection();
    private Statement statement;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    /*--Table views--*/

    /**
     * Gets appointment data from database.
     *
     * @param conn The connection session with the database.
     * @return An appointment model
     * @throws SQLException If a SQL exception occurred.
     */
    private ObservableList<Appointment> fetchAppointment(Connection conn) throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        statement = conn.createStatement();
        //execute SQL statement, grabbing appointments
        resultSet = statement.executeQuery("SELECT *, contacts.Contact_name FROM appointments " +
                "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID " +
                "ORDER BY Appointment_ID");
        //add results to new appointments
        while (resultSet.next()) {
            appointments.add(new Appointment(
                    resultSet.getInt("Appointment_ID"),
                    resultSet.getString("Title"),
                    resultSet.getString("Description"),
                    resultSet.getString("Location"),
                    resultSet.getString("Type"),
                    resultSet.getTimestamp("Start").toLocalDateTime(),
                    resultSet.getTimestamp("End").toLocalDateTime(),
                    resultSet.getTimestamp("Create_Date").toLocalDateTime(),
                    resultSet.getString("Created_By"),
                    resultSet.getTimestamp("Last_Update"),
                    resultSet.getString("Last_Updated_By"),
                    resultSet.getInt("Customer_ID"),
                    resultSet.getInt("User_ID"),
                    resultSet.getInt("Contact_ID"),
                    resultSet.getString("Contact_Name")
            ));
        }
        //return an appointment model
        return appointments;
    }

    /**
     * Sets appointment model into table view
     */
    private void setAppointmentTV() {
        ATappointmentIDCOL.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentID"));
        ATtitleCOL.setCellValueFactory(new PropertyValueFactory("title"));
        ATdescriptionCOL.setCellValueFactory(new PropertyValueFactory("description"));
        ATlocationCOL.setCellValueFactory(new PropertyValueFactory("location"));
        ATcontactCOL.setCellValueFactory(new PropertyValueFactory("contactName"));
        ATtypeCOL.setCellValueFactory(new PropertyValueFactory("type"));
        ATstartCOL.setCellValueFactory(new PropertyValueFactory("start"));
        ATstartCOL.setCellFactory(new Formatter.DateColumnFormatter<>());
        ATendCOL.setCellValueFactory(new PropertyValueFactory("end"));
        ATendCOL.setCellFactory(new Formatter.DateColumnFormatter<>());
        ATcustomerIDCOL.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));
    }

    /**
     * Gets customer data from database.
     *
     * @param conn The connection session with the database.
     * @return An customer model
     * @throws SQLException If a SQL exception occurred.
     */
    private ObservableList<Customer> fetchCustomer(Connection conn) throws SQLException {
        customers = FXCollections.observableArrayList();

        statement = conn.createStatement();
        //execute SQL statement, grabbing customers
        resultSet = statement.executeQuery("SELECT *, first_level_divisions.Division, first_level_divisions.COUNTRY_ID FROM customers " +
                "INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID " +
                "ORDER BY Customer_ID");
        //add results to new customers
        while (resultSet.next()) {
            customers.add(new Customer(
                    resultSet.getInt("Customer_ID"),
                    resultSet.getString("Customer_Name"),
                    resultSet.getString("Address"),
                    resultSet.getString("Postal_Code"),
                    resultSet.getString("Phone"),
                    resultSet.getTimestamp("Create_Date").toLocalDateTime(),
                    resultSet.getString("Created_By"),
                    resultSet.getTimestamp("Last_Update"),
                    resultSet.getString("Last_Updated_By"),
                    resultSet.getInt("Division_ID"),
                    resultSet.getString("Division"),
                    resultSet.getInt("COUNTRY_ID")
            ));
        }
        //return a customer model
        return customers;
    }

    /**
     * Sets customer model into table view
     */
    private void setCustomerTV() {
        CTcustomerIDCOL.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerID"));
        CTnameCOL.setCellValueFactory(new PropertyValueFactory("customerName"));
        CTaddressCOL.setCellValueFactory(new PropertyValueFactory("address"));
        CTpostalCodeCOL.setCellValueFactory(new PropertyValueFactory("postal"));
        CTphoneCOL.setCellValueFactory(new PropertyValueFactory("phone"));
        CTcreateDateCOL.setCellValueFactory(new PropertyValueFactory("createDate"));
        CTcreateDateCOL.setCellFactory(new Formatter.DateColumnFormatter<>());
        CTcreatedByCOL.setCellValueFactory(new PropertyValueFactory("createdBy"));
        CTupdateCOL.setCellValueFactory(new PropertyValueFactory("lastUpdate"));
        CTupdatedByCOL.setCellValueFactory(new PropertyValueFactory("lastUpdatedBy"));
        CTdivisionCOL.setCellValueFactory(new PropertyValueFactory("division"));
        CTcountryIDCOL.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("countryID"));
    }

    /* Appointment Radio Buttons */

    /**
     * Displays all appointments.
     *
     * @param actionEvent The action event.
     */
    public void allRBTNOA(javafx.event.ActionEvent actionEvent) {
        try {
            //call fetchAppointment and store and set all appointments
            appointments = fetchAppointment(conn);
            appointmentTV.setItems(appointments);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Displays appointments for current month.
     *
     * @param actionEvent the action event.
     */
    public void monthRBTNOA(javafx.event.ActionEvent actionEvent) {
        try {
            //call fetchAppointmentMonth and store and set all appointments within current month
            appointments = fetchAppointmentMonth(conn);
            appointmentTV.setItems(appointments);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Displays appointments for current week.
     *
     * @param actionEvent The action event.
     */
    public void weekRBTNOA(javafx.event.ActionEvent actionEvent) {
        try {
            //call fetchAppointmentWeek and store and set all appointments within current week
            appointments = fetchAppointmentWeek(conn);
            appointmentTV.setItems(appointments);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Gets appointment data from database for current month.
     *
     * @param conn The connection session with the database.
     * @return An appointment model
     * @throws SQLException If a SQL exception occurred.
     */
    private ObservableList<Appointment> fetchAppointmentMonth(Connection conn) throws SQLException {
        appointments = FXCollections.observableArrayList();

        //get current month and start of next month
        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate startOfNextMonth = startOfMonth.plusMonths(1);

        //SQL statement to grab appointments for current month
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT *, contacts.Contact_name FROM appointments " +
                "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID " +
                "WHERE Start >= ? AND Start < ?  " +
                "ORDER BY Start");
        preparedStatement.setString(1, String.valueOf(startOfMonth) + " 00:00:00");
        preparedStatement.setString(2, String.valueOf(startOfNextMonth) + " 00:00:00");

        ResultSet resultSet = preparedStatement.executeQuery();

        //add results to new appointments
        while (resultSet.next()) {
            appointments.add(new Appointment(
                    resultSet.getInt("Appointment_ID"),
                    resultSet.getString("Title"),
                    resultSet.getString("Description"),
                    resultSet.getString("Location"),
                    resultSet.getString("Type"),
                    resultSet.getTimestamp("Start").toLocalDateTime(),
                    resultSet.getTimestamp("End").toLocalDateTime(),
                    resultSet.getTimestamp("Create_Date").toLocalDateTime(),
                    resultSet.getString("Created_By"),
                    resultSet.getTimestamp("Last_Update"),
                    resultSet.getString("Last_Updated_By"),
                    resultSet.getInt("Customer_ID"),
                    resultSet.getInt("User_ID"),
                    resultSet.getInt("Contact_ID"),
                    resultSet.getString("Contact_Name")
            ));
        }
        //return an appointment model
        return appointments;
    }



    /**
     * Gets appointment data from database for current week.
     *
     * @param conn The connection session with the database.
     * @return An appointment model
     * @throws SQLException If a SQL exception occurred.
     */
    private ObservableList<Appointment> fetchAppointmentWeek(Connection conn) throws SQLException {
        appointments = FXCollections.observableArrayList();

        //get current week and start of next week
        LocalDate startOfWeek = LocalDate.now();
        LocalDate startOfNextWeek = startOfWeek.plusWeeks(1);

        //SQL statement to grab appointments for current week
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT *, contacts.Contact_name FROM appointments " +
                "INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID " +
                "WHERE Start >= ? AND Start < ?  " +
                "ORDER BY Start");
        preparedStatement.setString(1, String.valueOf(startOfWeek) + " 00:00:00");
        preparedStatement.setString(2, String.valueOf(startOfNextWeek) + " 00:00:00");

        ResultSet resultSet = preparedStatement.executeQuery();

        //add results to new appointments
        while (resultSet.next()) {
            appointments.add(new Appointment(
                    resultSet.getInt("Appointment_ID"),
                    resultSet.getString("Title"),
                    resultSet.getString("Description"),
                    resultSet.getString("Location"),
                    resultSet.getString("Type"),
                    resultSet.getTimestamp("Start").toLocalDateTime(),
                    resultSet.getTimestamp("End").toLocalDateTime(),
                    resultSet.getTimestamp("Create_Date").toLocalDateTime(),
                    resultSet.getString("Created_By"),
                    resultSet.getTimestamp("Last_Update"),
                    resultSet.getString("Last_Updated_By"),
                    resultSet.getInt("Customer_ID"),
                    resultSet.getInt("User_ID"),
                    resultSet.getInt("Contact_ID"),
                    resultSet.getString("Contact_Name")
            ));
        }
        //return an appointment model
        return appointments;
    }

    /* Buttons */

    /**
     * Calls ChangeScene to load and display AddAppointmentView fxml.
     *
     * @param actionEvent The action event.
     * @throws IOException  If an input or output exception occurred.
     */
    public void addAppointmentBTNOA(javafx.event.ActionEvent actionEvent) throws IOException {
        ChangeScene.getAddAppointmentScene(actionEvent);
    }

    /**
     * Calls ChangeScene to load and display EditAppointmentView fxml.
     *
     * @param actionEvent The action event.
     */
    public void editAppointmentBTNOA(javafx.event.ActionEvent actionEvent) {
        //grab user appointment table row selection
        appointmentSelectedItem = appointmentTV.getSelectionModel().getSelectedItem();

        try {
            //if no user appointment table row selected, return error message
            if (appointmentSelectedItem == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No Appointment Selected");
                alert.setContentText("Please select an appointment");
                alert.showAndWait();
            }
            //otherwise, call ChangeScene and pass appointment table row selected
            else {
                ChangeScene.getEditAppointmentScene(actionEvent, appointmentTV.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calls ChangeScene to load and display AddCustomerView fxml.
     *
     * @param actionEvent The action event.
     * @throws IOException  If an input or output exception occurred.
     */
    public void addCustomerBTNOA(javafx.event.ActionEvent actionEvent) throws IOException {
        ChangeScene.getAddCustomerScene(actionEvent);
    }

    /**
     * Calls ChangeScene to load and display EditCustomerView fxml.
     *
     * @param actionEvent The action event.
     */
    public void editCustomerBTNOA(javafx.event.ActionEvent actionEvent) {
        //grab user customer table row selection
        customerSelectedItem = customerTV.getSelectionModel().getSelectedItem();
        try {
            //if no user customer table row selected, return error message
            if (customerSelectedItem == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No Customer Selected");
                alert.setContentText("Please select a customer");
                alert.showAndWait();
            }
            //otherwise, call ChangeScene and pass customer table row selected
            else {
                ChangeScene.getEditCustomerScene(actionEvent, customerTV.getSelectionModel().getSelectedItem());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes selected appointment object from database.
     *
     * @param appointmentID The appointment selected.
     * @throws SQLException If a SQL exception occurred.
     */
    public void removeAppointment(int appointmentID) throws SQLException {
        try {
            //execute SQL statement to delete selected appointment from appointments database
            preparedStatement = conn.prepareStatement("DELETE FROM appointments where Appointment_ID = ?");
            preparedStatement.setInt(1, appointmentID);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    /**
     * Removes selected appointment object from table view.
     * Lambda expression used to execute user selected response and delete appointment object from database and table view.
     *
     * @param actionEvent The action event.
     */
    public void deleteAppointmentBTNOA(javafx.event.ActionEvent actionEvent) {
        //grab user customer table row selection
        appointmentSelectedItem = appointmentTV.getSelectionModel().getSelectedItem();

        //if no selection made on customer table, return error message
        if (appointmentSelectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Appointment Selected");
            alert.setContentText("Please select an appointment");
            alert.showAndWait();
        }
        //confirmation dialog for user selection deletion, confirmation message
         else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete \"" + appointmentSelectedItem.getTitle() + "\"?");
            alert.setContentText("Appointment ID: " + appointmentSelectedItem.getAppointmentID() +
                    "\nAppointment Type: " + appointmentSelectedItem.getType() +
                    "\nAre you sure you want to delete this appointment?");
            alert.showAndWait().ifPresent((response -> {
                //if user selects OK button, delete row from tableview and from database
                if (response == ButtonType.OK) {
                    try {
                        statement = conn.createStatement();

                        appointmentTV.getItems().remove(appointmentSelectedItem);
                        removeAppointment(appointmentSelectedItem.getAppointmentID());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
    }

    /**
     * Removes selected customer object from database.
     *
     * @param customerID The customer selected.
     * @throws SQLException If a SQL exception occurred.
     */
    public void removeCustomer(int customerID) throws SQLException {
        try {
            //execute SQL statement to delete selected customer from appointments and customers database
            preparedStatement = conn.prepareStatement("DELETE FROM appointments where Customer_ID = ?");
            preparedStatement.setInt(1, customerID);
            preparedStatement.execute();
            preparedStatement = conn.prepareStatement("DELETE FROM customers where Customer_ID = ?");
            preparedStatement.setInt(1, customerID);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    /**
     * Removes selected customer object from table view.
     * Lambda expression used to execute user selected response and delete customer object from database and table view.
     *
     * @param actionEvent The action event.
     */
    public void deleteCustomerBTNOA(javafx.event.ActionEvent actionEvent) {
        //grab user customer table row selection
        customerSelectedItem = customerTV.getSelectionModel().getSelectedItem();

        //if no selection made on customer table, return error message
        if (customerSelectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer");
            alert.showAndWait();
        }
        //confirmation dialog for user selection deletion, confirmation message
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete \"" + customerSelectedItem.getCustomerName() + "\"?");
            alert.setContentText("Are you sure you want to delete this customer?");
            alert.showAndWait().ifPresent((response -> {
                //if user selects OK button, delete row from tableview and from database
                if (alert.getResult() == ButtonType.OK) {
                    try {
                        statement = conn.createStatement();

                        customerTV.getItems().remove(customerSelectedItem);
                        removeCustomer(customerSelectedItem.getCustomerID());

                        appointments = fetchAppointment(conn);
                        setAppointmentTV();
                        appointmentTV.setItems(appointments);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
    }

    /* Reports radio buttons */

    /**
     * Displays the total number of appointments by type for each month.
     *
     * @param actionEvent The action event.
     * @throws SQLException If a SQL exception occurred.
     */
    public void reportsMonthRBTNOA(javafx.event.ActionEvent actionEvent) throws SQLException {
        statement = conn.createStatement();

        //clear current tableview and add new table columns
        reportsTV.getColumns().clear();
        reportsTV.getItems().clear();
        reportsTV.getColumns().addAll(month, type, monthTypeTotal);

        reportsMonth = FXCollections.observableArrayList();

        //SQL query to grab total number of appointments by type for each month
        String reportsMonthQuery = "SELECT MONTHNAME(Start) AS Month, Type, COUNT(*) AS 'Total Type' " +
                "FROM appointments " +
                "GROUP BY MONTHNAME(Start), Type;";
        resultSet = statement.executeQuery(reportsMonthQuery);

        //add results to new reportsMonth
        while (resultSet.next()) {
            reportsMonth.add(new ReportsMonth(
                    resultSet.getString("Month"),
                    resultSet.getString("Type"),
                    resultSet.getInt("Total Type")
            ));
        }

        //set reportsMonth into table view
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthTypeTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        //display reportsMonth table view
        reportsTV.setItems(reportsMonth);
    }

    /**
     * Displays contact's schedule.
     *
     * @param actionEvent The action event.
     * @throws SQLException If a SQL exception occurred.
     */
    public void reportsContactRBTNOA(javafx.event.ActionEvent actionEvent) throws SQLException {
        statement = conn.createStatement();

        //clear current tableview and add new table columns
        reportsTV.getColumns().clear();
        reportsTV.getItems().clear();
        reportsTV.getColumns().addAll(contactName, appointmentID, title, type, description,
                date, startTime, endTime, customerID);

        reportsContact = FXCollections.observableArrayList();

        //SQL query to grab contact's schedule
        String reportsCustomerQuery = "SELECT Contact_Name, Appointment_ID, Title, Type, Description, " +
                "DATE(Start) AS Date, TIME(Start) AS 'Start Time', TIME(End) AS 'End Time', Customer_ID " +
                "FROM appointments JOIN contacts ON contacts.Contact_ID = appointments.Contact_ID " +
                "ORDER BY Contact_Name, Date;";
        resultSet = statement.executeQuery(reportsCustomerQuery);

        //add results to new reportsContact
        while (resultSet.next()) {
            reportsContact.add(new ReportsContact(
                    resultSet.getString("Contact_Name"),
                    resultSet.getInt("Appointment_ID"),
                    resultSet.getString("Title"),
                    resultSet.getString("Type"),
                    resultSet.getString("Description"),
                    resultSet.getString("Date"),
                    resultSet.getString("Start Time"),
                    resultSet.getString("End Time"),
                    resultSet.getInt("Customer_ID")
            ));
        }

        //set reportsContact into table view
        contactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        startTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));

        //display reportsContact table view
        reportsTV.setItems(reportsContact);
    }

    /**
     * Displays total number of appointments for each contacts by month.
     *
     * @param actionEvent The action event.
     * @throws SQLException If a SQL exception occurred.
     */
    public void reportsCMTotalRBTNOA(javafx.event.ActionEvent actionEvent) throws SQLException {
        statement = conn.createStatement();

        //clear current tableview and add new table columns
        reportsTV.getColumns().clear();
        reportsTV.getItems().clear();
        reportsTV.getColumns().addAll(contactName, month, appTotal);

        reportsCMTotal = FXCollections.observableArrayList();

        //SQL query to grab the total number of appointments for each contacts by month
        String reportsCMTotalQuery = "SELECT Contact_Name, MONTHNAME(Start) AS Month, COUNT(*) AS 'Total Number of Appointments' " +
                "FROM appointments JOIN contacts ON contacts.Contact_ID = appointments.Contact_ID " +
                "GROUP BY Contact_Name, Month;";
        resultSet = statement.executeQuery(reportsCMTotalQuery);

        //add results to new reportsCMTotal
        while (resultSet.next()) {
            reportsCMTotal.add(new ReportsCMTotal(
                    resultSet.getString("Contact_Name"),
                    resultSet.getString("Month"),
                    resultSet.getInt("Total Number of Appointments")
            ));
        }

        //set reportsCMTotal into table view
        contactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        appTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        //display reportsCMTotal table view
        reportsTV.setItems(reportsCMTotal);
    }

    /**
     * Initializes the MainController class.
     * Sets tableviews.
     *
     * @param url The location used to resolve relative paths for the root object,
     *            or null if the location is not known.
     * @param rb The resources used to localize the root object,
     *           or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //grab appointments and customers from database
            appointments = fetchAppointment(conn);
            customers = fetchCustomer(conn);

            //set appointments and customers table view
            setAppointmentTV();
            setCustomerTV();

            //display appointments and customers table view
            appointmentTV.setItems(appointments);
            customerTV.setItems(customers);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}

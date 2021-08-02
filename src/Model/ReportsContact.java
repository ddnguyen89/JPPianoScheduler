/*
 * Program: ReportsContact.java
 * Author: Davis Nguyen
 * Description: ReportsContact class used to model a contact's schedule.
 */

package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class represents a contact's schedule.
 *
 * @author Davis Nguyen
 */
public class ReportsContact {
    //instantiating variables
    private SimpleStringProperty contactName, title, type, description, date, startTime, endTime;
    private SimpleIntegerProperty appointmentID, customerID;

    //default constructor
    public ReportsContact() {
    }

    /**
     * Creates a contact's schedule.
     *
     * @param contactName The contact's name.
     * @param appointmentID The appointment's ID.
     * @param title The appointment's title.
     * @param type The appointment's type.
     * @param description The appointment's description.
     * @param date The appointment's date.
     * @param startTime The appointment's start time.
     * @param endTime The appointment's end time.
     * @param customerID The customer's ID.
     */
    public ReportsContact(String contactName, int appointmentID, String title, String type, String description,
                          String date, String startTime, String endTime, int customerID) {
        this.contactName = new SimpleStringProperty(contactName);
        this.appointmentID = new SimpleIntegerProperty(appointmentID);
        this.title = new SimpleStringProperty(title);
        this.type = new SimpleStringProperty(type);
        this.description = new SimpleStringProperty(description);
        this.date = new SimpleStringProperty(date);
        this.startTime = new SimpleStringProperty(startTime);
        this.endTime = new SimpleStringProperty(endTime);
        this.customerID = new SimpleIntegerProperty(customerID);
    }

    /* Setters and getters */

    /**
     * Sets the contact's name.
     * @param contactName A string containing the contact's name.
     */
    public void setContactName(String contactName) {
        this.contactName = new SimpleStringProperty(contactName);
    }
    /**
     * Gets the contact's name.
     * @return A string representing the contact's name.
     */
    public String getContactName() {
        return contactName.get();
    }

    /**
     * Sets the appointment's ID.
     * @param appointmentID An integer containing the appointment's ID.
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = new SimpleIntegerProperty(appointmentID);
    }
    /**
     * Gets the appointment's ID.
     * @return An integer representing the appointment's ID.
     */
    public int getAppointmentID() {
        return appointmentID.get();
    }

    /**
     * Sets the appointment's title.
     * @param title A string containing the appointment's title.
     */
    public void setTitle(String title) {
        this.title = new SimpleStringProperty(title);
    }
    /**
     * Gets the appointment's title.
     * @return A string representing the appointment's title.
     */
    public String getTitle() {
        return title.get();
    }

    /**
     * Sets the appointment's type.
     * @param type A string containing the appointment's type.
     */
    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }
    /**
     * Gets the appointment's type.
     * @return A string representing the appointment's type.
     */
    public String getType() {
        return type.get();
    }

    /**
     * Sets the appointment's description.
     * @param description A string containing the appointment's description.
     */
    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }
    /**
     * Gets the appointment's description.
     * @return A string representing the appointment's description.
     */
    public String getDescription() {
        return description.get();
    }

    /**
     * Sets the appointment's date.
     * @param date A string containing the appointment's date.
     */
    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);
    }
    /**
     * Gets the appointment's date.
     * @return A string representing the appointment's date.
     */
    public String getDate() {
        return date.get();
    }

    /**
     * Sets the appointment's start time.
     * @param startTime A string containing the appointment's start time.
     */
    public void setStartTime(String startTime) {
        this.startTime = new SimpleStringProperty(startTime);
    }
    /**
     * Gets the appointment's start time.
     * @return A string representing the appointment's start time.
     */
    public String getStartTime() {
        return startTime.get();
    }

    /**
     * Sets the appointment's end time.
     * @param endTime A string containing the appointment's end time.
     */
    public void setEndTime(String endTime) {
        this.endTime = new SimpleStringProperty(endTime);
    }
    /**
     * Gets the appointment's end time.
     * @return A string representing the appointment's end time.
     */
    public String getEndTime() {
        return endTime.get();
    }

    /**
     * Sets the customer's ID.
     * @param customerID An integer containing the customer's ID.
     */
    public void setCustomerID(int customerID) {
        this.customerID = new SimpleIntegerProperty(customerID);
    }
    /**
     * Gets the customer's ID.
     * @return An integer representing the customer's ID.
     */
    public int getCustomerID() {
        return customerID.get();
    }
}

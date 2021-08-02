/*
 * Program: Appointment.java
 * Author: Davis Nguyen
 * Description: Appointment class used to model an appointment object.
 */

package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * This class represents an appointment.
 *
 * @author Davis Nguyen
 */
public class Appointment {

    //instantiating variables
    private SimpleIntegerProperty appointmentID, customerID, userID, contactID;
    private SimpleStringProperty title, description, location, type, createdBy, lastUpdatedBy;
    private SimpleStringProperty contactName;
    private LocalDateTime start, end, createDate;
    private Timestamp lastUpdate;

    //default constructor
    public Appointment(){}

    /**
     * Creates an appointment.
     *
     * @param appointmentID The appointment's ID.
     * @param title The appointment's title.
     * @param description The appointment's description.
     * @param location The appointment's location.
     * @param type The appointment's type.
     * @param start The appointment's start date and time.
     * @param end The appointment's end date and time.
     * @param createDate The appointment's creation date and time.
     * @param createdBy The user who created the appointment.
     * @param lastUpdate The date and time of when the appointment was last updated.
     * @param lastUpdatedBy The user who last updated the appointment.
     * @param customerID The customer's ID.
     * @param userID The user's ID.
     * @param contactID The contact's ID.
     */
    public Appointment(int appointmentID, String title, String description, String location, String type,
                       LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy, Timestamp lastUpdate,
                       String lastUpdatedBy, int customerID, int userID, int contactID){
        this.appointmentID = new SimpleIntegerProperty(appointmentID);
        this.customerID = new SimpleIntegerProperty(customerID);
        this.userID = new SimpleIntegerProperty(userID);
        this.contactID = new SimpleIntegerProperty(contactID);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.type = new SimpleStringProperty(type);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }

    /**
     * Creates an appointment.
     *
     * @param appointmentID The appointment's ID.
     * @param title The appointment's title.
     * @param description The appointment's description.
     * @param location The appointment's location.
     * @param type The appointment's type.
     * @param start The appointment's start date and time.
     * @param end The appointment's end date and time.
     * @param createDate The appointment's creation date and time.
     * @param createdBy The user who created the appointment.
     * @param lastUpdate The date and time of when the appointment was last updated.
     * @param lastUpdatedBy The user who last updated the appointment
     * @param customerID The customer's ID.
     * @param userID The user's ID.
     * @param contactID The contact's ID.
     * @param contactName The contact's name.
     */
    public Appointment(int appointmentID, String title, String description, String location, String type,
                       LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy, Timestamp lastUpdate,
                       String lastUpdatedBy, int customerID, int userID, int contactID, String contactName){
        this.appointmentID = new SimpleIntegerProperty(appointmentID);
        this.customerID = new SimpleIntegerProperty(customerID);
        this.userID = new SimpleIntegerProperty(userID);
        this.contactID = new SimpleIntegerProperty(contactID);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.type = new SimpleStringProperty(type);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.contactName = new SimpleStringProperty(contactName);
    }

    /* Setters and getters */

    /**
     * Sets the appointment's ID.
     * @param appointmentID An integer containing the appointment's ID.
     */
    public void setAppointmentID(int appointmentID){
        this.appointmentID = new SimpleIntegerProperty(appointmentID);
    }
    /**
     * Gets the appointment's ID.
     * @return An integer representing the appointment's ID.
     */
    public int getAppointmentID(){
        return appointmentID.get();
    }

    /**
     * Sets the customer's ID.
     * @param customerID An integer containing the customer's ID.
     */
    public void setCustomerID(int customerID){
        this.customerID = new SimpleIntegerProperty(customerID);
    }
    /**
     * Gets the customer's ID.
     * @return An integer representing the customer's ID.
     */
    public int getCustomerID(){
        return customerID.get();
    }

    /**
     * Sets the user's ID.
     * @param userID An integer containing the user's ID.
     */
    public void setUserID(int userID){
        this.userID = new SimpleIntegerProperty(userID);
    }
    /**
     * Gets the user's ID.
     * @return An integer representing the user's ID.
     */
    public int getUserID(){
        return userID.get();
    }

    /**
     * Sets the contact's ID.
     * @param contactID An integer containing the contact's ID.
     */
    public void setContactID(int contactID){
        this.contactID = new SimpleIntegerProperty(contactID);
    }
    /**
     * Gets the contact's ID.
     * @return An integer representing the contact's ID.
     */
    public int getContactID(){
        return contactID.get();
    }

    /**
     * Sets the appointment's title.
     * @param title A string containing the appointment's title.
     */
    public void setTitle(String title){
        this.title = new SimpleStringProperty(title);
    }
    /**
     * Gets the appointment's title.
     * @return A string representing the appointment's title.
     */
    public String getTitle(){
        return title.get();
    }

    /**
     * Sets the appointment's description.
     * @param description A string containing the appointment's description.
     */
    public void setDescription(String description){
        this.description = new SimpleStringProperty(description);
    }
    /**
     * Gets the appointment's description.
     * @return A string representing the appointment's description.
     */
    public String getDescription(){
        return description.get();
    }

    /**
     * Sets the appointment's location.
     * @param location A string containing the appointment's location.
     */
    public void setLocation(String location){
        this.location = new SimpleStringProperty(location);
    }
    /**
     * Gets the appointment's location.
     * @return A string representing the appointment's location.
     */
    public String getLocation(){
        return location.get();
    }

    /**
     * Sets the appointment's type.
     * @param type A string containing the appointment's type.
     */
    public void setType(String type){
        this.type = new SimpleStringProperty(type);
    }
    /**
     * Gets the appointment's type.
     * @return A string representing the appointment's type.
     */
    public String getType(){
        return type.get();
    }

    /**
     * Sets the user who created the appointment.
     * @param createdBy A string containing the user who created the appointment.
     */
    public void setCreatedBy(String createdBy){
        this.createdBy = new SimpleStringProperty(createdBy);
    }
    /**
     * Gets the user who created the appointment.
     * @return A string representing the user who created the appointment.
     */
    public String getCreatedBy(){
        return createdBy.get();
    }

    /**
     * Sets the user who last updated the appointment.
     * @param lastUpdatedBy A string containing the user who last updated the appointment.
     */
    public void setLastUpdatedBy(String lastUpdatedBy){
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
    }
    /**
     * Gets the user who last updated the appointment.
     * @return A string representing the user who last updated the appointment.
     */
    public String getLastUpdatedBy(){
        return lastUpdatedBy.get();
    }

    /**
     * Sets the appointment's start date and time.
     * @param start A localdatetime containing the appointment's start date and time.
     */
    public void setStart(LocalDateTime start){
        this.start = start;
    }
    /**
     * Gets the appointment's start date and time.
     * @return A localdatetime representing the appointment's start date and time.
     */
    public LocalDateTime getStart(){
        return start;
    }

    /**
     * Sets the appointment's end date and time.
     * @param end A localdatetime containing the appointment's end date and time.
     */
    public void setEnd(LocalDateTime end){
        this.end = end;
    }
    /**
     * Gets the appointment's end date and time.
     * @return A localdatetime representing the appointment's end date and time.
     */
    public LocalDateTime getEnd(){
        return end;
    }

    /**
     * Sets the date and time of when the appointment was created.
     * @param createDate A localdatetime containing the appointment's creation date and time.
     */
    public void setCreateDate(LocalDateTime createDate){
        this.createDate = createDate;
    }
    /**
     * Gets the date and time of when the appointment was created.
     * @return A localdatetime representing the appointment's creation date and time.
     */
    public LocalDateTime getCreateDate(){
        return createDate;
    }

    /**
     * Sets the date and time of when the appointment was last updated.
     * @param lastUpdate A timestamp containing the appointment's last updated time.
     */
    public void setLastUpdate(Timestamp lastUpdate){
        this.lastUpdate = lastUpdate;
    }
    /**
     * Gets the date and time of when the appointment was last updated.
     * @return A timestamp representing the appointment's last updated time.
     */
    public Timestamp getLastUpdate(){
        return lastUpdate;
    }

    /**
     * Sets the contact's name.
     * @param contactName A string containing the contact's name.
     */
    public void setContactName(String contactName){
        this.contactName = new SimpleStringProperty(contactName);
    }
    /**
     * Gets the contact's name.
     * @return A string representing the contact's name.
     */
    public String getContactName(){
        return contactName.get();
    }
}

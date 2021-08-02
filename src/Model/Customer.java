/*
 * Program: Customer.java
 * Author: Davis Nguyen
 * Description: Customer class used to model a customer object.
 */

package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class represents a customer.
 *
 * @author Davis Nguyen
 */
public class Customer {
    //instantiating variables
    private SimpleIntegerProperty customerID, divisionID, countryID;
    private SimpleStringProperty customerName, address, postal, phone, createdBy, lastUpdatedBy;
    private SimpleStringProperty division;
    private LocalDateTime createDate;
    private Timestamp lastUpdate;

    //default constructor
    public Customer() {
    }

    /**
     * Creates a customer.
     *
     * @param customerID The customer's ID.
     * @param customerName The customer's name.
     * @param address The customer's address.
     * @param postal The customer's postal code.
     * @param phone The customer's phone number
     * @param createDate The customer's creation date and time.
     * @param createdBy The user who created the customer.
     * @param lastUpdate The user who last updated the customer.
     * @param lastUpdatedBy The user who last updated the customer.
     * @param divisionID The division's ID.
     * @param division The division's name.
     * @param countryID The country's ID.
     */
    public Customer(int customerID, String customerName, String address, String postal,
                    String phone, LocalDateTime createDate, String createdBy, Timestamp lastUpdate,
                    String lastUpdatedBy, int divisionID, String division, int countryID) {
        this.customerID = new SimpleIntegerProperty(customerID);
        this.divisionID = new SimpleIntegerProperty(divisionID);
        this.customerName = new SimpleStringProperty(customerName);
        this.address = new SimpleStringProperty(address);
        this.postal = new SimpleStringProperty(postal);
        this.phone = new SimpleStringProperty(phone);
        this.createdBy = new SimpleStringProperty(createdBy);
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.division = new SimpleStringProperty(division);
        this.countryID = new SimpleIntegerProperty(countryID);
    }

    /* Setters and getters */

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

    /**
     * Sets the division's ID.
     * @param divisionID An integer containing the division's ID.
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = new SimpleIntegerProperty(divisionID);
    }
    /**
     * Gets the division's ID.
     * @return  An integer representing the division's ID.
     */
    public int getDivisionID() {
        return divisionID.get();
    }

    /**
     * Sets the customer's name.
     * @param customerName A string containing the customer's name.
     */
    public void setCustomerName(String customerName) {
        this.customerName = new SimpleStringProperty(customerName);
    }
    /**
     * Gets the customer's name.
     * @return  A string representing the customer's name.
     */
    public String getCustomerName() {
        return customerName.get();
    }

    /**
     * Sets the customer's address.
     * @param address A string containing the customer's address.
     */
    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }
    /**
     * Gets the customer's address.
     * @return  A string representing the customer's address.
     */
    public String getAddress() {
        return address.get();
    }

    /**
     * Sets the customer's postal code.
     * @param postal A string containing the customer's postal code.
     */
    public void setPostal(String postal) {
        this.postal = new SimpleStringProperty(postal);
    }
    /**
     * Gets the customer's postal code.
     * @return A string containing the customer's postal code.
     */
    public String getPostal() {
        return postal.get();
    }

    /**
     * Sets the customer's phone number.
     * @param phone A string containing the customer's phone number.
     */
    public void setPhone(String phone) {
        this.phone = new SimpleStringProperty(phone);
    }
    /**
     * Gets the customer's phone number.
     * @return A string representing the customer's phone number.
     */
    public String getPhone() {
        return phone.get();
    }

    /**
     * Sets the user who created the customer.
     * @param createdBy A string containing the user who created the customer.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = new SimpleStringProperty(createdBy);
    }
    /**
     * Gets the user who created the customer.
     * @return A string representing the user who created the customer.
     */
    public String getCreatedBy() {
        return createdBy.get();
    }

    /**
     * Sets the user who last updated the customer.
     * @param lastUpdatedBy A string containing the user who last updated the customer.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = new SimpleStringProperty(lastUpdatedBy);
    }
    /**
     * Gets the user who last updated the customer.
     * @return A string representing the user who last updated the customer.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy.get();
    }

    /**
     * Sets the date and time of when the customer was created.
     * @param createDate A localdatetime containing the customer's creation date and time.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    /**
     * Gets the date and time of when the customer was created.
     * @return A localdatetime representing the customer's creation date and time.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets the date and time of when the customer was last updated.
     * @param lastUpdate A timestamp containing the customer's last updated time.
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    /**
     * Gets the date and time of when the customer was last updated.
     * @return A timestamp representing the customer's last updated time.
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the division's ID.
     * @param division A string containing the division's ID.
     */
    public void setDivision(String division) {
        this.division = new SimpleStringProperty(division);
    }
    /**
     * Gets the division's ID.
     * @return A string representing the division's ID.
     */
    public String getDivision() {
        return division.get();
    }

    /**
     * Sets the country's ID.
     * @param countryID An integer containing the country's ID.
     */
    public void setCountryID(int countryID) {
        this.countryID = new SimpleIntegerProperty(countryID);
    }
    /**
     * Gets the country's ID.
     * @return A string representing the country's ID.
     */
    public int getCountryID() {
        return countryID.get();
    }
}

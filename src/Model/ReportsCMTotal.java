/*
 * Program: ReportsCMTotal.java
 * Author: Davis Nguyen
 * Description: ReportsCMTotal class used to model the total number of appointments for each contact by month.
 */

package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class represents the total number of appointments for each contacts by month.
 *
 * @author Davis Nguyen
 */
public class ReportsCMTotal {
    //instantiating variables
    private SimpleStringProperty contactName, month;
    private SimpleIntegerProperty total;

    //default constructor
    public ReportsCMTotal() {
    }

    /**
     * Creates a report of the total number of appointments by month for each contact.
     *
     * @param contactName The contact's name.
     * @param month The month.
     * @param total The total number of appointments.
     */
    public ReportsCMTotal(String contactName, String month, int total) {
        this.contactName = new SimpleStringProperty(contactName);
        this.month = new SimpleStringProperty(month);
        this.total = new SimpleIntegerProperty(total);
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
     * @return  A string representing the contact's name.
     */
    public String getContactName() {
        return contactName.get();
    }

    /**
     * Sets the month.
     * @param month A string containing the month.
     */
    public void setMonth(String month) {
        this.month = new SimpleStringProperty(month);
    }
    /**
     * Gets the month.
     * @return  A string representing the month.
     */
    public String getMonth() {
        return month.get();
    }

    /**
     * Sets the total number of appointments.
     * @param total An integer containing the total number of appointments.
     */
    public void setTotal(int total) {
        this.total = new SimpleIntegerProperty(total);
    }
    /**
     * Gets the total number of appointments.
     * @return  An integer representing the total number of appointments.
     */
    public int getTotal() {
        return total.get();
    }
}

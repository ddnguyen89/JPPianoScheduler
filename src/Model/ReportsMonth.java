/*
 * Program: ReportsMonth.java
 * Author: Davis Nguyen
 * Description: ReportsMonth class used to model the total number of appointments by type for each month.
 */

package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class represents the total number of appointments by type for each month.
 *
 * @author Davis Nguyen
 */
public class ReportsMonth {
    //instantiating variables
    private SimpleStringProperty month, type;
    private SimpleIntegerProperty total;

    //default constructor
    public ReportsMonth(){}

    /**
     * Creates a report of the total number of appointments by type for each month.
     *
     * @param month The month.
     * @param type The appointment type.
     * @param total The total number of appointments.
     */
    public ReportsMonth(String month, String type, int total){
        this.month = new SimpleStringProperty(month);
        this.type = new SimpleStringProperty(type);
        this.total = new SimpleIntegerProperty(total);
    }

    /* Setters and getters */

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
        return  month.get();
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
        return  type.get();
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
        return  total.get();
    }
}

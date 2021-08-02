/*
 * Program: List.java
 * Author: Davis Nguyen
 * Description: List class used to store ObservableLists.
 */

package Resources;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.text.DecimalFormat;

/**
 * This class stores all ObservableList.
 *
 * @author Davis Nguyen
 */
public class List {

    /** List of countries. */
    public static ObservableList<String> countries = FXCollections.observableArrayList(
            "U.S", "UK", "Canada");

    /** List of US cities. */
    public static ObservableList<String> usCities = FXCollections.observableArrayList(
            "Alabama", "Arizona", "Arkansas", "California", "Colorado",
            "Connecticut", "Delaware", "District of Columbia", "Florida", "Georgia",
            "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
            "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
            "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
            "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
            "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
            "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia",
            "Washington", "West Virginia", "Wisconsin", "Wyoming", "Hawaii", "Alaska");

    /** List of UK cities. */
    public static ObservableList<String> ukCities = FXCollections.observableArrayList(
            "England", "Wales", "Scotland", "Northern Ireland");

    /** List of Canada provinces. */
    public static ObservableList<String> canadaProvinces = FXCollections.observableArrayList(
            "Northwest Territories", "Alberta", "British Columbia",
            "Manitoba", "New Brunswick", "Nova Scotia", "Prince Edward Island",
            "Ontario", "Qu├®bec", "Saskatchewan", "Nunavut", "Yukon",
            "Newfoundland and Labrador");

    /** List of appointment types. */
    public static ObservableList<String> types = FXCollections.observableArrayList(
            "Planning Session", "De-Briefing");

    /** List of hours. */
    public static ObservableList<String> hours = FXCollections.observableArrayList(hours());

    /** List of minutes. */
    public static ObservableList<String> minutes = FXCollections.observableArrayList(minutes());

    /**
     * Creates an ObservableList of hours.d
     *
     * @return A string containing list of hours 0 through 23.
     */
    public static ObservableList<String> hours(){
        DecimalFormat df = new DecimalFormat("00");
        ObservableList<String> list = FXCollections.observableArrayList();
        for(int i=0; i <24; i++){
            list.add(df.format(i));
        }
        //return string list
        return list;
    }

    /**
     * Creates an ObservableList of minutes.
     *
     * @return A string containing the list of minutes 0 through 59.
     */
    public static ObservableList<String> minutes(){
        DecimalFormat df = new DecimalFormat("00");
        ObservableList<String> list = FXCollections.observableArrayList();
        for(int i=0; i <60; i++){
            list.add(df.format(i));
        }
        //return string list
        return list;
    }
}

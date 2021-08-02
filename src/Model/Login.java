/*
 * Program: Login.java
 * Author: Davis Nguyen
 * Description: Login class used to model a login object.
 */

package Model;

/**
 * This class represents a login.
 *
 * @author Davis Nguyen
 */
public class Login {
    //instantiating variables
    private static String username;

    //default constructor
    public Login() {
    }

    /**
     * Creates a login.
     *
     * @param username The user's username.
     */
    public Login(String username) {
        this.username = username;
    }

    /* Setters and getters */

    /**
     * Sets the user's username.
     * @param username A string containing the user's username.
     */
    public static void setUsername(String username) {
        Login.username = username;
    }
    /**
     * Gets the user's username.
     * @return A string representing the user's username.
     */
    public static String getUsername() {
        return username;
    }
}

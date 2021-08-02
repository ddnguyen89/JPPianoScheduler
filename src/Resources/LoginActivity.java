/*
 * Program: LoginActivity.java
 * Author: Davis Nguyen
 * Description: LoginActivity class used to track user login attempts and timestamp and append to login_activity.txt.
 */

package Resources;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class tracks login attempts.
 * Appends activity to textfile login_activity.
 *
 * @author Davis Nguyen
 */
public class LoginActivity {

    LoginActivity(){}

    /**
     * Creates timestamp and user login attempt and appends results to textfile.
     *
     * @param user A string containing the username.
     * @param attempt Reference from database to determine if attempt is successful.
     * @throws IOException If an input or output exception occurred.
     */
    public static void track(String user, String attempt) throws IOException {
        //file location and store localdatetime
        String fileLocation = "./src/login_activity.txt";
        LocalDateTime localDateTime = LocalDateTime.now();

        //create and add login activity to textfile
        FileWriter fileWriter = new FileWriter(fileLocation, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(user + " - " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " - Attempt: " +  attempt);
        printWriter.close();
    }
}

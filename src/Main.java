/*
 * Program: Main.java
 * Author: Davis Nguyen
 * Description: Main class used to load LoginView screen and create connection to database.
 */

import Utilities.DBConnection;
import Utilities.DBQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class creates an app that displays the Login View Screen.
 *
 * @author Davis Nguyen
 */
public class Main extends Application {

    /**
     * Loads and displays LoginView FXML.
     * Set new scene, LoginView, as primary stage.
     *
     * @param primaryStage Primary window of application.
     * @throws IOException If an input or output exception occurred.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/LoginView.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Create connection to database.
     *
     * @param args Command-line arguments.
     * @throws SQLException If a SQL exception occurred.
     */
    public static void main(String[] args) throws SQLException {
        //create connection
        Connection conn = DBConnection.startConnection();

        //create statement object
        DBQuery.setStatement(conn);
        launch(args);

        //close connection
        DBConnection.closeConnection();
    }
}

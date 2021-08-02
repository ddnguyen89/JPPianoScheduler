/*
 * Program: ChangeScene.java
 * Author: Davis Nguyen
 * Description: ChangeScene class used to call different scenes.
 */

package Resources;

import Controller.EditAppointmentController;
import Controller.EditCustomerController;
import Model.Appointment;
import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

/**
 * This class loads FXML views and changes scenes.
 *
 * @author Davis Nguyen
 */
public class ChangeScene {
    private static Parent root;
    private static Stage stage;
    private static FXMLLoader loader;

    /**
     * Loads and displays MainView FXML.
     * Set new scene MainView. Grabs current stage window and closes it.
     * Set new stage with new scene and display.
     *
     * @param actionEvent An instance of an ActionEvent.
     * @throws IOException If an input or output exception occurred.
     */
    public static void getMainScene(ActionEvent actionEvent) throws IOException {
        //load MainView.fxml
        root = FXMLLoader.load(ChangeScene.class.getResource("/View/MainView.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        //close current stage and open new stage
        stage.close();
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Loads and displays AddAppointmentView FXML.
     * Set new scene AddAppointmentView. Grabs current stage window and closes it.
     * Set new stage with new scene and display.
     *
     * @param actionEvent An instance of an ActionEvent.
     * @throws IOException If an input or output exception occurred.
     */
    public static void getAddAppointmentScene(javafx.event.ActionEvent actionEvent) throws IOException {
        //load AddAppointmentView.fxml
        root = FXMLLoader.load(ChangeScene.class.getResource("/View/AddAppointmentView.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        //close current stage and open new stage
        stage.close();
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Loads and displays EditAppointmentView FXML.
     * Set new scene EditAppointmentView. Grab and store customer model.
     * Set new stage with new scene, pass customer model, and display.
     *
     * @param actionEvent An instance of an ActionEvent.
     * @param appointment A model of an appointment object.
     * @throws IOException If an input or output exception occurred.
     * @throws SQLException If a SQL exception occurred.
     */
    public static void getEditAppointmentScene(javafx.event.ActionEvent actionEvent, Appointment appointment) throws IOException, SQLException {
        //load EditAppointmentView.fxml
        loader = new FXMLLoader(ChangeScene.class.getResource("/View/EditAppointmentView.fxml"));
        Parent root = loader.load();

        //grab and store Appointment Model
        EditAppointmentController editAppointmentController = loader.getController();
        editAppointmentController.setAppointment(appointment);

        //change current stage to new scene
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    /**
     * Loads and displays AddCustomerView FXML.
     * Set new scene AddCustomerView. Grabs current stage window and closes it.
     * Set new stage with new scene and display.
     *
     * @param actionEvent An instance of an ActionEvent.
     * @throws IOException If an input or output exception occurred.
     */
    public static void getAddCustomerScene(javafx.event.ActionEvent actionEvent) throws IOException {
        //load AddCustomerView.fxml
        root = FXMLLoader.load(ChangeScene.class.getResource("/View/AddCustomerView.fxml"));
        Scene scene = new Scene(root);
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        //close current stage and open new stage
        stage.close();
        stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Loads and displays EditCustomerView FXML.
     * Set new scene EditCustomerView. Grab and store customer model.
     * Set new stage with new scene, pass customer model, and display.
     *
     * @param actionEvent An instance of an ActionEvent.
     * @param customer A model of a customer object.
     * @throws IOException If an input or output exception occurred.
     * @throws SQLException If a SQL exception occurred.
     */
    public static void getEditCustomerScene(javafx.event.ActionEvent actionEvent, Customer customer) throws IOException, SQLException {
        //load EditCustomerView.fxml
        loader = new FXMLLoader(ChangeScene.class.getResource("/View/EditCustomerView.fxml"));
        Parent root = loader.load();

        //grab and store Customer Model
        EditCustomerController editCustomerController = loader.getController();
        editCustomerController.setCustomer(customer);

        //change current stage to new scene
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}

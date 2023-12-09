// Code corrected with JavaFX on 12/8/23
// Pierce Mohney
// SDEV 200
// M06 assignment 1
// This program will prompt a user to enter in a new employee along with their respected credientals, then add the information into a connected database. 
// IMPORTANT run MySQL statements for database to work

package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StaffInformationSystem extends Application {

    private TextField idField, lastNameField, firstNameField, miField, addressField, cityField,
            stateField, telephoneField, emailField;
    //User input fields

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Staff Information System");
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(300);

        GridPane inputGridPane = createInputGridPane();
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> saveToDatabase());

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(inputGridPane, submitButton);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    //Creates window and adds submit button

    private GridPane createInputGridPane() {
        GridPane inputGridPane = new GridPane();
        inputGridPane.setHgap(10);
        inputGridPane.setVgap(5);

        inputGridPane.addRow(0, new Label("ID:"), idField = new TextField());
        inputGridPane.addRow(1, new Label("Last Name:"), lastNameField = new TextField());
        inputGridPane.addRow(2, new Label("First Name:"), firstNameField = new TextField());
        inputGridPane.addRow(3, new Label("Middle Init:"), miField = new TextField());
        inputGridPane.addRow(4, new Label("Address:"), addressField = new TextField());
        inputGridPane.addRow(5, new Label("City:"), cityField = new TextField());
        inputGridPane.addRow(6, new Label("State:"), stateField = new TextField());
        inputGridPane.addRow(7, new Label("Telephone:"), telephoneField = new TextField());
        inputGridPane.addRow(8, new Label("Email:"), emailField = new TextField());

        return inputGridPane;
    }
    //Labels for each field

    private void saveToDatabase() {
        String id = idField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String mi = miField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String telephone = telephoneField.getText();
        String email = emailField.getText();
        //Saves user input to MySQL database

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/staffrecords", "scott", "tiger");
            Statement statement = connection.createStatement();

            String insertQuery = String.format("INSERT INTO Staff VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    id, lastName, firstName, mi, address, city, state, telephone, email);
            //Methods to connect to database and insert data

            statement.executeUpdate(insertQuery);

            showAlert("Information saved");

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            showAlert("Error");
        }
    }
    //Handles error

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

// 12/1/23
// Pierce Mohney
// SDEV200
// This program will prompt a user to enter in a new employee along with their respected credientals, then add the information into a connected database. 
// IMPORTANT run MySQL statements for database to work

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StaffInformationSystem extends JFrame {
    private JTextField idField, lastNameField, firstNameField, miField, addressField, cityField,
            stateField, telephoneField, emailField;

    public StaffInformationSystem() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Staff Information System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToDatabase();
            }
        });
        add(submitButton, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }
//Creates window to add in new field along with methods and a submit button

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(10, 2));

        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        inputPanel.add(lastNameField);

        inputPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Middle Init:"));
        miField = new JTextField();
        inputPanel.add(miField);

        inputPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        inputPanel.add(addressField);

        inputPanel.add(new JLabel("City:"));
        cityField = new JTextField();
        inputPanel.add(cityField);

        inputPanel.add(new JLabel("State:"));
        stateField = new JTextField();
        inputPanel.add(stateField);

        inputPanel.add(new JLabel("Telephone:"));
        telephoneField = new JTextField();
        inputPanel.add(telephoneField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);
//Creates different text fields in window along with labels 
        
        return inputPanel;
    }

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

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/staffrecords", "scott", "tiger");
            Statement statement = connection.createStatement();

            String insertQuery = String.format("INSERT INTO Staff VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    id, lastName, firstName, mi, address, city, state, telephone, email);

            statement.executeUpdate(insertQuery);

            JOptionPane.showMessageDialog(this, "Information saved");

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error");
        }
    }
    //Saves values into database along with error handling 

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StaffInformationSystem staffInformationSystem = new StaffInformationSystem();
            staffInformationSystem.setVisible(true);
        });
    }
}

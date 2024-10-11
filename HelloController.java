package com.example.lab1divyajot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
public class HelloController implements Initializable {
    @FXML
    private TableView<DoctorAppt> tableView;
    @FXML
    private TableColumn<DoctorAppt, Integer> Id;
    @FXML
    private TableColumn<DoctorAppt, String> Name;
    @FXML
    private TableColumn<DoctorAppt, String> Doctor;
    @FXML
    private TableColumn<DoctorAppt, String> Mail;
    ObservableList<DoctorAppt> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Id.setCellValueFactory(new
                PropertyValueFactory<DoctorAppt, Integer>("Id"));
        Name.setCellValueFactory(new
                PropertyValueFactory<DoctorAppt, String>("Name"));
        Doctor.setCellValueFactory(new
                PropertyValueFactory<DoctorAppt, String>("Doctor"));
        Mail.setCellValueFactory(new
                PropertyValueFactory<DoctorAppt, String>("Mail"));
        tableView.setItems(list);
    }

    @FXML
    protected void onHelloButtonClick() {
        populateTable();
    }

    public void populateTable() {
        // Establish a database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/lab1divyajot";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM doctor";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int Id = resultSet.getInt("Id");
                String Name = resultSet.getString("Name");
                String Doctor = resultSet.getString("Doctor");
                String Mail = resultSet.getString("Mail");
                tableView.getItems().add(new DoctorAppt(Id, Name, Doctor,
                        Mail));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

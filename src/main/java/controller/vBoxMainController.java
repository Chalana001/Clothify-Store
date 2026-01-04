package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class vBoxMainController implements Initializable {

    @FXML
    private StackPane contentPane;

    private void loadView(String fxml) {
        try {
            Parent view = FXMLLoader.load(
                    getClass().getResource("/view/" + fxml)
            );
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openDashboard() {
        loadView("dashboard.fxml");
    }

    @FXML
    private void openPlaceOrder() {
        loadView("PlaceOrder.fxml");
    }

    @FXML
    private void openProducts() {
        loadView("products.fxml");
    }

    @FXML
    private void openInventory() {
        loadView("inventory.fxml");
    }

    @FXML
    private void openSuppliers() {
        loadView("suppliers.fxml");
    }

    @FXML
    private void openEmployees() {
        loadView("employees.fxml");
    }

    @FXML
    private void openCustomers() {
        loadView("customers.fxml");
    }

    @FXML
    private void openReports() {
        loadView("reports.fxml");
    }

    @FXML
    private void logout() {
        System.out.println("Logout clicked");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadView("dashboard.fxml");
    }
}

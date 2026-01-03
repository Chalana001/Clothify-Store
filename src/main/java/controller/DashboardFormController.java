package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import service.DashboardService;
import service.Impl.DashboardServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    private DashboardService dashboardService = new DashboardServiceImpl();

    @FXML
    private Label lblLowStock;

    @FXML
    private Label lblLowStockAlert1;

    @FXML
    private Label lblLowStockAlert2;

    @FXML
    private Label lblLowStockAlert3;

    @FXML
    private Label lblRecentOrder1;

    @FXML
    private Label lblRecentOrder2;

    @FXML
    private Label lblRecentOrder3;

    @FXML
    private Label lblTotalCustomer;

    @FXML
    private Label lblTotalProducts;

    @FXML
    private Label lblTotalRevenue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTotalProducts();
        loadTotalRevenue();
        loadLowStockItemCount();
        loadTotalCustomers();
        loadLowStockAlerts();

    }

    private void loadLowStockAlerts() {
        List<String> lowStockAlerts = dashboardService.getLowStockAlerts();
        try {
            lblLowStockAlert1.setText(lowStockAlerts.get(0));
            lblLowStockAlert2.setText(lowStockAlerts.get(1));
            lblLowStockAlert3.setText(lowStockAlerts.get(2));
        } catch (IndexOutOfBoundsException e) {

        }
    }

    private void loadTotalCustomers() {
        Integer totalCustomers = dashboardService.getTotalCustomers();
        lblTotalCustomer.setText(totalCustomers+" +");
    }

    private void loadLowStockItemCount() {
        Integer LowStockItemCount = dashboardService.getLowStockItemCount();
        lblLowStock.setText(LowStockItemCount+"");
    }

    private void loadTotalRevenue() {
        Integer totalRevenue = dashboardService.getTotalRevenue();
        lblTotalRevenue.setText(totalRevenue+" +");
    }

    private void loadTotalProducts() {
        Integer totalProducts = dashboardService.getTotalProducts();
        lblTotalProducts.setText(totalProducts+" +");
    }
}

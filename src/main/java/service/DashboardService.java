package service;

import java.util.List;

public interface DashboardService {
    Integer getTotalCustomers();

    Integer getLowStockItemCount();

    Integer getTotalRevenue();

    Integer getTotalProducts();

    List<String> getLowStockAlerts();
}

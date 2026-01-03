package service.Impl;

import Repository.CustomerRepository;
import Repository.Impl.CustomerRepositoryImpl;
import javafx.collections.ObservableList;
import modle.dto.Customer;
import modle.dto.Product;
import modle.dto.TblInventory;
import service.*;

import java.util.ArrayList;
import java.util.List;

public class DashboardServiceImpl implements DashboardService {

    private CustomerService customerService = new CustomerServiceImpl();
    private InventoryService inventoryService = new InventoryServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    ObservableList<TblInventory> inventoryDetails;

    @Override
    public Integer getTotalCustomers() {
        ObservableList<Customer> allCustomers = customerService.getAllCustomers();
        return allCustomers.size();
    }

    @Override
    public Integer getLowStockItemCount() {
        inventoryDetails = inventoryService.getInventoryDetails();
        int count = 0;

        for (TblInventory inventory: inventoryDetails){
            if (inventory.getAvailibleQty()<5){
                count++;
            }
        }
        return count;
    }

    @Override
    public Integer getTotalRevenue() {
        return 0;
    }

    @Override
    public Integer getTotalProducts() {
        ObservableList<Product> allProducts = productService.getAllProducts();
        return allProducts.size();
    }

    @Override
    public List<String> getLowStockAlerts() {
        List<String> lowStocks = new ArrayList<>();
        inventoryDetails = inventoryService.getInventoryDetails();

        for (TblInventory inventory: inventoryDetails){
            if (inventory.getAvailibleQty()<5){
                lowStocks.add(inventory.getProductName()+"  - " + inventory.getStatus());
            }
        }
        return lowStocks;
    }
}

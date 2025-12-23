package service;

import javafx.collections.ObservableList;
import modle.dto.Customer;

import java.util.List;

public interface CustomerService {
    ObservableList<Customer> getAllCustomers();

    String getNewCustomerId();

    void addCustomer(Customer customer);

    Customer getCustomerById(String text);

    ObservableList<Customer> searchCustomerByNameSearch(String newText);

    void updateCustomer(Customer selectedCustomer);

    void deleteCustomer(Customer selectedCustomer);
}

package service.Impl;

import Repository.CustomerRepository;
import Repository.Impl.CustomerRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modle.dto.Customer;
import modle.entity.CustomerEntity;
import service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private List<CustomerEntity> customerEntityList = null;
    private ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
    private ObservableList<Customer> customerObservableListForSearchCustomer = FXCollections.observableArrayList();

    @Override
    public ObservableList<Customer> getAllCustomers() {

        customerObservableList.clear();
        customerEntityList = customerRepository.getAllCustomer();

        System.out.println(customerEntityList.size()+ "Service called");

        for (CustomerEntity customerEntity: customerEntityList){
            customerObservableList.add(new Customer(
                    customerEntity.getCustomerId(),
                    customerEntity.getName(),
                    customerEntity.getPhoneNumber(),
                    customerEntity.getEmail()
            ));
        }
        return customerObservableList;
    }

    @Override
    public String getNewCustomerId() {
        CustomerEntity lastCustomer = customerRepository.getLastCustomerId();
        if(lastCustomer==null) {
            return "C001";
        }
        String lastCusId = lastCustomer.getCustomerId();
        int number = Integer.parseInt(lastCusId.split("C")[1]); //1
        number++;
        return String.format("C%03d", number);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.addCustomer(new CustomerEntity(
                customer.getId(),
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getEmail()
        ));
    }

    @Override
    public Customer getCustomerById(String text) throws RuntimeException {
        CustomerEntity customerEntity = customerRepository.getCustomerById(text);
        return new Customer(
                customerEntity.getCustomerId(),
                customerEntity.getName(),
                customerEntity.getPhoneNumber(),
                customerEntity.getEmail()
        );
    }

    @Override
    public ObservableList<Customer> searchCustomerByNameSearch(String newText) throws RuntimeException {
        if (!customerObservableListForSearchCustomer.isEmpty()){customerObservableListForSearchCustomer.clear();}

        if(newText == null || newText.trim().length()<2){
            return null;
        }

        customerEntityList = customerRepository.searchCustomerByNameSearch(newText);
        customerObservableListForSearchCustomer.clear();
        for (CustomerEntity customerEntity: customerEntityList){
            customerObservableListForSearchCustomer.add(new Customer(
                    customerEntity.getCustomerId(),
                    customerEntity.getName(),
                    customerEntity.getPhoneNumber(),
                    customerEntity.getEmail()
            ));
        }
        return customerObservableListForSearchCustomer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (customer == null){
            return;
        }
        customerRepository.updateCustomer(new CustomerEntity(
                customer.getId(),
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getEmail()
        ));
    }

    @Override
    public void deleteCustomer(Customer selectedCustomer) {
        if (selectedCustomer == null){
            return;
        }
        customerRepository.deleteCustomer(selectedCustomer.getId());
    }
}

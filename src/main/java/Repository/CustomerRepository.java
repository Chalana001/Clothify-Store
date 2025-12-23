package Repository;

import modle.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepository {
    List<CustomerEntity> getAllCustomer();

    CustomerEntity getLastCustomerId();

    void addCustomer(CustomerEntity customerEntity);

    CustomerEntity getCustomerById(String text);

    List<CustomerEntity> searchCustomerByNameSearch(String newText);

    void updateCustomer(CustomerEntity customerEntity);

    void deleteCustomer(String id);
}

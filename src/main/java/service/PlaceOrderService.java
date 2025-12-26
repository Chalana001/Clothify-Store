package service;

import javafx.collections.ObservableList;
import modle.dto.CartProducts;
import modle.dto.Customer;
import modle.dto.Orders;
import modle.dto.Product;

public interface PlaceOrderService {
    Product getProductById(String text);

    ObservableList<Product> searchProductByName(String newText);

    ObservableList<Customer> getAllCustomerIds();

    String genOrderId();

    void placeOrder(Orders orders, ObservableList<CartProducts> cartProducts);
}

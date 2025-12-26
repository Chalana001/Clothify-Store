package service;

import javafx.collections.ObservableList;
import modle.dto.CartProducts;
import modle.dto.Orders;

public interface OrderDetailsService {
    void addOrderDetails(Orders orders, ObservableList<CartProducts> cartProducts);
}

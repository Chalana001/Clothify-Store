package service;

import modle.dto.Orders;

public interface OrderService {
    String getLastOrderId();

    void addOrder(Orders orders);
}

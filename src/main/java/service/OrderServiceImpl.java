package service;

import Repository.OrderRepository;
import Repository.OrderRepositoryImpl;
import modle.dto.Orders;
import modle.entity.OrdersEntity;

public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public String getLastOrderId() {
        return orderRepository.getLastOrder().getOrderId();
    }

    @Override
    public void addOrder(Orders orders) {
        orderRepository.addOrder(new OrdersEntity(
                orders.getOrderId(),
                orders.getCustomerId(),
                orders.getOrderDate()
        ));
    }
}

package service.Impl;

import Repository.OrderRepository;
import Repository.Impl.OrderRepositoryImpl;
import modle.dto.Orders;
import modle.entity.OrdersEntity;
import service.OrderService;

public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public String getLastOrderId() {
        OrdersEntity orders = orderRepository.getLastOrder();
        if (orders!=null){
            return String.valueOf(orders.getOrderId());
        }
        return null;
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

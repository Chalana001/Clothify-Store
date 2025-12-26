package service;

import Repository.OrderDetailsRepository;
import Repository.OrderDetailsRepositoryImpl;
import javafx.collections.ObservableList;
import modle.dto.CartProducts;
import modle.dto.OrderDetails;
import modle.dto.Orders;
import modle.entity.OrderDetailsEntity;

public class OrderDetailsServiceImpl implements OrderDetailsService{

    OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepositoryImpl();

    @Override
    public void addOrderDetails(Orders orders, ObservableList<CartProducts> cartProducts) {
        for (CartProducts cartProducts1: cartProducts){
            orderDetailsRepository.addOrderDetails(new OrderDetailsEntity(
                    orders.getOrderId(),
                    cartProducts1.getPId(),
                    cartProducts1.getPQty()
            ));
        }
    }
}

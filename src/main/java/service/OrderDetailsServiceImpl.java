package service;

import Repository.OrderDetailsRepository;
import Repository.OrderDetailsRepositoryImpl;
import javafx.collections.ObservableList;
import modle.dto.CartProducts;
import modle.dto.OrderDetails;
import modle.dto.Orders;
import modle.entity.OrderDetailsEntity;
import modle.pk.OrderDetailsId;

public class OrderDetailsServiceImpl implements OrderDetailsService{

    OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepositoryImpl();

    @Override
    public void addOrderDetails(Orders orders, ObservableList<CartProducts> cartProducts) {

        for (CartProducts cartProducts1: cartProducts){
            OrderDetailsId orderDetailsId = new OrderDetailsId(orders.getOrderId(),cartProducts1.getPId());
            orderDetailsRepository.addOrderDetails(new OrderDetailsEntity(
                    orderDetailsId,
                    cartProducts1.getPQty()
            ));
        }
    }
}

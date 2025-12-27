package service.Impl;

import Repository.OrderDetailsRepository;
import Repository.Impl.OrderDetailsRepositoryImpl;
import javafx.collections.ObservableList;
import modle.dto.CartProducts;
import modle.dto.Orders;
import modle.entity.OrderDetailsEntity;
import modle.pk.OrderDetailsId;
import service.OrderDetailsService;

public class OrderDetailsServiceImpl implements OrderDetailsService {

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

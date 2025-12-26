package Repository;

import modle.entity.OrdersEntity;

public interface OrderRepository {
    void addOrder(OrdersEntity ordersEntity);

    OrdersEntity getLastOrder();
}

package Repository;

import modle.dto.OrderDetails;
import modle.entity.OrderDetailsEntity;

public interface OrderDetailsRepository {
    void addOrderDetails(OrderDetailsEntity orderDetailsEntity);
}

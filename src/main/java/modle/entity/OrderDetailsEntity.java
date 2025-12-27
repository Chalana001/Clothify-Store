package modle.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import modle.pk.OrderDetailsId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "OrderDetails")
public class OrderDetailsEntity {

    @EmbeddedId
    private OrderDetailsId id;
    private int itemQty;
}

package modle.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailsEntity {
    private String orderId;
    private String itemCode;
    private int itemQty;
}

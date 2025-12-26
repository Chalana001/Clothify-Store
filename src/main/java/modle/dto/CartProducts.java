package modle.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CartProducts {
    private String pId;
    private String pName;
    private int pQty;
    private double price;
    private double total;
}

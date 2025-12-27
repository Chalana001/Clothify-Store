package modle.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TblInventory {
    private String productName;
    private int availibleQty;
    private String status;

    public TblInventory(String productName, int availibleQty) {
        this.productName = productName;
        this.availibleQty = availibleQty;
        this.status = calcStatus(availibleQty);
    }

    private String calcStatus(int availibleQty) {
        return availibleQty == 0 ? "OUT"
                : availibleQty <= 5 ? "LOW"
                : "OK";
    }
}

package modle.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Product {
    private String PId;
    private String PName;
    private String catagory;
    private String size;
    private double price;
    private Integer AvailibleQty;
    private String Sid;
}
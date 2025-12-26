package modle.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "Products")
public class ProductEntity {
    @Id
    private String PId;
    private String PName;
    private String catagory;
    private String size;
    private double price;
    private Integer AvailibleQty;
    private String Sid;
}
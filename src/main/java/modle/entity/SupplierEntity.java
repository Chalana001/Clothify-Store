package modle.entity;

import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Suppliers")
public class SupplierEntity {
    private String SId;
    private String SName;
    private Integer SPhoneNumber;
    private String SEmail;
}

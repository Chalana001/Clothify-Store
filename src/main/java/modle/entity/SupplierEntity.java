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
@Table(name = "Suppliers")
public class SupplierEntity {
    @Id
    private String SId;
    private String SName;
    private Integer SPhoneNumber;
    private String SEmail;
}

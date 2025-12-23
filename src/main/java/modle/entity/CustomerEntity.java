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

@Table(name = "Customers")
public class CustomerEntity {
    @Id
    private String customerId;
    private String name;
    private int phoneNumber;
    private String email;
}

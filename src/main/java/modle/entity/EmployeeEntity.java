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
@Table(name = "Employees")
public class EmployeeEntity {
    @Id
    private String EId;
    private String EName;
    private String ERole;
    private Integer EPhoneNumber;
}
package modle.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private String id;
    private String name;
    private Integer phoneNumber;
    private String email;
}

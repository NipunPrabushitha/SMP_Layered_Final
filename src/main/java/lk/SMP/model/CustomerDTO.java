package lk.SMP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDTO {
    private String customerId;
    private String name;
    private String contactNumber;
    private String address;
}

package lk.SMP.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderDTO {
    private OrderDTO orderDTO;
    private List<OrderDetailDTO> odList;
}

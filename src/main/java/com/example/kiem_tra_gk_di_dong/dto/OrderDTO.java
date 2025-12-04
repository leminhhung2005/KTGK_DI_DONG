
package com.example.kiem_tra_gk_di_dong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private List<OrderItemDTO> items;
}


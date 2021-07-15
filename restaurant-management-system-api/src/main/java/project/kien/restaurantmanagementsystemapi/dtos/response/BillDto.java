package project.kien.restaurantmanagementsystemapi.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.kien.restaurantmanagementsystemapi.dtos.common.SessionDto;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillDto implements Serializable {
    private SessionDto session;

    private List<ItemBillDto> items;

    private double totalPrice;

    private int totalItemQuantity;
}

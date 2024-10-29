package demo.onebox.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class CartEntity {
    private Integer cartId;
    private List<ProductEntity> products;
    private OffsetDateTime expiryTime;
}

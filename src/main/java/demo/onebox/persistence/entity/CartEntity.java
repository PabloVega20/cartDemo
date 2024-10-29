package demo.onebox.persistence.entity;

import lombok.Data;

import java.util.List;

@Data
public class CartEntity {
    private String cartId;
    private List<ProductEntity> products;
}

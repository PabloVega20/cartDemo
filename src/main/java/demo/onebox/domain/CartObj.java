package demo.onebox.domain;

import lombok.Data;

import java.util.List;

@Data
public class CartObj {
    private String cartId;
    private List<ProductObj> products;
}

package demo.onebox.port;

import demo.onebox.domain.CartObj;
import demo.onebox.domain.ProductObj;

import java.util.List;

public interface ICartDao {
    void deleteById(String cartId);
    String createCart();
    CartObj getCartById(String cartId);
    CartObj addProductsById(String cartId, List<ProductObj> products);
}

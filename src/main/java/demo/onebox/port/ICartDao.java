package demo.onebox.port;

import demo.onebox.domain.CartObj;
import demo.onebox.domain.ProductObj;

import java.util.List;

public interface ICartDao {
    void deleteById(Integer cartId);
    String createCartById(Integer cartId);
    CartObj getCartById(Integer cartId);
    CartObj addProductsById(Integer cartId, List<ProductObj> products);
}
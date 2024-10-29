package demo.onebox.persistence;

import demo.onebox.domain.CartObj;
import demo.onebox.domain.ProductObj;
import demo.onebox.port.ICartDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartAdapter implements ICartDao {

    @Override
    public void deleteById(String cartId) {

    }

    @Override
    public String createCart() {
        return "";
    }


    @Override
    public CartObj getCartById(String cartId) {
        return null;
    }

    @Override
    public CartObj addProductsById(String cartId, List<ProductObj> products) {
        return null;
    }
}

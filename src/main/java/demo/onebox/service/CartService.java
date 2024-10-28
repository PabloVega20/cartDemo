package demo.onebox.service;

import demo.onebox.domain.CartObj;
import demo.onebox.domain.ProductObj;
import demo.onebox.port.ICartDao;
import demo.onebox.port.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService implements ICartService {
    private final ICartDao cartAdapter;

    @Override
    public void deleteCart(Integer cartId) {
        cartAdapter.deleteById(cartId);
    }

    @Override
    public String createCartById(Integer cartId) {
        return cartAdapter.createCartById(cartId);
    }

    @Override
    public CartObj getCartById(Integer cartId) {
        return cartAdapter.getCartById(cartId);
    }

    @Override
    public CartObj addProductsById(Integer cartId, List<ProductObj> products) {
        return cartAdapter.addProductsById(cartId,products);
    }
}

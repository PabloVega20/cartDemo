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
    public void deleteCart(String cartId) {
        cartAdapter.deleteById(cartId);
    }

    @Override
    public String createCart() {
        return cartAdapter.createCart();
    }


    @Override
    public CartObj getCartById(String cartId) {
        return cartAdapter.getCartById(cartId);
    }

    @Override
    public CartObj addProductsById(String cartId, List<ProductObj> products) {
        return cartAdapter.addProductsById(cartId,products);
    }
}

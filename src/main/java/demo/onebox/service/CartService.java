package demo.onebox.service;

import demo.onebox.domain.CartObj;
import demo.onebox.domain.ProductObj;
import demo.onebox.domain.exception.NotFoundException;
import demo.onebox.domain.exception.ValidationException;
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
    public void deleteCart(Integer cartId) throws NotFoundException {
        cartAdapter.deleteById(cartId);
    }

    @Override
    public Integer createCart(List<ProductObj> productObjList) throws ValidationException {
        return cartAdapter.createCart(productObjList);
    }


    @Override
    public CartObj getCartById(Integer cartId) throws NotFoundException {
        return cartAdapter.getCartById(cartId);
    }

    @Override
    public CartObj addProductsById(Integer cartId, List<ProductObj> products) throws ValidationException, NotFoundException {
        return cartAdapter.addProductsById(cartId,products);
    }
}

package demo.onebox.persistence;

import demo.onebox.domain.CartObj;
import demo.onebox.domain.ProductObj;
import demo.onebox.domain.exception.NotFoundException;
import demo.onebox.domain.exception.ValidationException;
import demo.onebox.persistence.repository.ICartRepository;
import demo.onebox.port.ICartDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CartAdapter implements ICartDao {
    private ICartRepository cartRepository;

    @Override
    public void deleteById(Integer cartId) throws NotFoundException {
        cartRepository.deleteById(cartId);
    }

    @Override
    public Integer createCart(List<ProductObj> productObjList) throws ValidationException {
        return cartRepository.createCart(productObjList);
    }


    @Override
    public CartObj getCartById(Integer cartId) throws NotFoundException {
        return cartRepository.getCartById(cartId);
    }

    @Override
    public CartObj addProductsById(Integer cartId, List<ProductObj> products) throws ValidationException, NotFoundException {
        return cartRepository.addProductsById(cartId,products);
    }
}

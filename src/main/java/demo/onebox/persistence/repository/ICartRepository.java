package demo.onebox.persistence.repository;

import demo.onebox.domain.CartObj;
import demo.onebox.domain.ProductObj;
import demo.onebox.domain.exception.NotFoundException;
import demo.onebox.domain.exception.ValidationException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartRepository {
    void deleteById(String cartId) throws NotFoundException;
    String createCart();
    CartObj getCartById(String cartId) throws NotFoundException;
    CartObj addProductsById(String cartId, List<ProductObj> products) throws NotFoundException, ValidationException;
}

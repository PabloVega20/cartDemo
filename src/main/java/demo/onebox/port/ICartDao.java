package demo.onebox.port;

import demo.onebox.domain.CartObj;
import demo.onebox.domain.ProductObj;
import demo.onebox.domain.exception.NotFoundException;
import demo.onebox.domain.exception.ValidationException;

import java.util.List;

public interface ICartDao {
    void deleteById(Integer cartId) throws NotFoundException;
    Integer createCart(List<ProductObj> productObjList) throws ValidationException;
    CartObj getCartById(Integer cartId) throws NotFoundException;
    CartObj addProductsById(Integer cartId, List<ProductObj> products) throws ValidationException, NotFoundException;
}

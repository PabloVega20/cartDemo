package demo.onebox.persistence.repository;

import demo.onebox.domain.CartObj;
import demo.onebox.domain.ProductObj;
import demo.onebox.domain.enums.TypeError;
import demo.onebox.domain.exception.NotFoundException;
import demo.onebox.domain.exception.ValidationException;
import demo.onebox.persistence.entity.CartEntity;
import demo.onebox.persistence.entity.ProductEntity;
import demo.onebox.persistence.mapper.ICartPersistenceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CartInMemoryRepository implements ICartRepository{
    private final Map<String, CartEntity> cartStorage = new HashMap<>();
    private final Set<Integer> productId = new HashSet<>();
    private final ICartPersistenceMapper cartMapper;
    @Override
    public void deleteById(String cartId) throws NotFoundException {
        //TODO: TESTEAR QUE PASA SI NO EXISTE EL ID
        cartStorage.remove(cartId);
        //throw new NotFoundException(TypeError.ERROR_CART_NO_EXISTS.getValue(),
        //            TypeError.ERROR_CART_NO_EXISTS.getDescription());

    }

    @Override
    public String createCart() {
        String id = generateUniqueId();
        cartStorage.put(id, new CartEntity());
        return id;
    }


    @Override
    public CartObj getCartById(String cartId) throws NotFoundException {
        CartEntity cartEntity = cartStorage.get(cartId);
        if(Objects.isNull(cartEntity)){
            throw new NotFoundException(TypeError.ERROR_CART_NO_EXISTS.getValue(),
                    TypeError.ERROR_CART_NO_EXISTS.getDescription()
            );
        }
        return cartMapper.cartEntityToCartObj(cartEntity);
    }

    @Override
    public CartObj addProductsById(String cartId, List<ProductObj> products) throws NotFoundException, ValidationException {
        CartEntity cartEntity = cartStorage.get(cartId);
        if(Objects.isNull(cartEntity)){
            throw new NotFoundException(TypeError.ERROR_CART_NO_EXISTS.getValue(),
                    TypeError.ERROR_CART_NO_EXISTS.getDescription()
            );
        }
        List<ProductEntity> productEntityList = cartMapper.productObjToProductEntity(products);
        checkProductId(productEntityList);
        cartEntity.setProducts(productEntityList);
        cartStorage.put(cartId, cartEntity);
        return cartMapper.cartEntityToCartObj(cartEntity);
    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    private void checkProductId(List<ProductEntity> productEntityList) throws ValidationException {
        for(ProductEntity productEntity:productEntityList){
            if(productId.contains(productEntity.getId())) throw new ValidationException(TypeError.ERROR_PRODUCT_ID_EXISTS.getValue(),
                    TypeError.ERROR_PRODUCT_ID_EXISTS.getDescription(), "product id");
        }
    }
}

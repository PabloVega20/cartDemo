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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Repository
@AllArgsConstructor
public class CartInMemoryRepository implements ICartRepository{
    private final Map<Integer, CartEntity> cartStorage = new HashMap<>();
    private final Set<Integer> productId = new HashSet<>();
    private final ICartPersistenceMapper cartMapper;
    private static int autoIncrement = 0;

    @Override
    public void deleteById(Integer cartId) {
        cartStorage.remove(cartId);
    }

    @Override
    public Integer createCart(List<ProductObj> productObjList) throws ValidationException {
        Integer id = autoIncrement++;
        List<ProductEntity> productEntityList = cartMapper.productObjToProductEntity(productObjList);
        checkProductId(productEntityList);
        cartStorage.put(id, new CartEntity(id,productEntityList, OffsetDateTime.now().plusMinutes(10)));
        return id;
    }


    @Override
    public CartObj getCartById(Integer cartId) throws NotFoundException {
        CartEntity cartEntity = cartStorage.get(cartId);
        if(Objects.isNull(cartEntity)){
            throw new NotFoundException(TypeError.ERROR_CART_NO_EXISTS.getValue(),
                    TypeError.ERROR_CART_NO_EXISTS.getDescription()
            );
        }
        cartEntity.setExpiryTime(OffsetDateTime.now().plusMinutes(10));
        cartStorage.put(cartId, cartEntity);
        return cartMapper.cartEntityToCartObj(cartEntity);
    }

    @Override
    public CartObj addProductsById(Integer cartId, List<ProductObj> products) throws NotFoundException, ValidationException {
        CartEntity cartEntity = cartStorage.get(cartId);
        if(Objects.isNull(cartEntity)){
            throw new NotFoundException(TypeError.ERROR_CART_NO_EXISTS.getValue(),
                    TypeError.ERROR_CART_NO_EXISTS.getDescription()
            );
        }
        List<ProductEntity> productEntityList = cartMapper.productObjToProductEntity(products);
        checkProductId(productEntityList);
        cartEntity.getProducts().addAll(productEntityList);
        cartEntity.setExpiryTime(OffsetDateTime.now().plusMinutes(10));
        cartStorage.put(cartId, cartEntity);
        return cartMapper.cartEntityToCartObj(cartEntity);
    }

    private void checkProductId(List<ProductEntity> productEntityList) throws ValidationException {
        for(ProductEntity productEntity:productEntityList){
            if(productId.contains(productEntity.getId())){
                throw new ValidationException(TypeError.ERROR_PRODUCT_ID_EXISTS.getValue(),
                        TypeError.ERROR_PRODUCT_ID_EXISTS.getDescription(), "product id");
            }
            productId.add(productEntity.getId());
        }
    }

    @Scheduled(fixedRate = 60000)
    public void removeExpiredCarts() {
        OffsetDateTime now = OffsetDateTime.now();
        cartStorage.entrySet().removeIf(entry -> entry.getValue().getExpiryTime().isBefore(now));
    }
}

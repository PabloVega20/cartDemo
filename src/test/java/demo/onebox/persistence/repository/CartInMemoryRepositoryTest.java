package demo.onebox.persistence.repository;

import demo.onebox.domain.CartObj;
import demo.onebox.domain.ProductObj;
import demo.onebox.domain.enums.TypeError;
import demo.onebox.domain.exception.NotFoundException;
import demo.onebox.domain.exception.ValidationException;
import demo.onebox.persistence.entity.ProductEntity;
import demo.onebox.persistence.mapper.ICartPersistenceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartInMemoryRepositoryTest {
    @InjectMocks
    private CartInMemoryRepository cartRepository;
    @Mock
    private ICartPersistenceMapper cartMapper;

    @Test
    void testCreateCart() throws ValidationException {
        List<ProductObj> productObjList = Collections.singletonList(new ProductObj(1, "Test Product", 10));
        List<ProductEntity> productEntityList = Collections.singletonList(new ProductEntity(1, "Test Product", 10));

        when(cartMapper.productObjToProductEntity(anyList())).thenReturn(productEntityList);
        Integer cartId = cartRepository.createCart(productObjList);

        assertNotNull(cartId);
        verify(cartMapper, times(1)).productObjToProductEntity(anyList());
    }

    @Test
    void testGetCartById_Success() throws NotFoundException, ValidationException {
        when(cartMapper.productObjToProductEntity(anyList()))
                .thenReturn(Collections.singletonList(new ProductEntity(1, "Test Product", 10)));
        Integer cartId = cartRepository.createCart(Collections.singletonList(new ProductObj(1, "Test Product", 10)));

        assertEquals(cartId, 0);
    }

    @Test
    void testGetCartById_NotFound() {
        Integer cartId = 999;
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            cartRepository.getCartById(cartId);
        });
        assertEquals(TypeError.ERROR_CART_NO_EXISTS.getDescription(), exception.getMessage());
    }

    @Test
    void testAddProductsById_Success() throws NotFoundException, ValidationException {
        Integer cartId = cartRepository.createCart(Collections.singletonList(new ProductObj(1, "Test Product", 10)));
        List<ProductObj> newProducts = Arrays.asList(new ProductObj(2, "Test Product 2", 15));
        List<ProductEntity> productEntityList = Collections.singletonList(new ProductEntity(2, "Test Product 2", 15));

        when(cartMapper.productObjToProductEntity(anyList())).thenReturn(productEntityList);
        when(cartMapper.cartEntityToCartObj(any())).thenReturn(new CartObj());
        CartObj updatedCart = cartRepository.addProductsById(cartId, newProducts);

        assertNotNull(updatedCart);
        verify(cartMapper, times(2)).productObjToProductEntity(anyList());
        verify(cartMapper).cartEntityToCartObj(any());
    }

    @Test
    void testAddProductsById_NotFound() {
        Integer cartId = 999;
        List<ProductObj> newProducts = Collections.singletonList(new ProductObj(2, "Test Product 2", 15));
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            cartRepository.addProductsById(cartId, newProducts);
        });
        assertEquals(TypeError.ERROR_CART_NO_EXISTS.getDescription(), exception.getMessage());
    }

    @Test
    void testDeleteById() throws NotFoundException, ValidationException {
        when(cartMapper.productObjToProductEntity(anyList()))
                .thenReturn(Collections.singletonList(new ProductEntity(1, "Test Product", 10)));
        when(cartMapper.cartEntityToCartObj(any())).thenReturn(new CartObj());

        Integer cartId = cartRepository.createCart(Collections.singletonList(new ProductObj(1, "Test Product", 10)));
        Integer beforeDelete = Objects.isNull(cartRepository.getCartById(cartId)) ? 0 : 1;
        cartRepository.deleteById(cartId);
        Integer afterDelete = 0;
        try{
            cartRepository.getCartById(cartId);
        }catch (NotFoundException nfe){
            afterDelete = 1;
        }
        assertEquals(beforeDelete,1);
        assertEquals(afterDelete,1);
    }
}

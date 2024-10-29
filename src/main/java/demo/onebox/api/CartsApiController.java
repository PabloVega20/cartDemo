package demo.onebox.api;

import demo.onebox.model.CartDTO;
import demo.onebox.model.CartsPost201Response;
import demo.onebox.model.ProductDTO;
import demo.onebox.port.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CartsApiController implements CartsApi {
    private final ICartService cartService;
    private final ICartApiMapper cartApiMapper;

    @Override
    public ResponseEntity<CartDTO> cartsCartIdGet(String cartId) throws Exception {
        return new ResponseEntity<>(
            cartApiMapper.cartObjToCartDto(
                cartService.getCartById(cartId)
            ),
        HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDTO> cartsCartIdProductsPost(String cartId, List<ProductDTO> productDTO) throws Exception {
        return new ResponseEntity<>(
            cartApiMapper.cartObjToCartDto(
                cartService.addProductsById(cartId,
                  cartApiMapper.productDtoToProductObj(productDTO)
                )
            ),
        HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> cartsDelete(String cartId) throws Exception {
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartsPost201Response> cartsPost(List<ProductDTO> productDTO) throws Exception {
        return null;
    }


    public ResponseEntity<CartsPost201Response> cartsPost() throws Exception {
        return new ResponseEntity<>(
            cartApiMapper.idStringToResponse(
                cartService.createCart()
            ),
        HttpStatus.OK);
    }
}

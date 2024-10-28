package demo.onebox.api;

import demo.onebox.port.ICartService;
import es.fudontime.api.CartsApi;
import es.fudontime.model.CartDTO;
import es.fudontime.model.CartsPost201Response;
import es.fudontime.model.ProductDTO;
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
    public ResponseEntity<CartDTO> cartsCartIdGet(Integer cartId) throws Exception {
        return new ResponseEntity<>(
            cartApiMapper.cartObjToCartDto(
                    cartService.getCartById(cartId)
            ),
        HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> cartsCartIdProductsPost(Integer cartId, List<ProductDTO> productDTO) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Void> cartsDelete(Integer cartId) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<CartsPost201Response> cartsPost() throws Exception {
        return null;
    }
}

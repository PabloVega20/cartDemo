package demo.onebox.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.onebox.domain.CartObj;
import demo.onebox.model.CartDTO;
import demo.onebox.model.CartsPost201Response;
import demo.onebox.model.ProductDTO;
import demo.onebox.port.ICartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(CartsApiController.class)
class CartsApiControllerTest {

    @MockBean
    private ICartService cartService;

    @MockBean
    private ICartApiMapper cartApiMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetCartById() throws Exception {
        Integer cartId = 1;
        CartDTO cartDTO = new CartDTO();

        when(cartService.getCartById(anyInt())).thenReturn(new CartObj());
        when(cartApiMapper.cartObjToCartDto(any())).thenReturn(cartDTO);

        mockMvc.perform(get("/carts/" + cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        InOrder orderVerifier = Mockito.inOrder(cartService, cartApiMapper);
        orderVerifier.verify(cartService, times(1)).getCartById(anyInt());
        orderVerifier.verify(cartApiMapper, times(1)).cartObjToCartDto(any());
        orderVerifier.verifyNoMoreInteractions();
    }

    @Test
    void testAddProductsToCart() throws Exception {
        Integer cartId = 1;
        List<ProductDTO> productDTOList = Collections.singletonList(new ProductDTO());
        CartDTO cartDTO = new CartDTO();

        when(cartApiMapper.productDtoToProductObj(anyList())).thenReturn(Collections.emptyList());
        when(cartService.addProductsById(anyInt(), anyList())).thenReturn(new CartObj());
        when(cartApiMapper.cartObjToCartDto(any())).thenReturn(cartDTO);

        mockMvc.perform(post("/carts/{cartId}/products", cartId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productDTOList)))
                .andExpect(status().isOk());

        InOrder orderVerifier = Mockito.inOrder(cartApiMapper, cartService);
        orderVerifier.verify(cartApiMapper, times(1)).productDtoToProductObj(anyList());
        orderVerifier.verify(cartService, times(1)).addProductsById(anyInt(), anyList());
        orderVerifier.verify(cartApiMapper, times(1)).cartObjToCartDto(any());
        orderVerifier.verifyNoMoreInteractions();
    }

    @Test
    void testDeleteCart() throws Exception {
        Integer cartId = 1;

        mockMvc.perform(delete("/carts/{cartId}", cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(cartService, times(1)).deleteCart(cartId);
        Mockito.verifyNoMoreInteractions(cartService);
    }

    @Test
    void testCreateCart() throws Exception {
        List<ProductDTO> productDTOList = Collections.singletonList(new ProductDTO());
        CartsPost201Response response = new CartsPost201Response();

        when(cartApiMapper.productDtoToProductObj(anyList())).thenReturn(Collections.emptyList());
        when(cartService.createCart(anyList())).thenReturn(1);
        when(cartApiMapper.idToResponse(anyInt())).thenReturn(response);

        mockMvc.perform(post("/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(productDTOList)))
                .andExpect(status().isOk());

        InOrder orderVerifier = Mockito.inOrder(cartApiMapper, cartService);
        orderVerifier.verify(cartApiMapper, times(1)).productDtoToProductObj(anyList());
        orderVerifier.verify(cartService, times(1)).createCart(anyList());
        orderVerifier.verify(cartApiMapper, times(1)).idToResponse(anyInt());
        orderVerifier.verifyNoMoreInteractions();
    }
}

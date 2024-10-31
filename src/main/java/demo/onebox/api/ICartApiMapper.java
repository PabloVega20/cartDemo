package demo.onebox.api;

import demo.onebox.domain.CartObj;
import demo.onebox.domain.ProductObj;
import demo.onebox.model.CartDTO;
import demo.onebox.model.CartsPost201Response;
import demo.onebox.model.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICartApiMapper {
    CartDTO cartObjToCartDto(CartObj cartObj);
    List<ProductObj> productDtoToProductObj(List<ProductDTO> productDto);

    @Mapping(source = "id",target = "cartId")
    CartsPost201Response idToResponse(Integer id);
}

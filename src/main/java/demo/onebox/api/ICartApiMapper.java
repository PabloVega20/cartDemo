package demo.onebox.api;

import demo.onebox.domain.CartObj;
import demo.onebox.domain.ProductObj;
import es.fudontime.model.CartsCartIdGet200Response;
import es.fudontime.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICartApiMapper {
    CartsCartIdGet200Response cartObjToCartDto(CartObj cartObj);
    List<ProductObj> productDtoToProductObj(List<Product> productDto);
}

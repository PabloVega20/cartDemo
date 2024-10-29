package demo.onebox.persistence.mapper;

import demo.onebox.domain.CartObj;
import demo.onebox.domain.ProductObj;
import demo.onebox.persistence.entity.CartEntity;
import demo.onebox.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICartPersistenceMapper {
    List<ProductEntity> productObjToProductEntity(List<ProductObj> productObj);
    CartObj cartEntityToCartObj(CartEntity cartEntity);
}

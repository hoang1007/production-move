package vnu.uet.prodmove.utils.converter.db;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import vnu.uet.prodmove.utils.productStage.ProductStageType;

@Converter(autoApply = true)
public class ProductStageTypeConverter implements AttributeConverter<ProductStageType, String> {

    @Override
    public String convertToDatabaseColumn(ProductStageType attribute) {
        return attribute.toString();
    }

    @Override
    public ProductStageType convertToEntityAttribute(String dbData) {
        return ProductStageType.fromString(dbData);
    }
}

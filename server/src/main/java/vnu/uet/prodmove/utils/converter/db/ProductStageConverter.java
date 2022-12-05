package vnu.uet.prodmove.utils.converter.db;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import vnu.uet.prodmove.enums.ProductStage;

@Converter(autoApply = true)
public class ProductStageConverter implements AttributeConverter<ProductStage, String> {

    @Override
    public String convertToDatabaseColumn(ProductStage attribute) {
        return attribute.toString();
    }

    @Override
    public ProductStage convertToEntityAttribute(String dbData) {
        return ProductStage.fromString(dbData);
    }
}
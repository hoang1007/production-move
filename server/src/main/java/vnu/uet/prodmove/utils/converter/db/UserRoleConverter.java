package vnu.uet.prodmove.utils.converter.db;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import vnu.uet.prodmove.config.UserRole;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole attribute) {
        return attribute.toString();
    }

    @Override
    public UserRole convertToEntityAttribute(String dbData) {
        return UserRole.fromString(dbData);
    }
}
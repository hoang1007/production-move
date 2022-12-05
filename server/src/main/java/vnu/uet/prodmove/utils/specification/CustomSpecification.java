package vnu.uet.prodmove.utils.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

public class CustomSpecification<Clazz> implements Specification<Clazz> {
    public Specification<Clazz> createSpecification(Filter input) {
        switch (input.getOperator()) {

            case EQUALS:
                return (root, query, criteriaBuilder) -> 
                criteriaBuilder.equal(
                        root.get(input.getField()),
                        castToRequiredType(root.get(input.getField()).getJavaType(), input.getValue())
                );

            case NOT_EQUALS:
                return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(input.getField()),
                        castToRequiredType(root.get(input.getField()).getJavaType(),
                                input.getValue()));

            case GREATER_THAN:
                return (root, query, criteriaBuilder) -> criteriaBuilder.gt(root.get(input.getField()),
                        (Number) castToRequiredType(
                                root.get(input.getField()).getJavaType(),
                                input.getValue()));

            case LESS_THAN:
                return (root, query, criteriaBuilder) -> criteriaBuilder.lt(root.get(input.getField()),
                        (Number) castToRequiredType(
                                root.get(input.getField()).getJavaType(),
                                input.getValue()));

            case LIKE:
                return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(input.getField()),
                        "%" + input.getValue() + "%");

            case IN:
                return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(input.getField()))
                        .value(castToRequiredType(
                                root.get(input.getField()).getJavaType(),
                                input.getValues()));

            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }

    private Object castToRequiredType(Class<?> fieldType, String value) {
        if (fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if (fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        }
        return null;
    }

    private Object castToRequiredType(Class<?> fieldType, List<String> value) {
        List<Object> lists = new ArrayList<>();
        for (String s : value) {
            lists.add(castToRequiredType(fieldType, s));
        }
        return lists;
    }

    @Override
    @Nullable
    public Predicate toPredicate(Root<Clazz> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // TODO Auto-generated method stub
        return null;
    }
}

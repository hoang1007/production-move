package vnu.uet.prodmove.utils.specification;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Filter {
    private String field;
    private QueryOperator operator;
    private String value;
    private List<String> values; // Used in case of IN operator

    public Filter field(String field) {
        this.field = field;
        return this;
    }

    public Filter queryOperator(QueryOperator op) {
        this.operator = op;
        return this;
    }

    public Filter value(String value) {
        this.value = value;
        this.values = null;
        return this;
    }

    public Filter values(List<String> values) {
        this.values = values;
        this.value = null;
        return this;
    }

    public static Filter builder() {
        return new Filter();
    }
}

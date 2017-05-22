package xyz.morecraft.dev.lang.morelang.object;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Variable {

    private String name;
    private Expression arrayIndexExpression;

    public boolean isArray() {
        return Objects.nonNull(arrayIndexExpression);
    }

}
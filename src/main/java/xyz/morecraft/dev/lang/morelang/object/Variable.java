package xyz.morecraft.dev.lang.morelang.object;


import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.morecraft.dev.lang.morelang.object.expression.Expression;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Variable {

    private String name;
    private String alias;
    private Expression arrayIndexExpression;

    public Variable(String name, Expression arrayIndexExpression) {
        this.name = name;
        this.arrayIndexExpression = arrayIndexExpression;
    }

    public boolean isArray() {
        return Objects.nonNull(arrayIndexExpression);
    }

    public String name() {
        return Objects.isNull(alias) ? name : alias;
    }

}

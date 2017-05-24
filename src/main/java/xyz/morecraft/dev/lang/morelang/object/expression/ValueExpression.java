package xyz.morecraft.dev.lang.morelang.object.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.morecraft.dev.lang.morelang.object.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.statement.Statement;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ValueExpression extends Expression {

    private String value;

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry, Type requiredType, Statement statementContext, Expression expressionContext) {
        List<String> lines = new ArrayList<>();

        setAlias(value);

//        if (Objects.nonNull(statementContext)) {
//            if (statementContext instanceof VariableDefinition) {
//                setAlias(value);
//            }
//        } else if (Objects.nonNull(expressionContext)) {
//            if (expressionContext instanceof FunctionInvocationExpression) {
//                setAlias(value);
//            }
//        }

        return lines;
    }

}

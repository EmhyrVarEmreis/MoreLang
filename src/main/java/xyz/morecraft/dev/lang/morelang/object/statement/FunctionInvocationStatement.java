package xyz.morecraft.dev.lang.morelang.object.statement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.morecraft.dev.lang.morelang.object.expression.Expression;
import xyz.morecraft.dev.lang.morelang.object.expression.FunctionInvocationExpression;
import xyz.morecraft.dev.lang.morelang.object.registry.FunctionContextRegistry;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FunctionInvocationStatement extends Statement {

    private String name;
    private List<Expression> argumentExpressionList;

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry) {
        final FunctionInvocationExpression expression = new FunctionInvocationExpression(this, true);
        expression.setLine(getLine());
        expression.setPos(getPos());
        return expression.llvm(functionContextRegistry, null, this, null);
    }

}

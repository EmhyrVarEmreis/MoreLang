package xyz.morecraft.dev.lang.morelang.object.statement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.morecraft.dev.lang.morelang.object.registry.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.expression.Expression;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FunctionInvocationStatement extends Statement {

    private String name;
    private List<Expression> argumentExpressionList;

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry) {
        return new ArrayList<>();
    }

}

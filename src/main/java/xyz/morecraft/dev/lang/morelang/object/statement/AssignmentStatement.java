package xyz.morecraft.dev.lang.morelang.object.statement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import xyz.morecraft.dev.lang.morelang.object.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.Variable;
import xyz.morecraft.dev.lang.morelang.object.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.expression.Expression;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class AssignmentStatement extends Statement {

    private Variable variable;
    private Expression expression;

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry) {
        return new ArrayList<>();
    }

}

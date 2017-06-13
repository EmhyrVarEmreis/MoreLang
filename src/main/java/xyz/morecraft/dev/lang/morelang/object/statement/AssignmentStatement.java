package xyz.morecraft.dev.lang.morelang.object.statement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import xyz.morecraft.dev.lang.morelang.object.registry.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.Variable;
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
        List<String> lines = new ArrayList<>();

        Type type = functionContextRegistry.getType(variable.getName());

        variable.setAlias(functionContextRegistry.getAlias(variable.getName()));

        Type requiredType = Type.of(type);
        requiredType.setPointer(false);

        lines.addAll(expression.llvm(functionContextRegistry, requiredType, this, null));

        lines.add("store " + type.getSimpleType().getLlvm() + " " + expression.getAlias() + ", " + type.getSimpleType().getLlvm() + "* " + variable.name() + ", align 4");

        return lines;
    }

}

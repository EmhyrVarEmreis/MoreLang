package xyz.morecraft.dev.lang.morelang.object.expression;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.Variable;
import xyz.morecraft.dev.lang.morelang.object.registry.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.statement.AssignmentStatement;
import xyz.morecraft.dev.lang.morelang.object.statement.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VariableExpression extends Expression {

    private Variable variable;

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry, Type requiredType, Statement statementContext, Expression expressionContext) {
        List<String> lines = new ArrayList<>();

        functionContextRegistry.updateVariable(variable);

        if (statementContext instanceof AssignmentStatement) {
            System.out.println("\tvar: \t\t\t" + variable.getName());
            System.out.println("\trequiredType:\t" + requiredType);
            System.out.println("\ttype: \t\t\t" + functionContextRegistry.getType(variable.getName()));
        }

        Type type = functionContextRegistry.getType(variable.getName());
        if (Objects.nonNull(requiredType) && !requiredType.equals(type)) {
            String newAlias = "%" + functionContextRegistry.getNextTemporaryVariableName();
            lines.add(newAlias + " = load " + requiredType.getSimpleType().getLlvm() + ", " + type.getSimpleType().getLlvm() + "* " + variable.name() + ", align 4");
            setAlias(newAlias);
        } else {
            setAlias(variable.name());
        }

        functionContextRegistry.register(this, requiredType, false);

        return lines;
    }

}

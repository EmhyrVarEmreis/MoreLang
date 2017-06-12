package xyz.morecraft.dev.lang.morelang.object.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.object.registry.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.statement.FunctionInvocationStatement;
import xyz.morecraft.dev.lang.morelang.object.statement.Statement;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.FunctionDefinition;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FunctionInvocationExpression extends Expression {

    private FunctionInvocationStatement invocation;

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry, Type requiredType, Statement statementContext, Expression expressionContext) {
        List<String> lines = new ArrayList<>();

        FunctionDefinition functionDefinition = functionContextRegistry.getProgramRegistry().getFunctionDefinition(invocation.getName());

        StringBuilder args = new StringBuilder();
        for (int i = 0; i < invocation.getArgumentExpressionList().size(); i++) {
            Expression expression = invocation.getArgumentExpressionList().get(i);
            TypedIdentifier typedIdentifier = functionDefinition.getArgumentList().get(i);
            lines.addAll(expression.llvm(functionContextRegistry, requiredType, null, this));
            args.append(", ").append(typedIdentifier.getType().getSimpleType().getLlvm()).append(" ").append(expression.getAlias());
        }

        String tmpAlias = functionContextRegistry.getNextTemporaryVariableName();

        setAlias("%" + tmpAlias);

        lines.add(
                "%" + tmpAlias + " = call "
                        + functionDefinition.getTypedIdentifier().getType().getSimpleType().getLlvm()
                        + " @" + invocation.getName() + "( " + args.substring(args.length() == 0 ? 0 : 2) + " )"
        );

        functionContextRegistry.register(this, functionDefinition.getTypedIdentifier().getType(), false);

        return lines;
    }

}

package xyz.morecraft.dev.lang.morelang.object.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.morecraft.dev.lang.morelang.exception.UndefinedFunctionException;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.object.registry.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.statement.FunctionInvocationStatement;
import xyz.morecraft.dev.lang.morelang.object.statement.Statement;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.FunctionDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FunctionInvocationExpression extends Expression {

    private FunctionInvocationStatement invocation;
    private boolean isStatement;

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry, Type requiredType, Statement statementContext, Expression expressionContext) {
        final List<String> lines = new ArrayList<>();

        final FunctionDefinition functionDefinition = functionContextRegistry.getProgramRegistry().getFunctionDefinition(invocation.getName());

        if (Objects.isNull(functionDefinition)) {
            throw new UndefinedFunctionException(this, invocation.getName());
        }

        final StringBuilder args = new StringBuilder();
        for (int i = 0; i < invocation.getArgumentExpressionList().size(); i++) {
            final Expression expression = invocation.getArgumentExpressionList().get(i);
            final TypedIdentifier typedIdentifier = functionDefinition.getArgumentList().get(i);
            final Type type = Type.of(typedIdentifier.getType());
            type.setPointer(false);
            lines.addAll(expression.llvm(functionContextRegistry, type, null, this));
            args.append(", ").append(typedIdentifier.getType().getSimpleType().getLlvm()).append(" ").append(expression.getAlias());
        }

        String preparedName;
        if (functionDefinition.isInternal()) {
            preparedName = functionDefinition.getPreparedHeader();
        } else {
            preparedName = "@" + invocation.getName();
        }

        setAlias("%" + functionContextRegistry.getNextTemporaryVariableName());

        if (isStatement) {
            lines.add(
                    "call " +
                            functionDefinition.getTypedIdentifier().getType().getSimpleType().getLlvm() +
                            " " +
                            preparedName +
                            "( " +
                            args.substring(args.length() == 0 ? 0 : 2) +
                            " )"
            );
        } else {
            lines.add(
                    getAlias() +
                            " = call " +
                            functionDefinition.getTypedIdentifier().getType().getSimpleType().getLlvm() +
                            " " +
                            preparedName +
                            "( " +
                            args.substring(args.length() == 0 ? 0 : 2) +
                            " )"
            );
            functionContextRegistry.register(this, functionDefinition.getTypedIdentifier().getType(), false);
        }

        return lines;
    }

}

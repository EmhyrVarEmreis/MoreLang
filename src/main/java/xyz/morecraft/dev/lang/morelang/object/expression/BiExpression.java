package xyz.morecraft.dev.lang.morelang.object.expression;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.morecraft.dev.lang.morelang.object.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.Operator;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.statement.Statement;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BiExpression extends Expression {

    private Expression left;
    private Expression right;
    private Operator operator;

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry, Type requiredType, Statement statementContext, Expression expressionContext) {
        List<String> lines = new ArrayList<>();

        System.out.println("biexp");

        lines.addAll(left.llvm(functionContextRegistry, requiredType, statementContext, this));
        lines.addAll(right.llvm(functionContextRegistry, requiredType, statementContext, this));

        setAlias("%" + functionContextRegistry.getNextTemporaryVariableName());

        switch (operator) {
            case MULTIPLY:
                lines.add(getAlias() + " = mul nsw " + requiredType.getSimpleType().getLlvm() + " " + left.getAlias() + ", " + right.getAlias());
                break;
            case PLUS:
                lines.add(getAlias() + " = add nsw " + requiredType.getSimpleType().getLlvm() + " " + left.getAlias() + ", " + right.getAlias());
                break;
            default:
                throw new IllegalArgumentException("Illegal operator");
        }

        return lines;
    }

}

package xyz.morecraft.dev.lang.morelang.object.statement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.morecraft.dev.lang.morelang.object.registry.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.SimpleType;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.expression.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class IfStatement extends Statement {

    private Expression conditionExpression;
    private List<Statement> thenBlock;
    private List<Statement> elseBlock;

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry) {
        final ArrayList<String> lines = new ArrayList<>();

        lines.addAll(
                conditionExpression.llvm(
                        functionContextRegistry,
                        new Type(SimpleType.BOOLEAN, false, false),
                        this,
                        null
                )
        );

        final String thenLabelName = functionContextRegistry.getNextTemporaryLabelName();
        final String elseLabelName = functionContextRegistry.getNextTemporaryLabelName();
        final String endLabelName = Objects.nonNull(elseBlock) ? functionContextRegistry.getNextTemporaryLabelName() : elseLabelName;

        lines.add("br i1 " + conditionExpression.getAlias() + ", label %" + thenLabelName + ", label %" + elseLabelName);

        lines.add(thenLabelName + ":");
        for (Statement statement : thenBlock) {
            lines.addAll(statement.llvm(functionContextRegistry));
        }
        lines.add("br label %" + endLabelName);
        if (Objects.nonNull(elseBlock)) {
            lines.add(elseLabelName + ":");
            for (Statement statement : elseBlock) {
                lines.addAll(statement.llvm(functionContextRegistry));
            }
            lines.add("br label %" + endLabelName);
        }
        lines.add(endLabelName + ":");

        return lines;
    }

}

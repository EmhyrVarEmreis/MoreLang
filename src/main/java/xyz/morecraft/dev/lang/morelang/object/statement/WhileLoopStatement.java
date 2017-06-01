package xyz.morecraft.dev.lang.morelang.object.statement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.morecraft.dev.lang.morelang.object.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.SimpleType;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.expression.Expression;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WhileLoopStatement extends Statement {

    private Expression conditionExpression;
    private List<Statement> bodyBlock;

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry) {
        final ArrayList<String> lines = new ArrayList<>();

        final String[] labels = {
                functionContextRegistry.getNextTemporaryLabelName(),
                functionContextRegistry.getNextTemporaryLabelName(),
                functionContextRegistry.getNextTemporaryLabelName()
        };

        lines.add("br label %" + labels[0]);

        lines.add(labels[0] + ":");
        lines.addAll(
                conditionExpression.llvm(
                        functionContextRegistry,
                        new Type(SimpleType.BOOLEAN, false, false),
                        this,
                        null
                )
        );
        lines.add("br i1 " + conditionExpression.getAlias() + ", label %" + labels[1] + ", label %" + labels[2]);

        lines.add(labels[1] + ":");

        for (Statement statement : bodyBlock) {
            lines.addAll(statement.llvm(functionContextRegistry));
        }
        lines.add("br label %" + labels[0]);

        lines.add(labels[2] + ":");

        return lines;
    }

}

package xyz.morecraft.dev.lang.morelang.object.statement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.morecraft.dev.lang.morelang.object.SimpleType;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.expression.Expression;
import xyz.morecraft.dev.lang.morelang.object.registry.FunctionContextRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WhileLoopStatement extends Statement {

    private Expression conditionExpression;
    private List<Statement> bodyBlock;
    private boolean isDo;

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
        if (isDo) {
            lines.addAll(bodyBlock.stream().flatMap(statement -> statement.llvm(functionContextRegistry).stream()).collect(Collectors.toList()));
            lines.add("br label %" + labels[1]);
            lines.add(labels[1] + ":");
            lines.addAll(conditionExpression.llvm(
                    functionContextRegistry,
                    new Type(SimpleType.BOOLEAN, false, false),
                    this,
                    null
            ));
            lines.add("br i1 " + conditionExpression.getAlias() + ", label %" + labels[0] + ", label %" + labels[2]);
        } else {
            lines.addAll(conditionExpression.llvm(
                    functionContextRegistry,
                    new Type(SimpleType.BOOLEAN, false, false),
                    this,
                    null
            ));
            lines.add("br i1 " + conditionExpression.getAlias() + ", label %" + labels[1] + ", label %" + labels[2]);
            lines.add(labels[1] + ":");
            lines.addAll(bodyBlock.stream().flatMap(statement -> statement.llvm(functionContextRegistry).stream()).collect(Collectors.toList()));
            lines.add("br label %" + labels[0]);
        }
        lines.add(labels[2] + ":");

        return lines;
    }

}

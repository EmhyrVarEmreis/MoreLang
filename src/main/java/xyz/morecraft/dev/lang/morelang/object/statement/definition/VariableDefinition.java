package xyz.morecraft.dev.lang.morelang.object.statement.definition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import xyz.morecraft.dev.lang.morelang.object.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.object.expression.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class VariableDefinition extends Definition {

    private Expression content;

    public VariableDefinition(TypedIdentifier typedIdentifier, Expression content) {
        super(typedIdentifier);
        this.content = content;
    }

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry) {
        List<String> lines = new ArrayList<>();

        getTypedIdentifier().getType().setPointer(true);
        functionContextRegistry.registerType(getTypedIdentifier());

        lines.add("%" + getTypedIdentifier().getName() + " = alloca " + getTypedIdentifier().getType().getSimpleType().getLlvm() + ", align 4");

        if (Objects.nonNull(content)) {
            lines.addAll(content.llvm(functionContextRegistry, getTypedIdentifier().getType(), this, null));
            lines.add("store " + getTypedIdentifier().getType().getSimpleType().getLlvm() + " " + content.getAlias() + ", i32* %" + getTypedIdentifier().name() + ", align 4");
        }

        return lines;
    }

}
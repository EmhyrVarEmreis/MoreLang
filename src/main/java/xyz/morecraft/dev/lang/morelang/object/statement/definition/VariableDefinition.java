package xyz.morecraft.dev.lang.morelang.object.statement.definition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.object.expression.Expression;
import xyz.morecraft.dev.lang.morelang.object.registry.FunctionContextRegistry;

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
        final List<String> lines = new ArrayList<>();

        getTypedIdentifier().setAlias(functionContextRegistry.getAlias(getTypedIdentifier()));

        getTypedIdentifier().getType().setPointer(true);
        functionContextRegistry.register(getTypedIdentifier(), false);

        System.out.println("vardef " + functionContextRegistry.getParent().getTypedIdentifier().getName() + " " + getTypedIdentifier().getName() + " \t" + getTypedIdentifier().getType());

        lines.add("%" + getTypedIdentifier().getName() + " = alloca " + getTypedIdentifier().getType().getSimpleType().getLlvm() + ", align 4");

        if (Objects.nonNull(content)) {
            lines.addAll(content.llvm(functionContextRegistry, getTypedIdentifier().getType(), this, null));
            lines.add("store " + getTypedIdentifier().getType().getSimpleType().getLlvm() + " " + content.getAlias() + ", " + getTypedIdentifier().getType().getSimpleType().getLlvm() + "* %" + getTypedIdentifier().name() + ", align 4");
        }

        return lines;
    }

}

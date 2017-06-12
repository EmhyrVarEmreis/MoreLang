package xyz.morecraft.dev.lang.morelang.object.statement.definition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import xyz.morecraft.dev.lang.morelang.object.Type;
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
            final Type typeCopy = Type.of(getTypedIdentifier().getType());
            typeCopy.setPointer(false);
            lines.addAll(content.llvm(functionContextRegistry, typeCopy, this, null));
            lines.add("store " + getTypedIdentifier().getType().getSimpleType().getLlvm() + " " + content.getAlias() + ", " + getTypedIdentifier().getType().getSimpleType().getLlvm() + "* %" + getTypedIdentifier().name() + ", align 4");
        }

        return lines;
    }

}

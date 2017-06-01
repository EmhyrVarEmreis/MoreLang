package xyz.morecraft.dev.lang.morelang.object.statement.definition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import xyz.morecraft.dev.lang.morelang.object.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class GlobalVariableDefinition extends Definition {

    private String value;

    public GlobalVariableDefinition(TypedIdentifier typedIdentifier, String value) {
        super(typedIdentifier);
        this.value = value;
    }

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry) {
        final List<String> lines = new ArrayList<>();

        lines.add("@" + getTypedIdentifier().getName() + " " + getTypedIdentifier().getType().getSimpleType().getLlvm() + " " + value + ", align 4");

        return lines;
    }

}

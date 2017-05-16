package xyz.morecraft.dev.lang.morelang.object;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class VariableDefinition extends Definition {

    public VariableDefinition(TypedIdentifier typedIdentifier) {
        super(typedIdentifier);
    }
}

package xyz.morecraft.dev.lang.morelang.object;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class FunctionDefinition extends Definition {

    public FunctionDefinition(TypedIdentifier typedIdentifier, List<FunctionArgument> argumentList) {
        super(typedIdentifier);
        this.argumentList = argumentList;
    }

    private List<FunctionArgument> argumentList;

}

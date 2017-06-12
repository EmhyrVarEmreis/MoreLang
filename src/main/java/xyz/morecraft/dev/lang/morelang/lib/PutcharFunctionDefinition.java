package xyz.morecraft.dev.lang.morelang.lib;

import xyz.morecraft.dev.lang.morelang.object.SimpleType;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.FunctionDefinition;

import java.util.Collections;

public class PutcharFunctionDefinition extends FunctionDefinition {

    public PutcharFunctionDefinition() {
        super(
                new TypedIdentifier("putchar", new Type(SimpleType.INT, false, false)),
                Collections.singletonList(new TypedIdentifier("putchar", new Type(SimpleType.INT, false, false))),
                Collections.emptyList(),
                null
        );
        this.setInternal(true);
        this.setRawHeader("declare i32 @putchar(i32)");
        this.setPreparedHeader("@putchar");
    }

}

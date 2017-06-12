package xyz.morecraft.dev.lang.morelang.lib;

import xyz.morecraft.dev.lang.morelang.object.SimpleType;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.FunctionDefinition;

import java.util.Collections;

public class GetcharFunctionDefinition extends FunctionDefinition {

    public GetcharFunctionDefinition() {
        super(
                new TypedIdentifier("getchar", new Type(SimpleType.INT, false, false)),
                Collections.emptyList(),
                Collections.emptyList(),
                null
        );
        this.setInternal(true);
        this.setRawHeader("declare i32 @getchar()");
        this.setPreparedHeader("@getchar");
    }

}

package xyz.morecraft.dev.lang.morelang.object.statement.definition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.object.statement.Statement;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class Definition extends Statement {

    private TypedIdentifier typedIdentifier;

}

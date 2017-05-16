package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;

@Data
@AllArgsConstructor
public class TypedIdentifier {

    private String name;
    private Type type;

    public static TypedIdentifier of(MoreLangGrammarParser.TypedIdentifierContext context) {
        return new TypedIdentifier(
                context.identifier().getText(),
                Type.of(
                        context.type().getText().toUpperCase()
                )
        );
    }

}

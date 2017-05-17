package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

public class TypedIdentifierVisitor extends MoreLangGrammarBaseVisitorCustom<TypedIdentifier> {

    @Override
    public TypedIdentifier visitTypedIdentifier(MoreLangGrammarParser.TypedIdentifierContext ctx) {
        return new TypedIdentifier(
                ctx.identifier().getText(),
                Type.of(
                        ctx.type().getText().toUpperCase()
                )
        );
    }

}

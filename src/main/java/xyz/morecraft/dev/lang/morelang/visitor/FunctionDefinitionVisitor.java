package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.FunctionDefinition;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

public class FunctionDefinitionVisitor extends MoreLangGrammarBaseVisitorCustom<FunctionDefinition> {

    @Override
    public FunctionDefinition visitFunctionDefinition(MoreLangGrammarParser.FunctionDefinitionContext ctx) {
        MoreLangGrammarParser.FunctionDefinitionHeaderContext header = ctx.functionDefinitionHeader();
        MoreLangGrammarParser.TypedIdentifierContext typedIdentifier = header.typedIdentifier();
        return new FunctionDefinition(
                new TypedIdentifier(
                        typedIdentifier.identifier().getText(),
                        Type.of(
                                typedIdentifier.type().getText().toUpperCase()
                        )
                ),
                null
        );
    }

}

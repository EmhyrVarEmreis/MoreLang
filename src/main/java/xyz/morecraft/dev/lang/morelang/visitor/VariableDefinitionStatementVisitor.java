package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.object.VariableDefinition;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

public class VariableDefinitionStatementVisitor extends MoreLangGrammarBaseVisitorCustom<VariableDefinition> {

    @Override
    public VariableDefinition visitVariableDefinitionStatement(MoreLangGrammarParser.VariableDefinitionStatementContext ctx) {
        MoreLangGrammarParser.TypedIdentifierContext typedIdentifierContext = ctx.typedIdentifier();
        return new VariableDefinition(
                new TypedIdentifier(
                        typedIdentifierContext.identifier().getText(),
                        Type.of(typedIdentifierContext.type().getText())
                )
        );
    }

}

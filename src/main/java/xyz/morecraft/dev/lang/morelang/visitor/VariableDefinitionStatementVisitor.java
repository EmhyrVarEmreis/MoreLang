package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.VariableDefinition;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

public class VariableDefinitionStatementVisitor extends MoreLangGrammarBaseVisitorCustom<VariableDefinition> {

    @Override
    public VariableDefinition visitVariableDefinitionStatement(MoreLangGrammarParser.VariableDefinitionStatementContext ctx) {
        TypedIdentifierVisitor typedIdentifierVisitor = new TypedIdentifierVisitor();
        return new VariableDefinition(
                ctx.typedIdentifier().accept(typedIdentifierVisitor)
        );
    }

}

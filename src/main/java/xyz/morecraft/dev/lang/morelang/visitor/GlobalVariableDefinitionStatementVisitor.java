package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.GlobalVariableDefinition;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.VariableDefinition;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

import java.util.Objects;

public class GlobalVariableDefinitionStatementVisitor extends MoreLangGrammarBaseVisitorCustom<GlobalVariableDefinition> {

    @Override
    public GlobalVariableDefinition visitGlobalVariableDefinitionStatement(MoreLangGrammarParser.GlobalVariableDefinitionStatementContext ctx) {
        TypedIdentifierVisitor typedIdentifierVisitor = new TypedIdentifierVisitor();
        return new GlobalVariableDefinition(
                ctx.typedIdentifier().accept(typedIdentifierVisitor),
                ctx.value().getText()
        );
    }

}

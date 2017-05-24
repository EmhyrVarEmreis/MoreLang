package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.VariableDefinition;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

import java.util.Objects;

public class VariableDefinitionStatementVisitor extends MoreLangGrammarBaseVisitorCustom<VariableDefinition> {

    @Override
    public VariableDefinition visitVariableDefinitionStatement(MoreLangGrammarParser.VariableDefinitionStatementContext ctx) {
        TypedIdentifierVisitor typedIdentifierVisitor = new TypedIdentifierVisitor();
        ExpressionVisitor expressionVisitor = new ExpressionVisitor();
        MoreLangGrammarParser.ExpressionContext expression = ctx.expression();
        return new VariableDefinition(
                ctx.typedIdentifier().accept(typedIdentifierVisitor),
                Objects.isNull(expression) ? null : expression.accept(expressionVisitor)
        );
    }

}

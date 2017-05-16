package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.Variable;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

import java.util.Objects;

public class VariableVisitor extends MoreLangGrammarBaseVisitorCustom<Variable> {

    @Override
    public Variable visitVariable(MoreLangGrammarParser.VariableContext ctx) {
        ExpressionVisitor expressionVisitor = new ExpressionVisitor();
        MoreLangGrammarParser.ExpressionContext expression = ctx.expression();
        return new Variable(
                ctx.identifier().getText(),
                Objects.isNull(expression) ? null : expression.accept(expressionVisitor)
        );
    }

}

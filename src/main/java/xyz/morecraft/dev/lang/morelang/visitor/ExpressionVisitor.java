package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.Expression;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

public class ExpressionVisitor extends MoreLangGrammarBaseVisitorCustom<Expression> {

    @Override
    public Expression visitExpression(MoreLangGrammarParser.ExpressionContext ctx) {
        return new Expression(ctx.getText());
    }

}

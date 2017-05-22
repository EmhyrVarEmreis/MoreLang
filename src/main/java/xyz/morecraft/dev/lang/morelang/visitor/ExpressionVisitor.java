package xyz.morecraft.dev.lang.morelang.visitor;

import org.antlr.v4.runtime.tree.TerminalNode;
import xyz.morecraft.dev.lang.morelang.MoreLangGrammarLexer;
import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.Expression;
import xyz.morecraft.dev.lang.morelang.object.Operator;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

public class ExpressionVisitor extends MoreLangGrammarBaseVisitorCustom<Expression> {

    @Override
    public Expression visitSmallExpression(MoreLangGrammarParser.SmallExpressionContext ctx) {
        return new Expression(null, null, null, ctx.getText(), null);
    }

    @Override
    public Expression visitParenExpression(MoreLangGrammarParser.ParenExpressionContext ctx) {
        return ctx.expression().accept(this);
    }

    @Override
    public Expression visitBiExpression(MoreLangGrammarParser.BiExpressionContext ctx) {
        return new Expression(
                ctx.expression(0).accept(this),
                ctx.expression(1).accept(this),
                Operator.valueOf(MoreLangGrammarLexer.ruleNames[TerminalNode.class.cast(ctx.operator().getChild(0)).getSymbol().getType() - 1].substring(3)),
                null,
                null
        );
    }

}

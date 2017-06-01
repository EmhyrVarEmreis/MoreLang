package xyz.morecraft.dev.lang.morelang.visitor;

import org.antlr.v4.runtime.tree.TerminalNode;
import xyz.morecraft.dev.lang.morelang.MoreLangGrammarLexer;
import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.Operator;
import xyz.morecraft.dev.lang.morelang.object.expression.*;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

import java.util.Objects;

public class ExpressionVisitor extends MoreLangGrammarBaseVisitorCustom<Expression> {

    @Override
    public Expression visitSmallExpression(MoreLangGrammarParser.SmallExpressionContext ctx) {
        MoreLangGrammarParser.AtomicExpressionContext atomicExpressionContext = ctx.atomicExpression();
        if (Objects.nonNull(atomicExpressionContext.value())) {
            return new ValueExpression(
                    atomicExpressionContext.value().getText()
            );
        } else if (Objects.nonNull(atomicExpressionContext.variable())) {
            return new VariableExpression(
                    atomicExpressionContext.variable().accept(new VariableVisitor())
            );
        } else if (Objects.nonNull(atomicExpressionContext.functionInvocationStatement())) {
            return new FunctionInvocationExpression(
                    atomicExpressionContext.functionInvocationStatement().accept(new FunctionInvocationStatementVisitor())
            );
        } else {
            throw new IllegalStateException("Compiler encountered serious error");
        }
    }

    @Override
    public Expression visitParenExpression(MoreLangGrammarParser.ParenExpressionContext ctx) {
        return ctx.expression().accept(this);
    }

    @Override
    public Expression visitBiExpression(MoreLangGrammarParser.BiExpressionContext ctx) {
        return new BiExpression(
                ctx.expression(0).accept(this),
                ctx.expression(1).accept(this),
                Operator.valueOf(MoreLangGrammarLexer.ruleNames[TerminalNode.class.cast(ctx.operator().getChild(0).getChild(0)).getSymbol().getType() - 1].substring(3))
        );
    }

}

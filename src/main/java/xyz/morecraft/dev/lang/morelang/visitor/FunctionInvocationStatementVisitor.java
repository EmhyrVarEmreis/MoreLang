package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.statement.FunctionInvocationStatement;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

public class FunctionInvocationStatementVisitor extends MoreLangGrammarBaseVisitorCustom<FunctionInvocationStatement> {

    @Override
    public FunctionInvocationStatement visitFunctionInvocationStatement(MoreLangGrammarParser.FunctionInvocationStatementContext ctx) {
        final FunctionInvocationStatement statement = new FunctionInvocationStatement(
                ctx.identifier().getText(),
                convert(ctx.functionInvocationArguments().expression(), new ExpressionVisitor())
        );
        statement.setLine(ctx.getStart().getLine());
        statement.setPos(ctx.getStart().getCharPositionInLine());
        return statement;
    }

}

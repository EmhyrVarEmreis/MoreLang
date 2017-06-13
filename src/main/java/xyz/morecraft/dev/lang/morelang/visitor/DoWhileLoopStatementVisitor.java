package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.statement.WhileLoopStatement;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

public class DoWhileLoopStatementVisitor extends MoreLangGrammarBaseVisitorCustom<WhileLoopStatement> {

    @Override
    public WhileLoopStatement visitDoWhileLoopStatement(MoreLangGrammarParser.DoWhileLoopStatementContext ctx) {
        ExpressionVisitor expressionVisitor = new ExpressionVisitor();
        StatementVisitor statementVisitor = new StatementVisitor();
        return new WhileLoopStatement(
                ctx.expression().accept(expressionVisitor),
                convert(ctx.bodyBlock().body().statement(), statementVisitor),
                true
        );
    }

}

package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.statement.IfStatement;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

import java.util.List;

public class IfStatementVisitor extends MoreLangGrammarBaseVisitorCustom<IfStatement> {

    @Override
    public IfStatement visitIfStatement(MoreLangGrammarParser.IfStatementContext ctx) {
        ExpressionVisitor expressionVisitor = new ExpressionVisitor();
        StatementVisitor statementVisitor = new StatementVisitor();
        final List<MoreLangGrammarParser.BodyBlockContext> bodyBlockContexts = ctx.bodyBlock();
        return new IfStatement(
                ctx.expression().accept(expressionVisitor),
                convert(bodyBlockContexts.get(0).body().statement(), statementVisitor),
                bodyBlockContexts.size() > 1 ? convert(bodyBlockContexts.get(1).body().statement(), statementVisitor) : null
        );
    }

}

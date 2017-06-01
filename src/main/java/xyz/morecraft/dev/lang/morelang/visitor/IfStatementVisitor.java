package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.statement.IfStatement;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class IfStatementVisitor extends MoreLangGrammarBaseVisitorCustom<IfStatement> {

    @Override
    public IfStatement visitIfStatement(MoreLangGrammarParser.IfStatementContext ctx) {
        ExpressionVisitor expressionVisitor = new ExpressionVisitor();
        StatementVisitor statementVisitor = new StatementVisitor();
        final List<MoreLangGrammarParser.BodyBlockContext> bodyBlockContexts = ctx.bodyBlock();
        final MoreLangGrammarParser.IfStatementContext obj = ctx.ifStatement();
        return new IfStatement(
                ctx.expression().accept(expressionVisitor),
                convert(bodyBlockContexts.get(0).body().statement(), statementVisitor),
                bodyBlockContexts.size() > 1 ? convert(bodyBlockContexts.get(1).body().statement(), statementVisitor) : (Objects.isNull(obj) ? null : Collections.singletonList(obj.accept(this)))
        );
    }

}

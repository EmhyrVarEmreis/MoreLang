package xyz.morecraft.dev.lang.morelang.visitor;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.statement.Statement;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatementVisitor extends MoreLangGrammarBaseVisitorCustom<Statement> {

    @Override
    public Statement visitStatement(MoreLangGrammarParser.StatementContext ctx) {
        List<ParseTreeVisitor<? extends Statement>> visitorList = new ArrayList<>();
        visitorList.add(new VariableDefinitionStatementVisitor());
        visitorList.add(new AssignmentStatementVisitor());
        visitorList.add(new FunctionInvocationStatementVisitor());
        visitorList.add(new IfStatementVisitor());
        visitorList.add(new WhileLoopStatementVisitor());
        for (ParserRuleContext parserRuleContext : ctx.getRuleContexts(ParserRuleContext.class)) {
            for (ParseTreeVisitor<? extends Statement> visitor : visitorList) {
                Statement statement = parserRuleContext.accept(visitor);
                if (Objects.nonNull(statement)) {
                    return statement;
                }
            }
        }
        return super.visitStatement(ctx);
    }

}

package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.AssignmentStatement;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

public class AssignmentStatementVisitor extends MoreLangGrammarBaseVisitorCustom<AssignmentStatement> {

    @Override
    public AssignmentStatement visitAssignmentStatement(MoreLangGrammarParser.AssignmentStatementContext ctx) {
        ExpressionVisitor expressionVisitor = new ExpressionVisitor();
        VariableVisitor variableVisitor = new VariableVisitor();
        return new AssignmentStatement(
                ctx.variable().accept(variableVisitor),
                ctx.expression().accept(expressionVisitor)
        );
    }

}

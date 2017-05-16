package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.FunctionInvocationStatement;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

public class FunctionInvocationStatementVisitor extends MoreLangGrammarBaseVisitorCustom<FunctionInvocationStatement> {

    @Override
    public FunctionInvocationStatement visitFunctionInvocationStatement(MoreLangGrammarParser.FunctionInvocationStatementContext ctx) {
        return new FunctionInvocationStatement(
                ctx.identifier().getText(),
                convert(ctx.functionInvocationArguments().variable(), new VariableVisitor())
        );
    }

}

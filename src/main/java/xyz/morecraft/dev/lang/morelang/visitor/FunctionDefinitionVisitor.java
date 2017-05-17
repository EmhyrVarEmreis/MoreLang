package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.FunctionDefinition;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

public class FunctionDefinitionVisitor extends MoreLangGrammarBaseVisitorCustom<FunctionDefinition> {

    @Override
    public FunctionDefinition visitFunctionDefinition(MoreLangGrammarParser.FunctionDefinitionContext ctx) {
        StatementVisitor statementVisitor = new StatementVisitor();
        ExpressionVisitor expressionVisitor = new ExpressionVisitor();
        TypedIdentifierVisitor typedIdentifierVisitor = new TypedIdentifierVisitor();
        MoreLangGrammarParser.FunctionDefinitionHeaderContext header = ctx.functionDefinitionHeader();
        return new FunctionDefinition(
                header.typedIdentifier().accept(typedIdentifierVisitor),
                convert(header.functionDefinitionHeaderArguments().typedIdentifier(), typedIdentifierVisitor),
                convert(ctx.body().statement(), statementVisitor),
                ctx.returnStatement().expression().accept(expressionVisitor)
        );
    }

}

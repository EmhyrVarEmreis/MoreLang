package xyz.morecraft.dev.lang.morelang;

import lombok.Getter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import xyz.morecraft.dev.lang.morelang.object.Program;

public class MoreLangCustomGrammarListener implements MoreLangGrammarListener {

    @Getter
    private Program program;

    public MoreLangCustomGrammarListener() {
        this.program = new Program();
    }

    @Override
    public void enterProgram(MoreLangGrammarParser.ProgramContext ctx) {

    }

    @Override
    public void exitProgram(MoreLangGrammarParser.ProgramContext ctx) {

    }

    @Override
    public void enterBody(MoreLangGrammarParser.BodyContext ctx) {

    }

    @Override
    public void exitBody(MoreLangGrammarParser.BodyContext ctx) {

    }

    @Override
    public void enterBodyBlock(MoreLangGrammarParser.BodyBlockContext ctx) {

    }

    @Override
    public void exitBodyBlock(MoreLangGrammarParser.BodyBlockContext ctx) {

    }

    @Override
    public void enterProgramHeader(MoreLangGrammarParser.ProgramHeaderContext ctx) {

    }

    @Override
    public void exitProgramHeader(MoreLangGrammarParser.ProgramHeaderContext ctx) {

    }

    @Override
    public void enterCtrlModuleName(MoreLangGrammarParser.CtrlModuleNameContext ctx) {

    }

    @Override
    public void exitCtrlModuleName(MoreLangGrammarParser.CtrlModuleNameContext ctx) {

    }

    @Override
    public void enterCtrlImport(MoreLangGrammarParser.CtrlImportContext ctx) {

    }

    @Override
    public void exitCtrlImport(MoreLangGrammarParser.CtrlImportContext ctx) {

    }

    @Override
    public void enterStatement(MoreLangGrammarParser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(MoreLangGrammarParser.StatementContext ctx) {

    }

    @Override
    public void enterIfStatement(MoreLangGrammarParser.IfStatementContext ctx) {

    }

    @Override
    public void exitIfStatement(MoreLangGrammarParser.IfStatementContext ctx) {

    }

    @Override
    public void enterWhileLoop(MoreLangGrammarParser.WhileLoopContext ctx) {

    }

    @Override
    public void exitWhileLoop(MoreLangGrammarParser.WhileLoopContext ctx) {

    }

    @Override
    public void enterDefinition(MoreLangGrammarParser.DefinitionContext ctx) {

    }

    @Override
    public void exitDefinition(MoreLangGrammarParser.DefinitionContext ctx) {

    }

    @Override
    public void enterVariableDefinition(MoreLangGrammarParser.VariableDefinitionContext ctx) {

    }

    @Override
    public void exitVariableDefinition(MoreLangGrammarParser.VariableDefinitionContext ctx) {

    }

    @Override
    public void enterValue(MoreLangGrammarParser.ValueContext ctx) {

    }

    @Override
    public void exitValue(MoreLangGrammarParser.ValueContext ctx) {

    }

    @Override
    public void enterReturnStatement(MoreLangGrammarParser.ReturnStatementContext ctx) {

    }

    @Override
    public void exitReturnStatement(MoreLangGrammarParser.ReturnStatementContext ctx) {

    }

    @Override
    public void enterExpression(MoreLangGrammarParser.ExpressionContext ctx) {

    }

    @Override
    public void exitExpression(MoreLangGrammarParser.ExpressionContext ctx) {

    }

    @Override
    public void enterArithmeticExpression(MoreLangGrammarParser.ArithmeticExpressionContext ctx) {

    }

    @Override
    public void exitArithmeticExpression(MoreLangGrammarParser.ArithmeticExpressionContext ctx) {

    }

    @Override
    public void enterSimpleExpression(MoreLangGrammarParser.SimpleExpressionContext ctx) {

    }

    @Override
    public void exitSimpleExpression(MoreLangGrammarParser.SimpleExpressionContext ctx) {

    }

    @Override
    public void enterAtomicExpression(MoreLangGrammarParser.AtomicExpressionContext ctx) {

    }

    @Override
    public void exitAtomicExpression(MoreLangGrammarParser.AtomicExpressionContext ctx) {

    }

    @Override
    public void enterAssigment(MoreLangGrammarParser.AssigmentContext ctx) {

    }

    @Override
    public void exitAssigment(MoreLangGrammarParser.AssigmentContext ctx) {

    }

    @Override
    public void enterFunctionDefinition(MoreLangGrammarParser.FunctionDefinitionContext ctx) {

    }

    @Override
    public void exitFunctionDefinition(MoreLangGrammarParser.FunctionDefinitionContext ctx) {

    }

    @Override
    public void enterFunctionInvocation(MoreLangGrammarParser.FunctionInvocationContext ctx) {

    }

    @Override
    public void exitFunctionInvocation(MoreLangGrammarParser.FunctionInvocationContext ctx) {

    }

    @Override
    public void enterFunctionHeader(MoreLangGrammarParser.FunctionHeaderContext ctx) {

    }

    @Override
    public void exitFunctionHeader(MoreLangGrammarParser.FunctionHeaderContext ctx) {

    }

    @Override
    public void enterFunctionArgs(MoreLangGrammarParser.FunctionArgsContext ctx) {

    }

    @Override
    public void exitFunctionArgs(MoreLangGrammarParser.FunctionArgsContext ctx) {

    }

    @Override
    public void enterIdentifier(MoreLangGrammarParser.IdentifierContext ctx) {

    }

    @Override
    public void exitIdentifier(MoreLangGrammarParser.IdentifierContext ctx) {

    }

    @Override
    public void enterType(MoreLangGrammarParser.TypeContext ctx) {

    }

    @Override
    public void exitType(MoreLangGrammarParser.TypeContext ctx) {

    }

    @Override
    public void enterOperator(MoreLangGrammarParser.OperatorContext ctx) {

    }

    @Override
    public void exitOperator(MoreLangGrammarParser.OperatorContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }

}

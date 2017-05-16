package xyz.morecraft.dev.lang.morelang;

import lombok.Getter;
import xyz.morecraft.dev.lang.morelang.object.*;

import java.util.HashSet;
import java.util.Set;

public class MoreLangCustomGrammarListener extends MoreLangGrammarBaseListener {

    @Getter
    private Program program;

    private Set<String> identifierSet;

    @Getter
    private boolean isError;

    public MoreLangCustomGrammarListener() {
        this.program = new Program();
        this.identifierSet = new HashSet<>();
        this.isError = false;
    }

    private void addError(String message) {
        isError = true;
        System.err.println("Parser: " + message);
    }

    @Override
    public void exitCtrlImportName(MoreLangGrammarParser.CtrlImportNameContext ctx) {
        program.getProgramHeader().addImport(ctx.getText());
    }

    @Override
    public void exitVariableDefinitionStatement(MoreLangGrammarParser.VariableDefinitionStatementContext ctx) {
        MoreLangGrammarParser.TypedIdentifierContext typedIdentifier = ctx.typedIdentifier();
        program.addStatement(new VariableDefinition(new TypedIdentifier(typedIdentifier.identifier().getText(), Type.of(typedIdentifier.type().getText()))));
    }

    @Override
    public void exitFunctionDefinition(MoreLangGrammarParser.FunctionDefinitionContext ctx) {
        for (MoreLangGrammarParser.IdentifierContext identifierContext : ctx.getRuleContexts(MoreLangGrammarParser.IdentifierContext.class)) {
            System.out.println(identifierContext.getText());
        }
        program.addStatement(new FunctionDefinition(new TypedIdentifier(null, null), null));
    }

    @Override
    public void exitIdentifier(MoreLangGrammarParser.IdentifierContext ctx) {
        if (!identifierSet.add(ctx.getText())) {
//            addError("Duplicated ID: " + ctx.getText());
        }
    }

}

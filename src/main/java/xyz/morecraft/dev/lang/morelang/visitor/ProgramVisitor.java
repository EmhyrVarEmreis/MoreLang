package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.Program;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

public class ProgramVisitor extends MoreLangGrammarBaseVisitorCustom<Program> {

    @Override
    public Program visitProgram(MoreLangGrammarParser.ProgramContext ctx) {
        return new Program(
                ctx.programHeader().accept(new ProgramHeaderVisitor()),
                convert(ctx.programBody().statement(), new StatementVisitor()),
                convert(ctx.programBody().functionDefinition(), new FunctionDefinitionVisitor())
        );
    }

}

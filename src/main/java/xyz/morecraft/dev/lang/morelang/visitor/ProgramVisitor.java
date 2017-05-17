package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.Program;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

import java.util.Objects;

public class ProgramVisitor extends MoreLangGrammarBaseVisitorCustom<Program> {

    @Override
    public Program visitProgram(MoreLangGrammarParser.ProgramContext ctx) {
        MoreLangGrammarParser.ProgramHeaderContext programHeader = ctx.programHeader();
        return new Program(
                Objects.isNull(programHeader) ? null : programHeader.accept(new ProgramHeaderVisitor()),
                convert(ctx.programBody().functionDefinition(), new FunctionDefinitionVisitor())
        );
    }

}

package xyz.morecraft.dev.lang.morelang.visitor;

import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;
import xyz.morecraft.dev.lang.morelang.object.ProgramHeader;
import xyz.morecraft.dev.lang.morelang.visitor.proto.MoreLangGrammarBaseVisitorCustom;

public class ProgramHeaderVisitor extends MoreLangGrammarBaseVisitorCustom<ProgramHeader> {

    @Override
    public ProgramHeader visitProgramHeader(MoreLangGrammarParser.ProgramHeaderContext ctx) {
        return new ProgramHeader(
                convert(ctx.ctrlImport(), ctrlImportContext -> ctrlImportContext.ctrlImportName().getText())
        );
    }

}

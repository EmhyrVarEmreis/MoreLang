package xyz.morecraft.dev.lang.morelang;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import xyz.morecraft.dev.lang.morelang.object.Program;
import xyz.morecraft.dev.lang.morelang.visitor.ProgramVisitor;

import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception {
        MoreLangGrammarLexer lexer = new MoreLangGrammarLexer(new ANTLRInputStream(new FileReader(args[0])));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MoreLangGrammarParser parser = new MoreLangGrammarParser(tokens);
        MoreLangCustomGrammarListener listener = new MoreLangCustomGrammarListener();
        parser.addParseListener(listener);
//        parser.program();

        if (parser.getNumberOfSyntaxErrors() > 0 || listener.isError()) {
            System.err.println("Aborting due to errors");
            return;
        }

        Program program = new ProgramVisitor().visit(parser.program());
        System.out.println(program.toString());
    }

}

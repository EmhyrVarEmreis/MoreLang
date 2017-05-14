package xyz.morecraft.dev.lang.morelang;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import xyz.morecraft.dev.lang.morelang.object.Program;

import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception {
        MoreLangGrammarLexer lexer = new MoreLangGrammarLexer(new ANTLRInputStream(new FileReader(args[0])));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MoreLangGrammarParser parser = new MoreLangGrammarParser(tokens);
        MoreLangCustomGrammarListener listener = new MoreLangCustomGrammarListener();
        parser.addParseListener(listener);
        parser.program();
        Program program = listener.getProgram();
        System.out.println(program.toString());
    }

}

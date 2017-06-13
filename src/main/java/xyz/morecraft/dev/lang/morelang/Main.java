package xyz.morecraft.dev.lang.morelang;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import xyz.morecraft.dev.lang.morelang.exception.MoreLangParseException;
import xyz.morecraft.dev.lang.morelang.object.Program;
import xyz.morecraft.dev.lang.morelang.visitor.ProgramVisitor;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Parsing...");
        MoreLangGrammarLexer lexer = new MoreLangGrammarLexer(new ANTLRInputStream(new FileReader(args[0])));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MoreLangGrammarParser parser = new MoreLangGrammarParser(tokens);
        Program program = new ProgramVisitor().visit(parser.program());

        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.err.println("Aborting due to parse errors");
            return;
        }

        System.out.println("Compiling to LLVM...");

        try {
            String llvm = program.llvm().stream().collect(Collectors.joining("\n"));

            System.out.println(llvm);

            Files.write(Paths.get(args[0].replace(".morelang", "") + ".ll"), Collections.singletonList(llvm));
        } catch (MoreLangParseException e) {
            System.err.println(e.getMessage());
            System.err.println("Compilation aborted due to errors");
        }
    }

}

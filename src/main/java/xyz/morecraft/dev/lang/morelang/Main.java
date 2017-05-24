package xyz.morecraft.dev.lang.morelang;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import xyz.morecraft.dev.lang.morelang.object.Program;
import xyz.morecraft.dev.lang.morelang.visitor.ProgramVisitor;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws Exception {
        MoreLangGrammarLexer lexer = new MoreLangGrammarLexer(new ANTLRInputStream(new FileReader(args[0])));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MoreLangGrammarParser parser = new MoreLangGrammarParser(tokens);
//        parser.program();

        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.err.println("Aborting due to errors");
            return;
        }

        Program program = new ProgramVisitor().visit(parser.program());
        System.out.println(program.toString());

        System.out.println();

        System.out.println(
                new ObjectMapper()
                        .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(program)
        );

        System.out.println();

        String llvm = program.llvm();

        System.out.println(llvm);

        Files.write(Paths.get(args[0].replace(".morelang", "") + ".ll"), Collections.singletonList(llvm));
    }

}

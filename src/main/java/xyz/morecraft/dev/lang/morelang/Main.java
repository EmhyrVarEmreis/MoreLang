package xyz.morecraft.dev.lang.morelang;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import xyz.morecraft.dev.lang.morelang.exception.MoreLangParseException;
import xyz.morecraft.dev.lang.morelang.object.Program;
import xyz.morecraft.dev.lang.morelang.visitor.ProgramVisitor;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    private static final Pattern regex = Pattern.compile("^(?:\\s*import\\s+[a-zA-Z][a-zA-Z0-9]*(?:\\s*\\.\\s*[a-zA-Z][a-zA-Z0-9]*)*\\s*;)*");
    private static final Pattern regex2 = Pattern.compile("\\s*import\\s+([a-zA-Z][a-zA-Z0-9]*(?:\\s*\\.\\s*[a-zA-Z][a-zA-Z0-9]*)*)\\s*;");
    private static final String separator = "/";
    private static final String ext = ".morelang";

    public static void main(String[] args) throws Exception {

        args[0] = args[0].replaceAll("\\\\", separator);

        StringBuilder content = new StringBuilder();
        Queue<String> filesToLoad = new LinkedList<>();
        filesToLoad.add(args[0]);
        String path = args[0].substring(0, args[0].lastIndexOf(separator));
        while (!filesToLoad.isEmpty()) {
            final String poll = filesToLoad.poll();
            System.out.println("Linking: " + poll);
            String fileContent = new String(Files.readAllBytes(Paths.get(poll)));
            Matcher m = regex.matcher(fileContent);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                String s = m.group();
                Matcher mm = regex2.matcher(s);
                while (mm.find()) {
                    filesToLoad.add(path + separator + mm.group(1).replaceAll("\\.", separator) + ext);
                }
                m.appendReplacement(sb, "");
            }
            m.appendTail(sb);
            content.insert(0, sb);
        }

        System.out.println("Parsing...");
        MoreLangGrammarLexer lexer = new MoreLangGrammarLexer(new ANTLRInputStream(content.toString()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MoreLangGrammarParser parser = new MoreLangGrammarParser(tokens);
        Program program = new ProgramVisitor().visit(parser.program());

        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.err.println("Aborting due to parse errors");
            return;
        }

        System.out.println("Compiling to LLVM...");

        try {
            final String llvm = program.llvm().stream().collect(Collectors.joining("\n"));
            final String llvmFile = args[0].replace(ext, "") + ".ll";
            Files.write(Paths.get(llvmFile), Collections.singletonList(llvm));
            System.out.println("Compiling executable...");
            Runtime.getRuntime().exec("clang " + llvmFile);
        } catch (MoreLangParseException e) {
            System.err.println(e.getMessage());
            System.err.println("Compilation aborted due to errors");
        }
    }

}

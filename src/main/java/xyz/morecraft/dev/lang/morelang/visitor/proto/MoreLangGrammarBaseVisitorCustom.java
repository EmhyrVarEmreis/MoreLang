package xyz.morecraft.dev.lang.morelang.visitor.proto;


import org.antlr.v4.runtime.ParserRuleContext;
import xyz.morecraft.dev.lang.morelang.MoreLangGrammarBaseVisitor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class MoreLangGrammarBaseVisitorCustom<T> extends MoreLangGrammarBaseVisitor<T> {

    protected <R extends ParserRuleContext, S> List<S> convert(List<R> parserRuleContextList, MoreLangGrammarBaseVisitor<S> visitor) {
        return parserRuleContextList.stream().map(context -> context.accept(visitor)).collect(Collectors.toList());
    }

    protected <R extends ParserRuleContext, G> List<G> convert(List<R> parserRuleContextList, Function<R, G> converter) {
        return parserRuleContextList.stream().map(converter).collect(Collectors.toList());
    }

}

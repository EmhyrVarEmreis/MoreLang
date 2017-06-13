package xyz.morecraft.dev.lang.morelang.exception;

import xyz.morecraft.dev.lang.morelang.object.ParsedElement;

public class UndefinedFunctionException extends MoreLangParseException {

    public UndefinedFunctionException(ParsedElement parsedElement, String content) {
        super(parsedElement, content, UndefinedFunctionException.class);
    }

}

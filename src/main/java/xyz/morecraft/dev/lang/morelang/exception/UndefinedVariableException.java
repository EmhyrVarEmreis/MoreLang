package xyz.morecraft.dev.lang.morelang.exception;

import xyz.morecraft.dev.lang.morelang.object.ParsedElement;

public class UndefinedVariableException extends MoreLangParseException {

    public UndefinedVariableException(ParsedElement parsedElement, String content) {
        super(parsedElement, content, UndefinedVariableException.class);
    }

}

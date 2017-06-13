package xyz.morecraft.dev.lang.morelang.exception;

import xyz.morecraft.dev.lang.morelang.object.ParsedElement;

public abstract class MoreLangParseException extends RuntimeException {

    private ParsedElement parsedElement;

    public MoreLangParseException(ParsedElement parsedElement, String content, Class<? extends MoreLangParseException> aClass) {
        super("line " + parsedElement.getLine() + ":" + parsedElement.getPos() + ": " + aClass.getSimpleName() + ": " + content);
    }

}

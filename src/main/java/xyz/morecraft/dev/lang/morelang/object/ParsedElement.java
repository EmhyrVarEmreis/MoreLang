package xyz.morecraft.dev.lang.morelang.object;

import lombok.Data;

@Data
public abstract class ParsedElement {

    private int line;
    private int pos;

}

package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Operator {

    PLUS("+", "add"), MINUS("-", "sub"), MULTIPLY("*", "mul"), COMPARE("=", null);

    @Getter
    private String op;

    @Getter
    private String llvm;

}

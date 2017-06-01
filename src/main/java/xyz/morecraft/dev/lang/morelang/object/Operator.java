package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Operator {

    PLUS("+", OperatorType.ARITHMETICAL, "add"),
    MINUS("-", OperatorType.ARITHMETICAL, "sub"),
    MULTIPLY("*", OperatorType.ARITHMETICAL, "mul"),
    DIVISION("/", OperatorType.ARITHMETICAL, "div"),
    COMPARE_EQ("==", OperatorType.LOGICAL, "eq"),
    COMPARE_NEQ("!=", OperatorType.LOGICAL, "neq"),
    COMPARE_GT(">", OperatorType.LOGICAL, "sgt"),
    COMPARE_LT("<", OperatorType.LOGICAL, "slt");

    @Getter
    private String op;

    @Getter
    private OperatorType type;

    @Getter
    private String llvm;

    public static enum OperatorType {
        ARITHMETICAL, LOGICAL, COMPARE
    }

}

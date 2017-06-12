package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public enum Operator {

    PLUS("+", OperatorType.ARITHMETICAL, "add"),
    MINUS("-", OperatorType.ARITHMETICAL, "sub"),
    MULTIPLY("*", OperatorType.ARITHMETICAL, "mul"),
    DIVISION("/", OperatorType.ARITHMETICAL, "div"),
    MODULO("%", OperatorType.ARITHMETICAL, "srem"),
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

    public Collection<? extends String> llvm(String alias, Type requiredType, String leftAlias, String rightAlias) {
        switch (this) {
            case PLUS:
            case MINUS:
            case MULTIPLY:
            case DIVISION:
                return Collections.singletonList(alias + " = " + llvm + " nsw " + requiredType.getSimpleType().getLlvm() + " " + leftAlias + ", " + rightAlias);
            case MODULO:
                return Collections.singletonList(alias + " = " + llvm + " " + requiredType.getSimpleType().getLlvm() + " " + leftAlias + ", " + rightAlias);
            case COMPARE_EQ:
            case COMPARE_NEQ:
            case COMPARE_GT:
            case COMPARE_LT:
                return Collections.singletonList(alias + " = icmp " + llvm + " " + requiredType.getSimpleType().getLlvm() + " " + leftAlias + ", " + rightAlias);
            default:
                throw new IllegalArgumentException("Unsupported operator");
        }
    }

    public static enum OperatorType {
        ARITHMETICAL, LOGICAL, COMPARE
    }

}

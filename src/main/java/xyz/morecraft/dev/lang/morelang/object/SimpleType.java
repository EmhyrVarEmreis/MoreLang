package xyz.morecraft.dev.lang.morelang.object;

import lombok.Getter;

public enum SimpleType {

    INT("i32"), FLOAT("float"), STRING("string"), BOOLEAN("i32");

    SimpleType(String llvm) {
        this.llvm = llvm;
    }

    @Getter
    private String llvm;

}

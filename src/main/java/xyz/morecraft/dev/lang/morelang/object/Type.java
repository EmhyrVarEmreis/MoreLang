package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Type {

    private SimpleType simpleType;
    private boolean array;
    private boolean pointer;

    public static Type of(Type t) {
        return new Type(t.simpleType, t.array, t.pointer);
    }

    public static Type of(String s) {
        if (s.contains("[]")) {
            return new Type(
                    SimpleType.valueOf(s.substring(0, s.length() - 2).toUpperCase()),
                    true,
                    false
            );
        } else {
            return new Type(
                    SimpleType.valueOf(s.toUpperCase()),
                    false,
                    false
            );
        }
    }

}

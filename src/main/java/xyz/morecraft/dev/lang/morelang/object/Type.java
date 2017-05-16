package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Type {

    private SimpleType simpleType;
    private boolean array;

    public static Type of(String s) {
        if (s.contains("[]")) {
            return new Type(
                    SimpleType.valueOf(s.substring(0, s.length() - 2).toUpperCase()),
                    true
            );
        } else {
            return new Type(
                    SimpleType.valueOf(s.toUpperCase()),
                    false
            );
        }
    }

}

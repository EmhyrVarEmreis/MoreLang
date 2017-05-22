package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expression {

    private Expression left;
    private Expression right;
    private Operator operator;
    private String content;
    private String alias;

    @SuppressWarnings("ConstantConditions")
    public boolean isAtomic() {
        return Objects.nonNull(left) && Objects.nonNull(right) && Objects.nonNull(operator);
    }

}

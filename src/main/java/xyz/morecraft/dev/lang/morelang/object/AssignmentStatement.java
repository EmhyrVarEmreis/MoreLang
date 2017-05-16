package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class AssignmentStatement extends Statement {

    private Variable variable;
    private Expression expression;

}

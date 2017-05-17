package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FunctionInvocationStatement extends Statement {

    private String name;
    private List<Variable> argumentExpressionList;

    @Override
    public String llvm() {
        return null;
    }

}

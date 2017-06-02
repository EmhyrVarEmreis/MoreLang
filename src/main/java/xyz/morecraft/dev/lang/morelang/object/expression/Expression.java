package xyz.morecraft.dev.lang.morelang.object.expression;

import lombok.Data;
import xyz.morecraft.dev.lang.morelang.object.registry.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.statement.Statement;

import java.util.List;
import java.util.Objects;

@Data
public abstract class Expression {

    private String alias;

    public abstract List<String> llvm(FunctionContextRegistry functionContextRegistry, Type requiredType, Statement statementContext, Expression expressionContext);

    protected String getRawAlias() {
        return Objects.isNull(alias) ? null : alias.replaceFirst("%", "");
    }

}

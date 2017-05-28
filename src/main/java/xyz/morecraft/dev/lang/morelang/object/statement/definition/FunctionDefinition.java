package xyz.morecraft.dev.lang.morelang.object.statement.definition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.morecraft.dev.lang.morelang.object.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.ProgramRegistry;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.object.expression.Expression;
import xyz.morecraft.dev.lang.morelang.object.statement.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public class FunctionDefinition extends Definition {

    private List<TypedIdentifier> argumentList;
    private List<Statement> statementList = new ArrayList<>();
    private Expression returnStatement;
    private FunctionContextRegistry functionContextRegistry;

    public FunctionDefinition(TypedIdentifier typedIdentifier, List<TypedIdentifier> argumentList, List<Statement> statementList, Expression returnStatement) {
        super(typedIdentifier);
        this.argumentList = Objects.isNull(argumentList) ? new ArrayList<>() : argumentList;
        this.statementList = Objects.isNull(statementList) ? new ArrayList<>() : statementList;
        this.returnStatement = returnStatement;
    }

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry) {
        throw new IllegalStateException("Illegal method");
    }

    public String llvm(ProgramRegistry programRegistry) {
        System.out.println("\n\nfun: " + getTypedIdentifier().getName());

        functionContextRegistry = new FunctionContextRegistry(this, programRegistry, this.argumentList);

        StringBuilder llvm = new StringBuilder();
        llvm.append("define ");
        llvm.append(getTypedIdentifier().getType().getSimpleType().getLlvm());
        llvm.append(" @");
        llvm.append(getTypedIdentifier().getName());
        llvm.append("(");
        if (Objects.nonNull(argumentList)) {
            llvm.append(
                    argumentList.stream().map(
                            typedIdentifier -> typedIdentifier.getType().getSimpleType().getLlvm() + " %" + typedIdentifier.getName()
                    ).collect(Collectors.joining(", "))
            );
        }
        llvm.append(") {\n");
//        llvm.append("entry: \n");
        llvm.append(
                generateFunctionBody().stream().map(
                        s -> "\t" + s + "\n"
                ).collect(Collectors.joining())
        );
        llvm.append("}\n\n");

        return llvm.toString();
    }

    private List<String> generateFunctionBody() {
        List<String> lines = new ArrayList<>();

        lines.addAll(generateInitArgLines());
        lines.addAll(generateStatements());
        lines.addAll(generateReturnStatement());

        return lines;
    }

    private List<String> generateInitArgLines() {
        List<String> lines = new ArrayList<>();

        for (TypedIdentifier typedIdentifier : argumentList) {
            functionContextRegistry.makeAlias(typedIdentifier);
        }

        for (TypedIdentifier var : argumentList) {
            lines.add(
                    "%" + functionContextRegistry.getAlias(var) + " = alloca " + var.getType().getSimpleType().getLlvm() + ", align 4"
            );
        }

        for (TypedIdentifier var : argumentList) {
            lines.add(
                    "store " + var.getType().getSimpleType().getLlvm() + " %" + var.getName() + ", " + var.getType().getSimpleType().getLlvm() + "* %" + functionContextRegistry.getAlias(var) + ", align 4"
            );
        }

        return lines;
    }

    private List<String> generateReturnStatement() {
        List<String> lines = new ArrayList<>();

        lines.addAll(returnStatement.llvm(functionContextRegistry, getTypedIdentifier().getType(), this, null));

        lines.add("ret " + getTypedIdentifier().getType().getSimpleType().getLlvm() + " " + returnStatement.getAlias());

        return lines;
    }

    private List<String> generateStatements() {
        List<String> lines = new ArrayList<>();

        for (Statement statement : statementList) {
            lines.addAll(statement.llvm(functionContextRegistry));
        }

        return lines;
    }

}

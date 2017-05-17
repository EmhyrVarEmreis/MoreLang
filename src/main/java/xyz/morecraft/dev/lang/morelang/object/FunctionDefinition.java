package xyz.morecraft.dev.lang.morelang.object;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
public class FunctionDefinition extends Definition {

    private List<TypedIdentifier> argumentList;
    private List<Statement> statementList = new ArrayList<>();
    private Expression returnStatement;
    private Map<TypedIdentifier, String> aliasesMap;
    private int temporaryVariableCounter = 0;

    public FunctionDefinition(TypedIdentifier typedIdentifier, List<TypedIdentifier> argumentList, List<Statement> statementList, Expression returnStatement) {
        super(typedIdentifier);
        this.argumentList = Objects.isNull(argumentList) ? new ArrayList<>() : argumentList;
        this.statementList = Objects.isNull(statementList) ? new ArrayList<>() : statementList;
        this.returnStatement = returnStatement;
        this.aliasesMap = new HashMap<>();
    }

    @Override
    public String llvm() {
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
        llvm.append(") {\nentry: \n");
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

        return lines;
    }

    private List<String> generateInitArgLines() {
        List<String> lines = new ArrayList<>();

        for (TypedIdentifier typedIdentifier : argumentList) {
            typedIdentifier.setAlias(getNextTemporaryVariableName());
            aliasesMap.put(typedIdentifier, typedIdentifier.getAlias());
        }

        for (TypedIdentifier var : argumentList) {
            lines.add(
                    "%" + getAlias(var) + " = alloca " + var.getType().getSimpleType().getLlvm() + ", align 4"
            );
        }

        for (TypedIdentifier var : argumentList) {
            lines.add(
                    "store " + var.getType().getSimpleType().getLlvm() + " %" + var.getName() + ", " + var.getType().getSimpleType().getLlvm() + "* %" + getAlias(var) + ", align 4"
            );
        }

        return lines;
    }

    private String getAlias(TypedIdentifier typedIdentifier) {
        return aliasesMap.getOrDefault(typedIdentifier, typedIdentifier.getName());
    }

    private String getNextTemporaryVariableName() {
        return "" + temporaryVariableCounter++;
    }

}

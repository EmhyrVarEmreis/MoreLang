package xyz.morecraft.dev.lang.morelang.object.statement.definition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.object.expression.Expression;
import xyz.morecraft.dev.lang.morelang.object.registry.FunctionContextRegistry;
import xyz.morecraft.dev.lang.morelang.object.registry.ProgramRegistry;
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
    private boolean isInternal;
    private boolean used;
    private String rawHeader;
    private String preparedHeader;

    public FunctionDefinition(TypedIdentifier typedIdentifier, List<TypedIdentifier> argumentList, List<Statement> statementList, Expression returnStatement) {
        super(typedIdentifier);
        this.argumentList = Objects.isNull(argumentList) ? new ArrayList<>() : argumentList;
        this.statementList = Objects.isNull(statementList) ? new ArrayList<>() : statementList;
        this.returnStatement = returnStatement;
        this.isInternal = false;
    }

    @Override
    public List<String> llvm(FunctionContextRegistry functionContextRegistry) {
        throw new IllegalStateException("Illegal method");
    }

    public List<String> llvm(ProgramRegistry programRegistry) {
        List<String> lines = new ArrayList<>();

        functionContextRegistry = new FunctionContextRegistry(this, programRegistry, this.argumentList);

        lines.add(
                "define " +
                        getTypedIdentifier().getType().getSimpleType().getLlvm() +
                        " @" +
                        getTypedIdentifier().getName() +
                        "(" +
                        argumentList.stream().map(
                                typedIdentifier -> typedIdentifier.getType().getSimpleType().getLlvm() + " %" + typedIdentifier.getName()
                        ).collect(Collectors.joining(", ")) +
                        ") {"
        );

        lines.addAll(
                generateFunctionBody().stream().map(
                        s -> (s.endsWith(":") ? s : ("\t" + s))
                ).collect(Collectors.toList())
        );

        lines.add("}");

        return lines;
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
            functionContextRegistry.register(typedIdentifier, true);
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

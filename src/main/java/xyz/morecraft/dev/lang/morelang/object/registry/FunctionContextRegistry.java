package xyz.morecraft.dev.lang.morelang.object.registry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import xyz.morecraft.dev.lang.morelang.object.Type;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.object.Variable;
import xyz.morecraft.dev.lang.morelang.object.expression.Expression;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.FunctionDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FunctionContextRegistry {

    @Getter
    @JsonIgnore
    private FunctionDefinition parent;
    private Map<String, RegisteredVariable> registeredVariableMap;
    private int temporaryVariableCounter = 1;
    private int temporaryLabelCounter = 1;
    @Getter
    private ProgramRegistry programRegistry;

    public FunctionContextRegistry(FunctionDefinition parent, ProgramRegistry programRegistry, List<TypedIdentifier> argumentList) {
        this.parent = parent;
        this.programRegistry = programRegistry;
        this.registeredVariableMap = new HashMap<>();
        for (TypedIdentifier typedIdentifier : argumentList) {
            typedIdentifier.getType().setPointer(true);
            register(typedIdentifier, false, false);
        }
        for (TypedIdentifier typedIdentifier : programRegistry.getGlobalVariableList()) {
            register(typedIdentifier, false, true);
        }
    }

    public String getNextTemporaryVariableName() {
        return "" + temporaryVariableCounter++;
    }

    public String getNextTemporaryLabelName() {
        return "lbl" + temporaryLabelCounter++;
    }

    public RegisteredVariable register(Expression expression, Type type, boolean makeAlias) {
        if (makeAlias) {
            expression.setAlias("%" + getNextTemporaryVariableName());
        }
        RegisteredVariable registeredVariable = new RegisteredVariable(
                expression.getAlias(),
                expression.getAlias(),
                false,
                type
        );
        registeredVariableMap.put(registeredVariable.getName(), registeredVariable);
        return registeredVariable;
    }

    public RegisteredVariable register(TypedIdentifier typedIdentifier, boolean makeAlias) {
        return register(typedIdentifier, makeAlias, false);
    }

    public RegisteredVariable register(TypedIdentifier typedIdentifier, boolean makeAlias, boolean isGlobal) {
        if (makeAlias) {
            typedIdentifier = makeAlias(typedIdentifier);
        }
        RegisteredVariable registeredVariable = new RegisteredVariable(
                typedIdentifier.getName(),
                typedIdentifier.getAlias(),
                isGlobal,
                typedIdentifier.getType()
        );
        registeredVariableMap.put(registeredVariable.getName(), registeredVariable);
        return registeredVariable;
    }

    public TypedIdentifier makeAlias(TypedIdentifier typedIdentifier) {
        if (Objects.isNull(typedIdentifier.getAlias())) {
            typedIdentifier.setAlias(getNextTemporaryVariableName());
        }
        return typedIdentifier;
    }

    public Type getType(TypedIdentifier typedIdentifier) {
        return getType(typedIdentifier.getName());
    }

    public Type getType(String name) {
        return registeredVariableMap.getOrDefault(name, new RegisteredVariable()).getType();
    }

    public String getAlias(TypedIdentifier typedIdentifier) {
        return getAlias(typedIdentifier.getName());
    }

    public String getAlias(String id) {
        return registeredVariableMap.getOrDefault(id, new RegisteredVariable(id, id, false, null)).getAlias();
    }

    public void updateVariable(Variable variable) {
        final RegisteredVariable registeredVariable = registeredVariableMap.get(variable.getName());
        if (Objects.nonNull(registeredVariable)) {
            variable.setAlias(registeredVariable.getAlias());
            variable.setType(registeredVariable.getType());
            variable.setGlobal(registeredVariable.isGlobal());
        }
    }
}

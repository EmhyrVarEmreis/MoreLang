package xyz.morecraft.dev.lang.morelang.object;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.FunctionDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FunctionContextRegistry {

    @Getter
    @JsonIgnore
    private FunctionDefinition parent;
    private Map<String, Type> variableTypesMap;
    private Map<String, TypedIdentifier> typedIdentifierNameMap;
    private int temporaryVariableCounter = 1;
    private int temporaryLabelCounter = 1;
    @Getter
    private ProgramRegistry programRegistry;

    public FunctionContextRegistry(FunctionDefinition parent, ProgramRegistry programRegistry, List<TypedIdentifier> argumentList) {
        this.parent = parent;
        this.programRegistry = programRegistry;
        this.variableTypesMap = new HashMap<>();
        this.typedIdentifierNameMap = new HashMap<>();
        for (TypedIdentifier typedIdentifier : argumentList) {
            typedIdentifier.getType().setPointer(true);
            registerType(typedIdentifier);
        }
        for (TypedIdentifier typedIdentifier : programRegistry.getGlobalVariableList()) {
            register(typedIdentifier, false);
            registerType(typedIdentifier);
        }
    }

    public String getNextTemporaryVariableName() {
        return "" + temporaryVariableCounter++;
    }

    public String getNextTemporaryLabelName() {
        return "lbl" + temporaryLabelCounter++;
    }

    public Type registerType(TypedIdentifier typedIdentifier) {
        return registerType(typedIdentifier.getName(), typedIdentifier.getType());
    }

    public Type registerType(String name, Type type) {
        System.out.println("- registering [" + (variableTypesMap.size() + 1) + "]" + name + " " + type);
        return variableTypesMap.put(name, type);
    }

    public TypedIdentifier register(TypedIdentifier typedIdentifier, boolean makeAlias) {
        variableTypesMap.put(typedIdentifier.getName(), typedIdentifier.getType());
        if (makeAlias) {
            typedIdentifier = makeAlias(typedIdentifier);
        }
        return typedIdentifier;
    }

    public TypedIdentifier makeAlias(TypedIdentifier typedIdentifier) {
        if (Objects.isNull(typedIdentifier.getAlias())) {
            typedIdentifier.setAlias(getNextTemporaryVariableName());
        }
        typedIdentifierNameMap.put(typedIdentifier.getName(), typedIdentifier);
        return typedIdentifier;
    }

    public Type getType(TypedIdentifier typedIdentifier) {
        return getType(typedIdentifier.getName());
    }

    public Type getType(String name) {
        Type type = variableTypesMap.get(name);
        if (Objects.isNull(type)) {
            type = determineType(typedIdentifierNameMap.get(name));
            variableTypesMap.put(name, type);
        }
        return type;
    }

    private Type determineType(TypedIdentifier typedIdentifier) {
        return null;
    }

    public String getAlias(TypedIdentifier typedIdentifier) {
        return getAlias(typedIdentifier.getName());
    }

    public String getAlias(String id) {
        return typedIdentifierNameMap.getOrDefault(id, new TypedIdentifier(id, id, null)).getAlias();
    }

}

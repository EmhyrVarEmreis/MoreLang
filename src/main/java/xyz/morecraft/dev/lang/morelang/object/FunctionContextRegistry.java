package xyz.morecraft.dev.lang.morelang.object;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FunctionContextRegistry {

    private Map<String, Type> variableTypesMap;
    private Map<String, TypedIdentifier> typedIdentifierNameMap;
    private int temporaryVariableCounter = 1;
    @Getter
    @Setter
    private ProgramRegistry programRegistry;

    public FunctionContextRegistry(List<TypedIdentifier> argumentList) {
        this.variableTypesMap = new HashMap<>();
        this.typedIdentifierNameMap = new HashMap<>();
        for (TypedIdentifier typedIdentifier : argumentList) {
            typedIdentifier.getType().setPointer(true);
            registerType(typedIdentifier);
        }
    }

    public String getNextTemporaryVariableName() {
        return "" + temporaryVariableCounter++;
    }

    public Type registerType(TypedIdentifier typedIdentifier) {
        return variableTypesMap.put(typedIdentifier.getName(), typedIdentifier.getType());
    }

    public Type registerType(String name, Type type) {
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

package xyz.morecraft.dev.lang.morelang.object.registry;

import xyz.morecraft.dev.lang.morelang.lib.GetcharFunctionDefinition;
import xyz.morecraft.dev.lang.morelang.lib.PutcharFunctionDefinition;
import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.FunctionDefinition;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.GlobalVariableDefinition;

import java.util.*;

public class ProgramRegistry {

    private List<TypedIdentifier> globalVariableList;
    private Map<String, FunctionDefinition> functionDefinitionNameMap;
    private Map<String, FunctionDefinition> internalFunctionDefinitionNameMap;

    public ProgramRegistry(Collection<? extends GlobalVariableDefinition> globalVariableCollection, Collection<? extends FunctionDefinition> functionDefinitionCollection) {
        this.globalVariableList = new ArrayList<>();
        if (Objects.nonNull(globalVariableCollection)) {
            for (GlobalVariableDefinition globalVariableDefinition : globalVariableCollection) {
                registerGlobalVariable(globalVariableDefinition);
            }
        }
        this.functionDefinitionNameMap = new HashMap<>();
        this.internalFunctionDefinitionNameMap = new HashMap<>();
        for (FunctionDefinition functionDefinition : functionDefinitionCollection) {
            registerFunction(functionDefinition);
        }
        registerFunction(new GetcharFunctionDefinition());
        registerFunction(new PutcharFunctionDefinition());
    }

    public List<TypedIdentifier> getGlobalVariableList() {
        return globalVariableList;
    }

    public void registerGlobalVariable(GlobalVariableDefinition globalVariableDefinition) {
        globalVariableList.add(globalVariableDefinition.getTypedIdentifier());
    }

    public void registerFunction(FunctionDefinition functioNDefinition) {
        if (functioNDefinition.isInternal()) {
            this.internalFunctionDefinitionNameMap.put(functioNDefinition.getTypedIdentifier().getName(), functioNDefinition);
        } else {
            this.functionDefinitionNameMap.put(functioNDefinition.getTypedIdentifier().getName(), functioNDefinition);
        }
    }

    public FunctionDefinition getFunctionDefinition(String name) {
        final FunctionDefinition functionDefinition = functionDefinitionNameMap.getOrDefault(name, internalFunctionDefinitionNameMap.get(name));
        if (Objects.nonNull(functionDefinition)) {
            functionDefinition.setUsed(true);
        }
        return functionDefinition;
    }

    public Collection<? extends FunctionDefinition> getInternalFunctionDefinitions() {
        return internalFunctionDefinitionNameMap.values();
    }

}

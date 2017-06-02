package xyz.morecraft.dev.lang.morelang.object.registry;

import xyz.morecraft.dev.lang.morelang.object.TypedIdentifier;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.FunctionDefinition;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.GlobalVariableDefinition;

import java.util.*;
import java.util.stream.Collectors;

public class ProgramRegistry {

    private List<TypedIdentifier> globalVariableList;
    private Map<String, FunctionDefinition> functionDefinitionNameMap;

    public ProgramRegistry(Collection<? extends GlobalVariableDefinition> globalVariableCollection, Collection<? extends FunctionDefinition> functionDefinitionCollection) {
        this.globalVariableList = new ArrayList<>();
        if (Objects.nonNull(globalVariableCollection)) {
            for (GlobalVariableDefinition globalVariableDefinition : globalVariableCollection) {
                registerGlobalVariable(globalVariableDefinition);
            }
        }
        this.functionDefinitionNameMap = functionDefinitionCollection.stream().collect(
                Collectors.toMap(
                        o -> o.getTypedIdentifier().getName(),
                        o -> o
                )
        );
    }

    public List<TypedIdentifier> getGlobalVariableList() {
        return globalVariableList;
    }

    public void registerGlobalVariable(GlobalVariableDefinition globalVariableDefinition) {
        globalVariableList.add(globalVariableDefinition.getTypedIdentifier());
    }

    public FunctionDefinition getFunctionDefinition(String name) {
        return functionDefinitionNameMap.get(name);
    }

}

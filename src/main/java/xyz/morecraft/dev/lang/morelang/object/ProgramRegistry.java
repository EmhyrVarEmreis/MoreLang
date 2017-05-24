package xyz.morecraft.dev.lang.morelang.object;

import xyz.morecraft.dev.lang.morelang.object.statement.definition.FunctionDefinition;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class ProgramRegistry {

    private Map<String, FunctionDefinition> functionDefinitionNameMap;

    public ProgramRegistry(Collection<? extends FunctionDefinition> functionDefinitionCollection) {
        functionDefinitionNameMap = functionDefinitionCollection.stream().collect(
                Collectors.toMap(
                        o -> o.getTypedIdentifier().getName(),
                        o -> o
                )
        );
    }

    public FunctionDefinition getFunctionDefinition(String name) {
        return functionDefinitionNameMap.get(name);
    }

}

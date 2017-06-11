package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.morecraft.dev.lang.morelang.object.registry.ProgramRegistry;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.FunctionDefinition;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.GlobalVariableDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    private ProgramHeader programHeader = new ProgramHeader();
    private List<GlobalVariableDefinition> globalVariableDefinitionList = new ArrayList<>();
    private List<FunctionDefinition> functionDefinitionList = new ArrayList<>();
    private ProgramRegistry programRegistry;

    public Program(ProgramHeader programHeader, List<GlobalVariableDefinition> globalVariableDefinitionList, List<FunctionDefinition> functionDefinitionList) {
        this.programHeader = programHeader;
        this.globalVariableDefinitionList = globalVariableDefinitionList;
        this.functionDefinitionList = functionDefinitionList;
        this.programRegistry = new ProgramRegistry(globalVariableDefinitionList, functionDefinitionList);
    }

    public String llvm() {
        return globalVariableDefinitionList.stream()
                .map(globalVariableDefinition -> globalVariableDefinition.llvm(null).stream().collect(Collectors.joining("\n")))
                .collect(Collectors.joining("\n"))
                + "\n\n\n" +
                functionDefinitionList.stream()
                        .map(functionDefinition -> functionDefinition.llvm(programRegistry))
                        .collect(Collectors.joining("\n\n"));
    }

}

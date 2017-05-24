package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.morecraft.dev.lang.morelang.object.statement.definition.FunctionDefinition;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    private ProgramHeader programHeader = new ProgramHeader();
    private List<FunctionDefinition> functionDefinitionList = new ArrayList<>();
    private ProgramRegistry programRegistry;

    public Program(ProgramHeader programHeader, List<FunctionDefinition> functionDefinitionList) {
        this.programHeader = programHeader;
        this.functionDefinitionList = functionDefinitionList;
        this.programRegistry = new ProgramRegistry(functionDefinitionList);
    }

    public String llvm() {
        StringBuilder llvm = new StringBuilder();

        for (FunctionDefinition functionDefinition : functionDefinitionList) {
            llvm.append(functionDefinition.llvm(programRegistry));
        }

        return llvm.toString();
    }

}

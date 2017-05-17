package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program implements LLVMEmmitable {

    private ProgramHeader programHeader = new ProgramHeader();
    private List<FunctionDefinition> functionDefinitionList = new ArrayList<>();

    public String llvm() {
        StringBuilder llvm = new StringBuilder();

        for (FunctionDefinition functionDefinition : functionDefinitionList) {
            llvm.append(functionDefinition.llvm());
        }

        return llvm.toString();
    }

}

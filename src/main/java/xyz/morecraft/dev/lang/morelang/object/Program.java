package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Program {

    private ProgramHeader programHeader = new ProgramHeader();
    private List<Statement> statementList = new ArrayList<>();
    private List<FunctionDefinition> functionDefinitionList = new ArrayList<>();

    public void addStatement(Statement statement) {
        statementList.add(statement);
    }

}

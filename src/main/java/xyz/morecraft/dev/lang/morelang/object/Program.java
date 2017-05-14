package xyz.morecraft.dev.lang.morelang.object;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Program {

    private List<Statement> statementList = new ArrayList<>();

    public void addStatement(Statement statement) {
        statementList.add(statement);
    }

}

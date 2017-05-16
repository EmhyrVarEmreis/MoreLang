package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramHeader {

    private List<String> importList = new ArrayList<>();

    public void addImport(String importName) {
        importList.add(importName);
    }

}

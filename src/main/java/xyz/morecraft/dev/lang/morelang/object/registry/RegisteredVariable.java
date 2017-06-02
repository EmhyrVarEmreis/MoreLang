package xyz.morecraft.dev.lang.morelang.object.registry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.morecraft.dev.lang.morelang.object.Type;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredVariable {

    private String name;
    private String alias;
    private boolean global;
    private Type type;

}

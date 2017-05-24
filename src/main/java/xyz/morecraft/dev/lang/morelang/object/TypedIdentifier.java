package xyz.morecraft.dev.lang.morelang.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import xyz.morecraft.dev.lang.morelang.MoreLangGrammarParser;

import java.util.Objects;

@Data
@AllArgsConstructor
public class TypedIdentifier {

    public TypedIdentifier(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    private String name;
    private String alias;
    private Type type;

    public String name() {
        return Objects.isNull(alias) ? name : alias;
    }

    public static TypedIdentifier of(MoreLangGrammarParser.TypedIdentifierContext context) {
        return new TypedIdentifier(
                context.identifier().getText(),
                Type.of(
                        context.type().getText().toUpperCase()
                )
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TypedIdentifier that = (TypedIdentifier) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}

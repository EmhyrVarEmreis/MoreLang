grammar MoreLangGrammar;

@header {
    import xyz.morecraft.dev.lang.morelang.object.Program;
    import xyz.morecraft.dev.lang.morelang.object.Statement;
}

program:
    programHeader? module+;

programHeader :
    (ctrlImport)+;

ctrlImport:
    CTRL_IMPORT ID (CTRL_DOT ID)* SEMICOLON;

module :
    CTRL_MODULE ID moduleBodyBlock;

moduleBodyBlock :
    BRACKET_BRACE_OP moduleBody BRACKET_BRACE_CLOSE;

moduleBody :
    (variableDefinitionWithModifier | functionDefinition)+;

bodyBlock :
    BRACKET_BRACE_OP body BRACKET_BRACE_CLOSE;

body :
    statement+;

statement :
    ((expression | returnStatement) SEMICOLON) | variableDefinition | ifStatement | whileLoop;

ifStatement :
    CTRL_IF BRACKET_PAREN_OP expression BRACKET_PAREN_CLOSE bodyBlock (CTRL_ELSE (bodyBlock | ifStatement))?;

whileLoop :
    CTRL_WHILE BRACKET_PAREN_OP expression BRACKET_PAREN_CLOSE bodyBlock;

variableDefinitionWithModifier:
    modifier variableDefinition;

variableDefinition :
    typedIdentifier TAB_SUFFIX? (EQUAL expression)? SEMICOLON;

value :
    VAL_STRING | VAL_INT | VAL_FLOAT;

returnStatement :
    CTRL_RETURN expression?;

expression :
   assigment | atomicExpression | arithmeticExpression;

arithmeticExpression :
    (simpleExpression (operator simpleExpression)+);

simpleExpression :
    atomicExpression | (BRACKET_PAREN_OP expression BRACKET_PAREN_CLOSE);

atomicExpression:
    value | identifier | functionInvocation;

assigment :
    identifier (BRACKET_SQUARE_OP VAL_INT BRACKET_SQUARE_CLOSE)? EQUAL expression;

functionDefinition :
    functionHeader bodyBlock;

functionInvocation:
    identifier functionArgs;

functionHeader :
    modifier typedIdentifier BRACKET_PAREN_OP ((typedIdentifier) (COMMA (typedIdentifier))*)? BRACKET_PAREN_CLOSE;

functionArgs :
    BRACKET_PAREN_OP ((expression) (COMMA expression)*)? BRACKET_PAREN_CLOSE;

typedIdentifier:
    type identifier;

identifier:
    ID;

modifier:
    MOD_PRIVATE | MOD_PUBLIC;

type:
    ((INT | FLOAT | STRING) TAB_SUFFIX?) | VOID;

operator :
    OP_PLUS | OP_MINUS | OP_DIVIDE | OP_MULTIPLY | OP_COMPARE | OP_COMPARE_NEG | OP_AND | OP_OR;


// types
INT                     :   'int';
FLOAT                   :   'float';
STRING                  :   'string';
VOID                    :   'void';
TAB_SUFFIX              :   '[]';

// control sequences
CTRL_IMPORT             :   'import';
CTRL_MODULE             :   'module';
CTRL_DEF                :   'def';
CTRL_RETURN             :   'return';
CTRL_IF                 :   'if';
CTRL_ELSE               :   'else';
CTRL_WHILE              :   'while';
CTRL_DOT                :   '.';

// modifiers
MOD_PRIVATE             :   'private';
MOD_PUBLIC              :   'public';

// operations
OP_PLUS                 :   '+';
OP_MINUS                :   '-';
OP_DIVIDE               :   '/';
OP_MULTIPLY             :   '*';
OP_COMPARE              :   '==';
OP_COMPARE_NEG          :   '!=';
OP_AND                  :   '&&';
OP_OR                   :   '||';

// values
VAL_STRING              :   ['].*?['];
VAL_INT                 :   [0-9];
VAL_FLOAT               :   [0-9]* '.' [0-9]+;

//special
EQUAL                   :   '=';
COMMA                   :   ',';
SEMICOLON               :   ';';
BRACKET_PAREN_OP        :   '(';
BRACKET_PAREN_CLOSE     :   ')';
BRACKET_BRACE_OP        :   '{';
BRACKET_BRACE_CLOSE     :   '}';
BRACKET_SQUARE_OP       :   '[';
BRACKET_SQUARE_CLOSE    :   ']';

ID                      :   [a-zA-Z][a-zA-Z0-9]*;

WS                      :   (' '|'\r'|'\n') -> skip;
COMMENT                 :   ( '//' ~[\r\n]* '\r'? '\n' | '/*' .*? '*/' ) -> skip;
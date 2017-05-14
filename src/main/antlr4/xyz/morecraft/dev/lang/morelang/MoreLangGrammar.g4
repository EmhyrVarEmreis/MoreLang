grammar MoreLangGrammar;

@header {
    import xyz.morecraft.dev.lang.morelang.object.Program;
    import xyz.morecraft.dev.lang.morelang.object.Statement;
}

program:
    programHeader? body;

body :
    (SEMICOLON | statement)+;

bodyBlock :
    BRACKET_BRACE_OP body BRACKET_BRACE_CLOSE;

programHeader :
    (ctrlImport)+ ctrlModuleName;

ctrlModuleName :
    CTRL_MODULE ID SEMICOLON;

ctrlImport:
    CTRL_IMPORT ID (CTRL_DOT ID)* SEMICOLON;

statement :
    ((expression | returnStatement) SEMICOLON) | definition | ifStatement | whileLoop;

ifStatement :
    CTRL_IF BRACKET_PAREN_OP expression BRACKET_PAREN_CLOSE bodyBlock (CTRL_ELSE (bodyBlock | ifStatement))?;

whileLoop :
    CTRL_WHILE BRACKET_PAREN_OP expression BRACKET_PAREN_CLOSE bodyBlock;

definition :
    CTRL_DEF (variableDefinition | functionDefinition);

variableDefinition :
    type identifier TAB_SUFFIX? expression SEMICOLON;

value :
    VAL_STRING | VAL_INT | VAL_FLOAT;

returnStatement :
    CTRL_RETURN expression;

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
    type identifier functionArgs;

functionArgs :
    BRACKET_PAREN_OP ((expression) (COMMA expression)*)? BRACKET_PAREN_CLOSE;

identifier:
    ID;

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
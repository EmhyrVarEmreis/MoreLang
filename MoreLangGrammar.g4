grammar MoreLangGrammar;

program :
    programHeader? body;

body :
    (SEMICOLON | statement)+;

bodyBlock :
    BRACKET_BRACE_OP body BRACKET_BRACE_CLOSE;

programHeader :
    (ctrlImport)+;

ctrlImport:
    CTRL_IMPORT ID SEMICOLON;

statement :
    ((expression | returnStatement) SEMICOLON) | definition | ifStatement | whileLoop;

ifStatement :
    CTRL_IF BRACKET_PAREN_OP expression BRACKET_PAREN_CLOSE bodyBlock (CTRL_ELSE (bodyBlock | ifStatement))?;

whileLoop :
    CTRL_WHILE BRACKET_PAREN_OP expression BRACKET_PAREN_CLOSE bodyBlock;

definition :
    CTRL_DEF (variableDefinition | functionDefinition);

variableDefinition :
    type ID TAB_SUFFIX? expression SEMICOLON;

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
    value | ID | functionInvocation;

functionInvocation:
    functionHeader;

assigment :
    ID (BRACKET_SQUARE_OP VAL_INT BRACKET_SQUARE_CLOSE)? EQUAL expression;

functionDefinition :
    functionHeader bodyBlock;

functionHeader :
    ID BRACKET_PAREN_OP ((expression) (COMMA expression)*)? BRACKET_PAREN_CLOSE;

type:
    (INT | FLOAT | STRING) TAB_SUFFIX?;

operator :
    OP_PLUS | OP_MINUS | OP_DIVIDE | OP_MULTIPLY | OP_COMPARE | OP_COMPARE_NEG | OP_AND | OP_OR;


// types
INT                     :   'int';
FLOAT                   :   'float';
STRING                  :   'string';
TAB_SUFFIX              :   '[]';

// control sequences
CTRL_IMPORT             :   'import';
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

WS                      : (' '|'\r'|'\n') -> skip;
COMMENT                 : ( '//' ~[\r\n]* '\r'? '\n' | '/*' .*? '*/' ) -> skip;
grammar MoreLangGrammar;

@header {
    import xyz.morecraft.dev.lang.morelang.object.Program;
    import xyz.morecraft.dev.lang.morelang.object.Statement;
}

program:
    programHeader? programBody;

programBody :
    (SEMICOLON | statement | functionDefinition)+;

body :
    (SEMICOLON | statement)+;

bodyBlock :
    BRACKET_BRACE_OP body BRACKET_BRACE_CLOSE;

programHeader :
    (ctrlImport)+;

ctrlImport:
    CTRL_IMPORT ctrlImportName SEMICOLON;

ctrlImportName:
    ID (CTRL_DOT ID)*;

statement :
    ((assignmentStatement | returnStatement | functionInvocationStatement) SEMICOLON) | variableDefinitionStatement | ifStatement | whileLoopStatement;

ifStatement :
    CTRL_IF BRACKET_PAREN_OP expression BRACKET_PAREN_CLOSE bodyBlock (CTRL_ELSE (bodyBlock | ifStatement))?;

whileLoopStatement :
    CTRL_WHILE BRACKET_PAREN_OP expression BRACKET_PAREN_CLOSE bodyBlock;

variableDefinitionStatement :
    typedIdentifier TAB_SUFFIX? (EQUAL expression)? SEMICOLON;

value :
    VAL_STRING | VAL_INT | VAL_FLOAT;

returnStatement :
    CTRL_RETURN expression?;

expression :
   assignmentStatement | atomicExpression | arithmeticExpression;

arithmeticExpression :
    (simpleExpression (operator simpleExpression)+);

simpleExpression :
    atomicExpression | (BRACKET_PAREN_OP expression BRACKET_PAREN_CLOSE);

atomicExpression:
    value | variable | functionInvocationStatement;

assignmentStatement :
    variable (BRACKET_SQUARE_OP VAL_INT BRACKET_SQUARE_CLOSE)? EQUAL expression;

functionDefinition :
    functionDefinitionHeader bodyBlock;

functionInvocationStatement:
    identifier BRACKET_PAREN_OP functionInvocationArguments BRACKET_PAREN_CLOSE;

functionDefinitionHeader :
    typedIdentifier BRACKET_PAREN_OP functionDefinitionHeaderArguments BRACKET_PAREN_CLOSE;

functionDefinitionHeaderArguments:
    ((typedIdentifier) (COMMA (typedIdentifier))*)?;

functionInvocationArguments :
    ((value | variable) (COMMA (value | variable) )*)?;

typedIdentifier:
    type identifier;

variable:
    identifier (BRACKET_SQUARE_OP expression BRACKET_SQUARE_CLOSE)?;

identifier:
    ID;

type:
    ((INT | FLOAT | STRING) TAB_SUFFIX?) | VOID;

operator :
    OP_PLUS | OP_MINUS | OP_DIVIDE | OP_MULTIPLY | OP_COMPARE | OP_COMPARE_NEG | OP_AND | OP_OR;


// control sequences
CTRL_IMPORT             :   'import';
CTRL_RETURN             :   'return';
CTRL_IF                 :   'if';
CTRL_ELSE               :   'else';
CTRL_WHILE              :   'while';
CTRL_DOT                :   '.';

// types
INT                     :   'int';
FLOAT                   :   'float';
STRING                  :   'string';
VOID                    :   'void';
TAB_SUFFIX              :   '[]';

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
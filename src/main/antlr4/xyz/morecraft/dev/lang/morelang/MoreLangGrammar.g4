grammar MoreLangGrammar;

program:
    programHeader? programBody;

programBody :
    (SEMICOLON | functionDefinition)+;

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
    ((assignmentStatement | functionInvocationStatement) SEMICOLON) | variableDefinitionStatement | ifStatement | whileLoopStatement;

ifStatement :
    CTRL_IF BRACKET_PAREN_OP expression BRACKET_PAREN_CLOSE bodyBlock (CTRL_ELSE (bodyBlock | ifStatement))?;

whileLoopStatement :
    CTRL_WHILE BRACKET_PAREN_OP expression BRACKET_PAREN_CLOSE bodyBlock;

variableDefinitionStatement :
    typedIdentifier TAB_SUFFIX? (EQUAL expression)? SEMICOLON;

assignmentStatement :
    variable (BRACKET_SQUARE_OP VAL_INT BRACKET_SQUARE_CLOSE)? EQUAL expression;

value :
    VAL_STRING | VAL_INT | VAL_FLOAT;

returnStatement :
    CTRL_RETURN expression?;

expression :
    expression operator expression #biExpression
    | '(' expression ')' #parenExpression
    | atomicExpression #smallExpression;

atomicExpression:
    value | variable | functionInvocationStatement;

functionDefinition :
    functionDefinitionHeader BRACKET_BRACE_OP body returnStatement SEMICOLON BRACKET_BRACE_CLOSE;

functionInvocationStatement:
    identifier BRACKET_PAREN_OP functionInvocationArguments BRACKET_PAREN_CLOSE;

functionDefinitionHeader :
    typedIdentifier BRACKET_PAREN_OP functionDefinitionHeaderArguments BRACKET_PAREN_CLOSE;

functionDefinitionHeaderArguments:
    ((typedIdentifier) (COMMA (typedIdentifier))*)?;

functionInvocationArguments :
    (expression (COMMA expression )*)?;

typedIdentifier:
    type identifier;

variable:
    identifier (BRACKET_SQUARE_OP expression BRACKET_SQUARE_CLOSE)?;

identifier:
    ID;

type:
    ((INT | FLOAT | STRING) TAB_SUFFIX?) | VOID;

operator:
    arithmeticOperator | logicalOperator | compareOperator;

arithmeticOperator :
    OP_PLUS | OP_MINUS | OP_DIVIDE | OP_MULTIPLY;

logicalOperator :
    OP_LOGIC_AND | OP_LOGIC_OR;

compareOperator :
    OP_COMPARE_EQ | OP_COMPARE_NEQ | OP_COMPARE_GT | OP_COMPARE_LT;


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
OP_COMPARE_EQ           :   '==';
OP_COMPARE_NEQ          :   '!=';
OP_COMPARE_GT           :   '>';
OP_COMPARE_LT           :   '<';
OP_LOGIC_AND            :   '&&';
OP_LOGIC_OR             :   '||';

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
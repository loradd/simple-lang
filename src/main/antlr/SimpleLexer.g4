lexer grammar SimpleLexer;

// Comments (ignored)
COMMENT: '#' ~[\r\n]* -> skip;

// Keywords
ELSE: 'else';
FUNCTION: 'function';
IF: 'if';
PRINT:'print';
RETURN: 'return';

// Operators
GREATER_EQUAL: '>=';
LESS_EQUAL: '<=';
EQUAL: '==';
NOT_EQUAL: '!=';
GREATER: '>';
LESS: '<';
NOT: '!';
PLUS: '+';
MINUS: '-';
ASTERISK: '*';
DIVISION: '/';
BIND: '=';

// Parentheses
L_PAREN: '(';
R_PAREN: ')';

// Braces
L_BRACE: '{';
R_BRACE: '}';

// Punctuation
COMMA: ',';
COLON: ';';

// Identifiers
NAME: [a-z][a-zA-Z]*;

// Literals
NUMBER: '0'| [1-9][0-9]*;

// Whitespaces (ignored)
WHITESPACE: [\t\r\n ] -> skip;

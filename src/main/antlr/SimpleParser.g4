parser grammar SimpleParser;

options { tokenVocab=SimpleLexer; }

// (entry)
workspace:
  functions+=function*   // functions
  statements+=statement* // statements
;

function:
  FUNCTION name=NAME
  L_PAREN (parameters+=NAME (',' parameters+=NAME)*)? R_PAREN // parameters
  L_BRACE statements += statement* R_BRACE                    // statements
;

statement:
  RETURN expression COLON                                                             # returnStatement
  | PRINT expression COLON                                                            # printStatement
  | IF L_PAREN condition=expression R_PAREN
    (L_BRACE positiveBranch+=statement* R_BRACE | positiveBranch+=statement)
    (ELSE (L_BRACE negativeBranch+=statement* R_BRACE | negativeBranch+=statement))?  # conditionalStatement
  | name=NAME BIND value=expression COLON                                             # bindStatement
  | expression COLON                                                                  # expressionStatement
  ;

// Expressions
expression:
  L_PAREN expression R_PAREN                                                      # parenExpression
  | op=(NOT|PLUS|MINUS) expression                                                # unaryExpression
  | left=expression op=(ASTERISK|DIVISION) right=expression                       # multiplicativeExpression
  | left=expression op=(PLUS|MINUS) right=expression                              # additiveExpression
  | left=expression op=(LESS|GREATER|LESS_EQUAL|GREATER_EQUAL) right=expression   # relationalExpression
  | left=expression op=(EQUAL|NOT_EQUAL) right=expression                         # equalityExpression
  | NAME L_PAREN (arguments+=expression (COMMA arguments+=expression)*)? R_PAREN  # invocationExpression
  | NAME                                                                          # nameExpression
  | NUMBER                                                                        # numberExpression
  ;

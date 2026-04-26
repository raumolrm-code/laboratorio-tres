grammar Laboratorio;

/* =======================================
   REGLAS SINTÁCTICAS (PARSER)
   ======================================= */

// 1. Regla inicial: El programa lee instrucciones hasta el final del archivo
prog : instruccion+ EOF ;

// 2. Una instrucción puede ser una asignación o una evaluación lógica
instruccion
    : asignacion
    | expresion
    ;

// 3. Asignación estricta (Ej: x = 1, y = true, z = "Hola")
asignacion
    : ID '=' valor
    ;

// 4. Tipos de valores permitidos (Punto 1 del PDF)
valor
    : INT       # ValInt
    | BOOL      # ValBool
    | STRING    # ValString
    ;

// 5. Expresiones lógicas (Manejo automático de precedencia)
expresion
    : 'not' '(' expresion ')'     # ExprNot
    | expresion 'and' expresion   # ExprAnd
    | '(' expresion ')'           # ExprParens
    | ID                          # ExprId
    ;

/* =======================================
   REGLAS LÉXICAS (LEXER / TOKENS)
   ======================================= */

// Palabras reservadas del laboratorio
AND     : 'and' ;
NOT     : 'not' ;

// Tipos de datos
BOOL    : 'true' | 'false' ;
INT     : [0-9]+ ;
STRING  : '"' ~["]* '"' ;

// Identificadores (Nombres de variables)
ID      : [a-zA-Z_][a-zA-Z0-9_]* ;

// Ignorar espacios en blanco, tabulaciones y saltos de línea
WS      : [ \t\r\n]+ -> skip ;
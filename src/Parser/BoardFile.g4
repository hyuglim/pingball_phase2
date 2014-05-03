grammar BoardFile;

// This puts a Java package statement at the top of the output Java files.
@header {
package phase1;
}

// This adds code to the generated lexer and parser.
@members {
    /**
     * Call this method to have the lexer or parser throw a RuntimeException if
     * it encounters an error.
     */
    public void reportErrorsAsExceptions() {
        addErrorListener(new ExceptionThrowingErrorListener());
    }
    
    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new RuntimeException(msg);
        }
    }
}

/*
 * These are the lexical rules. They define the tokens used by the lexer.
 * *** ANTLR requires tokens to be CAPITALIZED, like START_ITALIC, END_ITALIC, and TEXT.
 */

BOARD : 'board';
GRAVITY : 'gravity';
FRICTION1 : 'friction1';
FRICTION2 : 'friction2';
BALL : 'ball';
SQUARE_BUMPER : 'squareBumper' ;
CIRCLE_BUMPER : 'circleBumper' ;
TRIANGULAR_BUMPER : 'triangleBumper' ;
LEFT_FLIPPER : 'leftFlipper' ;
RIGHT_FLIPPER : 'rightFlipper' ;
ABSORBER : 'absorber' ;
FIRE : 'fire';
TRIGGER : 'trigger';
ACTION : 'action';
EQUAL : '=' ;
FLOAT : '-'?([0-9]+'.'[0-9]*|'.'?[0-9]+) ;
NAME : [A-Za-z_][A-Za-z_0-9]* ;
WHITESPACE : [ \t\r\n]+ -> skip ;
COMMENT : '#' ~('\n'|'\r')* -> skip ;

/*
 * These are the parser rules. They define the structures used by the parser.
 * *** ANTLR requires grammar nonterminals to be lowercase, like html, normal, and italic.
 */
file : declaration EOF;
declaration : board line*;
line : ball | square | circle | triangle | leftFlipper | rightFlipper | absorber | fire;
board : BOARD (attribute EQUAL value) (GRAVITY EQUAL value)? (FRICTION1 EQUAL value)? (FRICTION2 EQUAL value)?;
ball : BALL (attribute EQUAL value)+;
square : SQUARE_BUMPER (attribute EQUAL value)+;
circle : CIRCLE_BUMPER (attribute EQUAL value)+;
triangle : TRIANGULAR_BUMPER (attribute EQUAL value)+;
leftFlipper : LEFT_FLIPPER (attribute EQUAL value)+;
rightFlipper : RIGHT_FLIPPER (attribute EQUAL value)+;
absorber : ABSORBER (attribute EQUAL value)+;
fire : FIRE TRIGGER EQUAL NAME ACTION EQUAL NAME;  
attribute : NAME;
value : NAME | FLOAT;
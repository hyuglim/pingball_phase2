// Generated from BoardFile.g4 by ANTLR 4.0

package Parser;

import org.antlr.v4.runtime.Lexer; 
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BoardFileLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BOARD=1, GRAVITY=2, FRICTION1=3, FRICTION2=4, BALL=5, SQUARE_BUMPER=6, 
		CIRCLE_BUMPER=7, TRIANGULAR_BUMPER=8, LEFT_FLIPPER=9, RIGHT_FLIPPER=10, 
		ABSORBER=11, FIRE=12, TRIGGER=13, ACTION=14, EQUAL=15, FLOAT=16, NAME=17, 
		WHITESPACE=18, COMMENT=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'board'", "'gravity'", "'friction1'", "'friction2'", "'ball'", "'squareBumper'", 
		"'circleBumper'", "'triangleBumper'", "'leftFlipper'", "'rightFlipper'", 
		"'absorber'", "'fire'", "'trigger'", "'action'", "'='", "FLOAT", "NAME", 
		"WHITESPACE", "COMMENT"
	};
	public static final String[] ruleNames = {
		"BOARD", "GRAVITY", "FRICTION1", "FRICTION2", "BALL", "SQUARE_BUMPER", 
		"CIRCLE_BUMPER", "TRIANGULAR_BUMPER", "LEFT_FLIPPER", "RIGHT_FLIPPER", 
		"ABSORBER", "FIRE", "TRIGGER", "ACTION", "EQUAL", "FLOAT", "NAME", "WHITESPACE", 
		"COMMENT"
	};


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


	public BoardFileLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BoardFile.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 17: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;

		case 18: COMMENT_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WHITESPACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\2\4\25\u00e1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\5\21"+
		"\u00b3\n\21\3\21\6\21\u00b6\n\21\r\21\16\21\u00b7\3\21\3\21\7\21\u00bc"+
		"\n\21\f\21\16\21\u00bf\13\21\3\21\5\21\u00c2\n\21\3\21\6\21\u00c5\n\21"+
		"\r\21\16\21\u00c6\5\21\u00c9\n\21\3\22\3\22\7\22\u00cd\n\22\f\22\16\22"+
		"\u00d0\13\22\3\23\6\23\u00d3\n\23\r\23\16\23\u00d4\3\23\3\23\3\24\3\24"+
		"\7\24\u00db\n\24\f\24\16\24\u00de\13\24\3\24\3\24\2\25\3\3\1\5\4\1\7\5"+
		"\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17"+
		"\1\35\20\1\37\21\1!\22\1#\23\1%\24\2\'\25\3\3\2\t\3\62;\3\62;\3\62;\5"+
		"C\\aac|\6\62;C\\aac|\5\13\f\17\17\"\"\4\f\f\17\17\u00e9\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\3)\3\2\2\2\5/\3\2\2\2\7\67\3\2\2\2\tA\3\2\2\2\13K\3\2\2\2"+
		"\rP\3\2\2\2\17]\3\2\2\2\21j\3\2\2\2\23y\3\2\2\2\25\u0085\3\2\2\2\27\u0092"+
		"\3\2\2\2\31\u009b\3\2\2\2\33\u00a0\3\2\2\2\35\u00a8\3\2\2\2\37\u00af\3"+
		"\2\2\2!\u00b2\3\2\2\2#\u00ca\3\2\2\2%\u00d2\3\2\2\2\'\u00d8\3\2\2\2)*"+
		"\7d\2\2*+\7q\2\2+,\7c\2\2,-\7t\2\2-.\7f\2\2.\4\3\2\2\2/\60\7i\2\2\60\61"+
		"\7t\2\2\61\62\7c\2\2\62\63\7x\2\2\63\64\7k\2\2\64\65\7v\2\2\65\66\7{\2"+
		"\2\66\6\3\2\2\2\678\7h\2\289\7t\2\29:\7k\2\2:;\7e\2\2;<\7v\2\2<=\7k\2"+
		"\2=>\7q\2\2>?\7p\2\2?@\7\63\2\2@\b\3\2\2\2AB\7h\2\2BC\7t\2\2CD\7k\2\2"+
		"DE\7e\2\2EF\7v\2\2FG\7k\2\2GH\7q\2\2HI\7p\2\2IJ\7\64\2\2J\n\3\2\2\2KL"+
		"\7d\2\2LM\7c\2\2MN\7n\2\2NO\7n\2\2O\f\3\2\2\2PQ\7u\2\2QR\7s\2\2RS\7w\2"+
		"\2ST\7c\2\2TU\7t\2\2UV\7g\2\2VW\7D\2\2WX\7w\2\2XY\7o\2\2YZ\7r\2\2Z[\7"+
		"g\2\2[\\\7t\2\2\\\16\3\2\2\2]^\7e\2\2^_\7k\2\2_`\7t\2\2`a\7e\2\2ab\7n"+
		"\2\2bc\7g\2\2cd\7D\2\2de\7w\2\2ef\7o\2\2fg\7r\2\2gh\7g\2\2hi\7t\2\2i\20"+
		"\3\2\2\2jk\7v\2\2kl\7t\2\2lm\7k\2\2mn\7c\2\2no\7p\2\2op\7i\2\2pq\7n\2"+
		"\2qr\7g\2\2rs\7D\2\2st\7w\2\2tu\7o\2\2uv\7r\2\2vw\7g\2\2wx\7t\2\2x\22"+
		"\3\2\2\2yz\7n\2\2z{\7g\2\2{|\7h\2\2|}\7v\2\2}~\7H\2\2~\177\7n\2\2\177"+
		"\u0080\7k\2\2\u0080\u0081\7r\2\2\u0081\u0082\7r\2\2\u0082\u0083\7g\2\2"+
		"\u0083\u0084\7t\2\2\u0084\24\3\2\2\2\u0085\u0086\7t\2\2\u0086\u0087\7"+
		"k\2\2\u0087\u0088\7i\2\2\u0088\u0089\7j\2\2\u0089\u008a\7v\2\2\u008a\u008b"+
		"\7H\2\2\u008b\u008c\7n\2\2\u008c\u008d\7k\2\2\u008d\u008e\7r\2\2\u008e"+
		"\u008f\7r\2\2\u008f\u0090\7g\2\2\u0090\u0091\7t\2\2\u0091\26\3\2\2\2\u0092"+
		"\u0093\7c\2\2\u0093\u0094\7d\2\2\u0094\u0095\7u\2\2\u0095\u0096\7q\2\2"+
		"\u0096\u0097\7t\2\2\u0097\u0098\7d\2\2\u0098\u0099\7g\2\2\u0099\u009a"+
		"\7t\2\2\u009a\30\3\2\2\2\u009b\u009c\7h\2\2\u009c\u009d\7k\2\2\u009d\u009e"+
		"\7t\2\2\u009e\u009f\7g\2\2\u009f\32\3\2\2\2\u00a0\u00a1\7v\2\2\u00a1\u00a2"+
		"\7t\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4\7i\2\2\u00a4\u00a5\7i\2\2\u00a5"+
		"\u00a6\7g\2\2\u00a6\u00a7\7t\2\2\u00a7\34\3\2\2\2\u00a8\u00a9\7c\2\2\u00a9"+
		"\u00aa\7e\2\2\u00aa\u00ab\7v\2\2\u00ab\u00ac\7k\2\2\u00ac\u00ad\7q\2\2"+
		"\u00ad\u00ae\7p\2\2\u00ae\36\3\2\2\2\u00af\u00b0\7?\2\2\u00b0 \3\2\2\2"+
		"\u00b1\u00b3\7/\2\2\u00b2\u00b1\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00c8"+
		"\3\2\2\2\u00b4\u00b6\t\2\2\2\u00b5\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		"\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bd\7\60"+
		"\2\2\u00ba\u00bc\t\3\2\2\u00bb\u00ba\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd"+
		"\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00c9\3\2\2\2\u00bf\u00bd\3\2"+
		"\2\2\u00c0\u00c2\7\60\2\2\u00c1\u00c0\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2"+
		"\u00c4\3\2\2\2\u00c3\u00c5\t\4\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c6\3\2"+
		"\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8"+
		"\u00b5\3\2\2\2\u00c8\u00c1\3\2\2\2\u00c9\"\3\2\2\2\u00ca\u00ce\t\5\2\2"+
		"\u00cb\u00cd\t\6\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc"+
		"\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf$\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1"+
		"\u00d3\t\7\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\b\23\2\2\u00d7"+
		"&\3\2\2\2\u00d8\u00dc\7%\2\2\u00d9\u00db\n\b\2\2\u00da\u00d9\3\2\2\2\u00db"+
		"\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00df\3\2"+
		"\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\b\24\3\2\u00e0(\3\2\2\2\f\2\u00b2"+
		"\u00b7\u00bd\u00c1\u00c6\u00c8\u00ce\u00d4\u00dc";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}
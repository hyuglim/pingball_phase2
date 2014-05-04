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
		ABSORBER=11, FIRE=12, KEYUP=13, KEYDOWN=14, TRIGGER=15, ACTION=16, PORTAL=17, 
		EQUAL=18, FLOAT=19, KEY=20, NAME=21, WHITESPACE=22, COMMENT=23;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'board'", "'gravity'", "'friction1'", "'friction2'", "'ball'", "'squareBumper'", 
		"'circleBumper'", "'triangleBumper'", "'leftFlipper'", "'rightFlipper'", 
		"'absorber'", "'fire'", "'keyup'", "'keydown'", "'trigger'", "'action'", 
		"'portal'", "'='", "FLOAT", "'key'", "NAME", "WHITESPACE", "COMMENT"
	};
	public static final String[] ruleNames = {
		"BOARD", "GRAVITY", "FRICTION1", "FRICTION2", "BALL", "SQUARE_BUMPER", 
		"CIRCLE_BUMPER", "TRIANGULAR_BUMPER", "LEFT_FLIPPER", "RIGHT_FLIPPER", 
		"ABSORBER", "FIRE", "KEYUP", "KEYDOWN", "TRIGGER", "ACTION", "PORTAL", 
		"EQUAL", "FLOAT", "KEY", "NAME", "WHITESPACE", "COMMENT"
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
		case 21: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;

		case 22: COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\2\4\31\u0102\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\24"+
		"\5\24\u00d0\n\24\3\24\6\24\u00d3\n\24\r\24\16\24\u00d4\3\24\3\24\7\24"+
		"\u00d9\n\24\f\24\16\24\u00dc\13\24\3\24\5\24\u00df\n\24\3\24\6\24\u00e2"+
		"\n\24\r\24\16\24\u00e3\5\24\u00e6\n\24\3\25\3\25\3\25\3\25\3\26\3\26\7"+
		"\26\u00ee\n\26\f\26\16\26\u00f1\13\26\3\27\6\27\u00f4\n\27\r\27\16\27"+
		"\u00f5\3\27\3\27\3\30\3\30\7\30\u00fc\n\30\f\30\16\30\u00ff\13\30\3\30"+
		"\3\30\2\31\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1"+
		"\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25"+
		"\1)\26\1+\27\1-\30\2/\31\3\3\2\t\3\62;\3\62;\3\62;\5C\\aac|\6\62;C\\a"+
		"ac|\5\13\f\17\17\"\"\4\f\f\17\17\u010a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2\5\67\3\2\2\2\7?\3"+
		"\2\2\2\tI\3\2\2\2\13S\3\2\2\2\rX\3\2\2\2\17e\3\2\2\2\21r\3\2\2\2\23\u0081"+
		"\3\2\2\2\25\u008d\3\2\2\2\27\u009a\3\2\2\2\31\u00a3\3\2\2\2\33\u00a8\3"+
		"\2\2\2\35\u00ae\3\2\2\2\37\u00b6\3\2\2\2!\u00be\3\2\2\2#\u00c5\3\2\2\2"+
		"%\u00cc\3\2\2\2\'\u00cf\3\2\2\2)\u00e7\3\2\2\2+\u00eb\3\2\2\2-\u00f3\3"+
		"\2\2\2/\u00f9\3\2\2\2\61\62\7d\2\2\62\63\7q\2\2\63\64\7c\2\2\64\65\7t"+
		"\2\2\65\66\7f\2\2\66\4\3\2\2\2\678\7i\2\289\7t\2\29:\7c\2\2:;\7x\2\2;"+
		"<\7k\2\2<=\7v\2\2=>\7{\2\2>\6\3\2\2\2?@\7h\2\2@A\7t\2\2AB\7k\2\2BC\7e"+
		"\2\2CD\7v\2\2DE\7k\2\2EF\7q\2\2FG\7p\2\2GH\7\63\2\2H\b\3\2\2\2IJ\7h\2"+
		"\2JK\7t\2\2KL\7k\2\2LM\7e\2\2MN\7v\2\2NO\7k\2\2OP\7q\2\2PQ\7p\2\2QR\7"+
		"\64\2\2R\n\3\2\2\2ST\7d\2\2TU\7c\2\2UV\7n\2\2VW\7n\2\2W\f\3\2\2\2XY\7"+
		"u\2\2YZ\7s\2\2Z[\7w\2\2[\\\7c\2\2\\]\7t\2\2]^\7g\2\2^_\7D\2\2_`\7w\2\2"+
		"`a\7o\2\2ab\7r\2\2bc\7g\2\2cd\7t\2\2d\16\3\2\2\2ef\7e\2\2fg\7k\2\2gh\7"+
		"t\2\2hi\7e\2\2ij\7n\2\2jk\7g\2\2kl\7D\2\2lm\7w\2\2mn\7o\2\2no\7r\2\2o"+
		"p\7g\2\2pq\7t\2\2q\20\3\2\2\2rs\7v\2\2st\7t\2\2tu\7k\2\2uv\7c\2\2vw\7"+
		"p\2\2wx\7i\2\2xy\7n\2\2yz\7g\2\2z{\7D\2\2{|\7w\2\2|}\7o\2\2}~\7r\2\2~"+
		"\177\7g\2\2\177\u0080\7t\2\2\u0080\22\3\2\2\2\u0081\u0082\7n\2\2\u0082"+
		"\u0083\7g\2\2\u0083\u0084\7h\2\2\u0084\u0085\7v\2\2\u0085\u0086\7H\2\2"+
		"\u0086\u0087\7n\2\2\u0087\u0088\7k\2\2\u0088\u0089\7r\2\2\u0089\u008a"+
		"\7r\2\2\u008a\u008b\7g\2\2\u008b\u008c\7t\2\2\u008c\24\3\2\2\2\u008d\u008e"+
		"\7t\2\2\u008e\u008f\7k\2\2\u008f\u0090\7i\2\2\u0090\u0091\7j\2\2\u0091"+
		"\u0092\7v\2\2\u0092\u0093\7H\2\2\u0093\u0094\7n\2\2\u0094\u0095\7k\2\2"+
		"\u0095\u0096\7r\2\2\u0096\u0097\7r\2\2\u0097\u0098\7g\2\2\u0098\u0099"+
		"\7t\2\2\u0099\26\3\2\2\2\u009a\u009b\7c\2\2\u009b\u009c\7d\2\2\u009c\u009d"+
		"\7u\2\2\u009d\u009e\7q\2\2\u009e\u009f\7t\2\2\u009f\u00a0\7d\2\2\u00a0"+
		"\u00a1\7g\2\2\u00a1\u00a2\7t\2\2\u00a2\30\3\2\2\2\u00a3\u00a4\7h\2\2\u00a4"+
		"\u00a5\7k\2\2\u00a5\u00a6\7t\2\2\u00a6\u00a7\7g\2\2\u00a7\32\3\2\2\2\u00a8"+
		"\u00a9\7m\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7{\2\2\u00ab\u00ac\7w\2\2"+
		"\u00ac\u00ad\7r\2\2\u00ad\34\3\2\2\2\u00ae\u00af\7m\2\2\u00af\u00b0\7"+
		"g\2\2\u00b0\u00b1\7{\2\2\u00b1\u00b2\7f\2\2\u00b2\u00b3\7q\2\2\u00b3\u00b4"+
		"\7y\2\2\u00b4\u00b5\7p\2\2\u00b5\36\3\2\2\2\u00b6\u00b7\7v\2\2\u00b7\u00b8"+
		"\7t\2\2\u00b8\u00b9\7k\2\2\u00b9\u00ba\7i\2\2\u00ba\u00bb\7i\2\2\u00bb"+
		"\u00bc\7g\2\2\u00bc\u00bd\7t\2\2\u00bd \3\2\2\2\u00be\u00bf\7c\2\2\u00bf"+
		"\u00c0\7e\2\2\u00c0\u00c1\7v\2\2\u00c1\u00c2\7k\2\2\u00c2\u00c3\7q\2\2"+
		"\u00c3\u00c4\7p\2\2\u00c4\"\3\2\2\2\u00c5\u00c6\7r\2\2\u00c6\u00c7\7q"+
		"\2\2\u00c7\u00c8\7t\2\2\u00c8\u00c9\7v\2\2\u00c9\u00ca\7c\2\2\u00ca\u00cb"+
		"\7n\2\2\u00cb$\3\2\2\2\u00cc\u00cd\7?\2\2\u00cd&\3\2\2\2\u00ce\u00d0\7"+
		"/\2\2\u00cf\u00ce\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00e5\3\2\2\2\u00d1"+
		"\u00d3\t\2\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00da\7\60\2\2\u00d7"+
		"\u00d9\t\3\2\2\u00d8\u00d7\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da\u00d8\3\2"+
		"\2\2\u00da\u00db\3\2\2\2\u00db\u00e6\3\2\2\2\u00dc\u00da\3\2\2\2\u00dd"+
		"\u00df\7\60\2\2\u00de\u00dd\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e1\3"+
		"\2\2\2\u00e0\u00e2\t\4\2\2\u00e1\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3"+
		"\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e6\3\2\2\2\u00e5\u00d2\3\2"+
		"\2\2\u00e5\u00de\3\2\2\2\u00e6(\3\2\2\2\u00e7\u00e8\7m\2\2\u00e8\u00e9"+
		"\7g\2\2\u00e9\u00ea\7{\2\2\u00ea*\3\2\2\2\u00eb\u00ef\t\5\2\2\u00ec\u00ee"+
		"\t\6\2\2\u00ed\u00ec\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef"+
		"\u00f0\3\2\2\2\u00f0,\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f4\t\7\2\2"+
		"\u00f3\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6"+
		"\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\b\27\2\2\u00f8.\3\2\2\2\u00f9"+
		"\u00fd\7%\2\2\u00fa\u00fc\n\b\2\2\u00fb\u00fa\3\2\2\2\u00fc\u00ff\3\2"+
		"\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0100\3\2\2\2\u00ff"+
		"\u00fd\3\2\2\2\u0100\u0101\b\30\3\2\u0101\60\3\2\2\2\f\2\u00cf\u00d4\u00da"+
		"\u00de\u00e3\u00e5\u00ef\u00f5\u00fd";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}
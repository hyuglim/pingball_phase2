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
		ABSORBER=11, SPAWNER=12, FIRE=13, KEYUP=14, KEYDOWN=15, TRIGGER=16, ACTION=17, 
		PORTAL=18, EQUAL=19, FLOAT=20, KEY=21, NAME=22, WHITESPACE=23, COMMENT=24;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'board'", "'gravity'", "'friction1'", "'friction2'", "'ball'", "'squareBumper'", 
		"'circleBumper'", "'triangleBumper'", "'leftFlipper'", "'rightFlipper'", 
		"'absorber'", "'spawner'", "'fire'", "'keyup'", "'keydown'", "'trigger'", 
		"'action'", "'portal'", "'='", "FLOAT", "'key'", "NAME", "WHITESPACE", 
		"COMMENT"
	};
	public static final String[] ruleNames = {
		"BOARD", "GRAVITY", "FRICTION1", "FRICTION2", "BALL", "SQUARE_BUMPER", 
		"CIRCLE_BUMPER", "TRIANGULAR_BUMPER", "LEFT_FLIPPER", "RIGHT_FLIPPER", 
		"ABSORBER", "SPAWNER", "FIRE", "KEYUP", "KEYDOWN", "TRIGGER", "ACTION", 
		"PORTAL", "EQUAL", "FLOAT", "KEY", "NAME", "WHITESPACE", "COMMENT"
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
		case 22: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;

		case 23: COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\2\4\32\u010c\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
		"\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\25\5\25\u00da\n\25\3\25"+
		"\6\25\u00dd\n\25\r\25\16\25\u00de\3\25\3\25\7\25\u00e3\n\25\f\25\16\25"+
		"\u00e6\13\25\3\25\5\25\u00e9\n\25\3\25\6\25\u00ec\n\25\r\25\16\25\u00ed"+
		"\5\25\u00f0\n\25\3\26\3\26\3\26\3\26\3\27\3\27\7\27\u00f8\n\27\f\27\16"+
		"\27\u00fb\13\27\3\30\6\30\u00fe\n\30\r\30\16\30\u00ff\3\30\3\30\3\31\3"+
		"\31\7\31\u0106\n\31\f\31\16\31\u0109\13\31\3\31\3\31\2\32\3\3\1\5\4\1"+
		"\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1"+
		"\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31"+
		"\2\61\32\3\3\2\t\3\62;\3\62;\3\62;\5C\\aac|\6\62;C\\aac|\5\13\f\17\17"+
		"\"\"\4\f\f\17\17\u0114\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\59\3\2\2\2\7A\3\2\2"+
		"\2\tK\3\2\2\2\13U\3\2\2\2\rZ\3\2\2\2\17g\3\2\2\2\21t\3\2\2\2\23\u0083"+
		"\3\2\2\2\25\u008f\3\2\2\2\27\u009c\3\2\2\2\31\u00a5\3\2\2\2\33\u00ad\3"+
		"\2\2\2\35\u00b2\3\2\2\2\37\u00b8\3\2\2\2!\u00c0\3\2\2\2#\u00c8\3\2\2\2"+
		"%\u00cf\3\2\2\2\'\u00d6\3\2\2\2)\u00d9\3\2\2\2+\u00f1\3\2\2\2-\u00f5\3"+
		"\2\2\2/\u00fd\3\2\2\2\61\u0103\3\2\2\2\63\64\7d\2\2\64\65\7q\2\2\65\66"+
		"\7c\2\2\66\67\7t\2\2\678\7f\2\28\4\3\2\2\29:\7i\2\2:;\7t\2\2;<\7c\2\2"+
		"<=\7x\2\2=>\7k\2\2>?\7v\2\2?@\7{\2\2@\6\3\2\2\2AB\7h\2\2BC\7t\2\2CD\7"+
		"k\2\2DE\7e\2\2EF\7v\2\2FG\7k\2\2GH\7q\2\2HI\7p\2\2IJ\7\63\2\2J\b\3\2\2"+
		"\2KL\7h\2\2LM\7t\2\2MN\7k\2\2NO\7e\2\2OP\7v\2\2PQ\7k\2\2QR\7q\2\2RS\7"+
		"p\2\2ST\7\64\2\2T\n\3\2\2\2UV\7d\2\2VW\7c\2\2WX\7n\2\2XY\7n\2\2Y\f\3\2"+
		"\2\2Z[\7u\2\2[\\\7s\2\2\\]\7w\2\2]^\7c\2\2^_\7t\2\2_`\7g\2\2`a\7D\2\2"+
		"ab\7w\2\2bc\7o\2\2cd\7r\2\2de\7g\2\2ef\7t\2\2f\16\3\2\2\2gh\7e\2\2hi\7"+
		"k\2\2ij\7t\2\2jk\7e\2\2kl\7n\2\2lm\7g\2\2mn\7D\2\2no\7w\2\2op\7o\2\2p"+
		"q\7r\2\2qr\7g\2\2rs\7t\2\2s\20\3\2\2\2tu\7v\2\2uv\7t\2\2vw\7k\2\2wx\7"+
		"c\2\2xy\7p\2\2yz\7i\2\2z{\7n\2\2{|\7g\2\2|}\7D\2\2}~\7w\2\2~\177\7o\2"+
		"\2\177\u0080\7r\2\2\u0080\u0081\7g\2\2\u0081\u0082\7t\2\2\u0082\22\3\2"+
		"\2\2\u0083\u0084\7n\2\2\u0084\u0085\7g\2\2\u0085\u0086\7h\2\2\u0086\u0087"+
		"\7v\2\2\u0087\u0088\7H\2\2\u0088\u0089\7n\2\2\u0089\u008a\7k\2\2\u008a"+
		"\u008b\7r\2\2\u008b\u008c\7r\2\2\u008c\u008d\7g\2\2\u008d\u008e\7t\2\2"+
		"\u008e\24\3\2\2\2\u008f\u0090\7t\2\2\u0090\u0091\7k\2\2\u0091\u0092\7"+
		"i\2\2\u0092\u0093\7j\2\2\u0093\u0094\7v\2\2\u0094\u0095\7H\2\2\u0095\u0096"+
		"\7n\2\2\u0096\u0097\7k\2\2\u0097\u0098\7r\2\2\u0098\u0099\7r\2\2\u0099"+
		"\u009a\7g\2\2\u009a\u009b\7t\2\2\u009b\26\3\2\2\2\u009c\u009d\7c\2\2\u009d"+
		"\u009e\7d\2\2\u009e\u009f\7u\2\2\u009f\u00a0\7q\2\2\u00a0\u00a1\7t\2\2"+
		"\u00a1\u00a2\7d\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7t\2\2\u00a4\30\3\2"+
		"\2\2\u00a5\u00a6\7u\2\2\u00a6\u00a7\7r\2\2\u00a7\u00a8\7c\2\2\u00a8\u00a9"+
		"\7y\2\2\u00a9\u00aa\7p\2\2\u00aa\u00ab\7g\2\2\u00ab\u00ac\7t\2\2\u00ac"+
		"\32\3\2\2\2\u00ad\u00ae\7h\2\2\u00ae\u00af\7k\2\2\u00af\u00b0\7t\2\2\u00b0"+
		"\u00b1\7g\2\2\u00b1\34\3\2\2\2\u00b2\u00b3\7m\2\2\u00b3\u00b4\7g\2\2\u00b4"+
		"\u00b5\7{\2\2\u00b5\u00b6\7w\2\2\u00b6\u00b7\7r\2\2\u00b7\36\3\2\2\2\u00b8"+
		"\u00b9\7m\2\2\u00b9\u00ba\7g\2\2\u00ba\u00bb\7{\2\2\u00bb\u00bc\7f\2\2"+
		"\u00bc\u00bd\7q\2\2\u00bd\u00be\7y\2\2\u00be\u00bf\7p\2\2\u00bf \3\2\2"+
		"\2\u00c0\u00c1\7v\2\2\u00c1\u00c2\7t\2\2\u00c2\u00c3\7k\2\2\u00c3\u00c4"+
		"\7i\2\2\u00c4\u00c5\7i\2\2\u00c5\u00c6\7g\2\2\u00c6\u00c7\7t\2\2\u00c7"+
		"\"\3\2\2\2\u00c8\u00c9\7c\2\2\u00c9\u00ca\7e\2\2\u00ca\u00cb\7v\2\2\u00cb"+
		"\u00cc\7k\2\2\u00cc\u00cd\7q\2\2\u00cd\u00ce\7p\2\2\u00ce$\3\2\2\2\u00cf"+
		"\u00d0\7r\2\2\u00d0\u00d1\7q\2\2\u00d1\u00d2\7t\2\2\u00d2\u00d3\7v\2\2"+
		"\u00d3\u00d4\7c\2\2\u00d4\u00d5\7n\2\2\u00d5&\3\2\2\2\u00d6\u00d7\7?\2"+
		"\2\u00d7(\3\2\2\2\u00d8\u00da\7/\2\2\u00d9\u00d8\3\2\2\2\u00d9\u00da\3"+
		"\2\2\2\u00da\u00ef\3\2\2\2\u00db\u00dd\t\2\2\2\u00dc\u00db\3\2\2\2\u00dd"+
		"\u00de\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e0\3\2"+
		"\2\2\u00e0\u00e4\7\60\2\2\u00e1\u00e3\t\3\2\2\u00e2\u00e1\3\2\2\2\u00e3"+
		"\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00f0\3\2"+
		"\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e9\7\60\2\2\u00e8\u00e7\3\2\2\2\u00e8"+
		"\u00e9\3\2\2\2\u00e9\u00eb\3\2\2\2\u00ea\u00ec\t\4\2\2\u00eb\u00ea\3\2"+
		"\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee"+
		"\u00f0\3\2\2\2\u00ef\u00dc\3\2\2\2\u00ef\u00e8\3\2\2\2\u00f0*\3\2\2\2"+
		"\u00f1\u00f2\7m\2\2\u00f2\u00f3\7g\2\2\u00f3\u00f4\7{\2\2\u00f4,\3\2\2"+
		"\2\u00f5\u00f9\t\5\2\2\u00f6\u00f8\t\6\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00fb"+
		"\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa.\3\2\2\2\u00fb"+
		"\u00f9\3\2\2\2\u00fc\u00fe\t\7\2\2\u00fd\u00fc\3\2\2\2\u00fe\u00ff\3\2"+
		"\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"\u0102\b\30\2\2\u0102\60\3\2\2\2\u0103\u0107\7%\2\2\u0104\u0106\n\b\2"+
		"\2\u0105\u0104\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0108"+
		"\3\2\2\2\u0108\u010a\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u010b\b\31\3\2"+
		"\u010b\62\3\2\2\2\f\2\u00d9\u00de\u00e4\u00e8\u00ed\u00ef\u00f9\u00ff"+
		"\u0107";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}
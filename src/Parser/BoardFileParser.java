// Generated from BoardFile.g4 by ANTLR 4.0

package Parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BoardFileParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BOARD=1, GRAVITY=2, FRICTION1=3, FRICTION2=4, BALL=5, SQUARE_BUMPER=6, 
		CIRCLE_BUMPER=7, TRIANGULAR_BUMPER=8, LEFT_FLIPPER=9, RIGHT_FLIPPER=10, 
		ABSORBER=11, FIRE=12, TRIGGER=13, ACTION=14, EQUAL=15, FLOAT=16, NAME=17, 
		WHITESPACE=18, COMMENT=19;
	public static final String[] tokenNames = {
		"<INVALID>", "'board'", "'gravity'", "'friction1'", "'friction2'", "'ball'", 
		"'squareBumper'", "'circleBumper'", "'triangleBumper'", "'leftFlipper'", 
		"'rightFlipper'", "'absorber'", "'fire'", "'trigger'", "'action'", "'='", 
		"FLOAT", "NAME", "WHITESPACE", "COMMENT"
	};
	public static final int
		RULE_file = 0, RULE_declaration = 1, RULE_line = 2, RULE_board = 3, RULE_ball = 4, 
		RULE_square = 5, RULE_circle = 6, RULE_triangle = 7, RULE_leftFlipper = 8, 
		RULE_rightFlipper = 9, RULE_absorber = 10, RULE_fire = 11, RULE_attribute = 12, 
		RULE_value = 13;
	public static final String[] ruleNames = {
		"file", "declaration", "line", "board", "ball", "square", "circle", "triangle", 
		"leftFlipper", "rightFlipper", "absorber", "fire", "attribute", "value"
	};

	@Override
	public String getGrammarFileName() { return "BoardFile.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public BoardFileParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public TerminalNode EOF() { return getToken(BoardFileParser.EOF, 0); }
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28); declaration();
			setState(29); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationContext extends ParserRuleContext {
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public BoardContext board() {
			return getRuleContext(BoardContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31); board();
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BALL) | (1L << SQUARE_BUMPER) | (1L << CIRCLE_BUMPER) | (1L << TRIANGULAR_BUMPER) | (1L << LEFT_FLIPPER) | (1L << RIGHT_FLIPPER) | (1L << ABSORBER) | (1L << FIRE))) != 0)) {
				{
				{
				setState(32); line();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LineContext extends ParserRuleContext {
		public AbsorberContext absorber() {
			return getRuleContext(AbsorberContext.class,0);
		}
		public BallContext ball() {
			return getRuleContext(BallContext.class,0);
		}
		public TriangleContext triangle() {
			return getRuleContext(TriangleContext.class,0);
		}
		public RightFlipperContext rightFlipper() {
			return getRuleContext(RightFlipperContext.class,0);
		}
		public CircleContext circle() {
			return getRuleContext(CircleContext.class,0);
		}
		public SquareContext square() {
			return getRuleContext(SquareContext.class,0);
		}
		public FireContext fire() {
			return getRuleContext(FireContext.class,0);
		}
		public LeftFlipperContext leftFlipper() {
			return getRuleContext(LeftFlipperContext.class,0);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitLine(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_line);
		try {
			setState(46);
			switch (_input.LA(1)) {
			case BALL:
				enterOuterAlt(_localctx, 1);
				{
				setState(38); ball();
				}
				break;
			case SQUARE_BUMPER:
				enterOuterAlt(_localctx, 2);
				{
				setState(39); square();
				}
				break;
			case CIRCLE_BUMPER:
				enterOuterAlt(_localctx, 3);
				{
				setState(40); circle();
				}
				break;
			case TRIANGULAR_BUMPER:
				enterOuterAlt(_localctx, 4);
				{
				setState(41); triangle();
				}
				break;
			case LEFT_FLIPPER:
				enterOuterAlt(_localctx, 5);
				{
				setState(42); leftFlipper();
				}
				break;
			case RIGHT_FLIPPER:
				enterOuterAlt(_localctx, 6);
				{
				setState(43); rightFlipper();
				}
				break;
			case ABSORBER:
				enterOuterAlt(_localctx, 7);
				{
				setState(44); absorber();
				}
				break;
			case FIRE:
				enterOuterAlt(_localctx, 8);
				{
				setState(45); fire();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoardContext extends ParserRuleContext {
		public TerminalNode FRICTION2() { return getToken(BoardFileParser.FRICTION2, 0); }
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode GRAVITY() { return getToken(BoardFileParser.GRAVITY, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public TerminalNode BOARD() { return getToken(BoardFileParser.BOARD, 0); }
		public TerminalNode EQUAL(int i) {
			return getToken(BoardFileParser.EQUAL, i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(BoardFileParser.EQUAL); }
		public TerminalNode FRICTION1() { return getToken(BoardFileParser.FRICTION1, 0); }
		public BoardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_board; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterBoard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitBoard(this);
		}
	}

	public final BoardContext board() throws RecognitionException {
		BoardContext _localctx = new BoardContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_board);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); match(BOARD);
			{
			setState(49); attribute();
			setState(50); match(EQUAL);
			setState(51); value();
			}
			setState(55);
			_la = _input.LA(1);
			if (_la==GRAVITY) {
				{
				setState(52); match(GRAVITY);
				setState(53); match(EQUAL);
				setState(54); value();
				}
			}

			setState(60);
			_la = _input.LA(1);
			if (_la==FRICTION1) {
				{
				setState(57); match(FRICTION1);
				setState(58); match(EQUAL);
				setState(59); value();
				}
			}

			setState(65);
			_la = _input.LA(1);
			if (_la==FRICTION2) {
				{
				setState(62); match(FRICTION2);
				setState(63); match(EQUAL);
				setState(64); value();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BallContext extends ParserRuleContext {
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public TerminalNode BALL() { return getToken(BoardFileParser.BALL, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public TerminalNode EQUAL(int i) {
			return getToken(BoardFileParser.EQUAL, i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(BoardFileParser.EQUAL); }
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public BallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ball; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterBall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitBall(this);
		}
	}

	public final BallContext ball() throws RecognitionException {
		BallContext _localctx = new BallContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ball);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); match(BALL);
			setState(72); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68); attribute();
				setState(69); match(EQUAL);
				setState(70); value();
				}
				}
				setState(74); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SquareContext extends ParserRuleContext {
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public TerminalNode EQUAL(int i) {
			return getToken(BoardFileParser.EQUAL, i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(BoardFileParser.EQUAL); }
		public TerminalNode SQUARE_BUMPER() { return getToken(BoardFileParser.SQUARE_BUMPER, 0); }
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public SquareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_square; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterSquare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitSquare(this);
		}
	}

	public final SquareContext square() throws RecognitionException {
		SquareContext _localctx = new SquareContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_square);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); match(SQUARE_BUMPER);
			setState(81); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(77); attribute();
				setState(78); match(EQUAL);
				setState(79); value();
				}
				}
				setState(83); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CircleContext extends ParserRuleContext {
		public TerminalNode CIRCLE_BUMPER() { return getToken(BoardFileParser.CIRCLE_BUMPER, 0); }
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public TerminalNode EQUAL(int i) {
			return getToken(BoardFileParser.EQUAL, i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(BoardFileParser.EQUAL); }
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public CircleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_circle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterCircle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitCircle(this);
		}
	}

	public final CircleContext circle() throws RecognitionException {
		CircleContext _localctx = new CircleContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_circle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85); match(CIRCLE_BUMPER);
			setState(90); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(86); attribute();
				setState(87); match(EQUAL);
				setState(88); value();
				}
				}
				setState(92); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriangleContext extends ParserRuleContext {
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode TRIANGULAR_BUMPER() { return getToken(BoardFileParser.TRIANGULAR_BUMPER, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public TerminalNode EQUAL(int i) {
			return getToken(BoardFileParser.EQUAL, i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(BoardFileParser.EQUAL); }
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public TriangleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triangle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterTriangle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitTriangle(this);
		}
	}

	public final TriangleContext triangle() throws RecognitionException {
		TriangleContext _localctx = new TriangleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_triangle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94); match(TRIANGULAR_BUMPER);
			setState(99); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(95); attribute();
				setState(96); match(EQUAL);
				setState(97); value();
				}
				}
				setState(101); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeftFlipperContext extends ParserRuleContext {
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode LEFT_FLIPPER() { return getToken(BoardFileParser.LEFT_FLIPPER, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public TerminalNode EQUAL(int i) {
			return getToken(BoardFileParser.EQUAL, i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(BoardFileParser.EQUAL); }
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public LeftFlipperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftFlipper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterLeftFlipper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitLeftFlipper(this);
		}
	}

	public final LeftFlipperContext leftFlipper() throws RecognitionException {
		LeftFlipperContext _localctx = new LeftFlipperContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_leftFlipper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); match(LEFT_FLIPPER);
			setState(108); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(104); attribute();
				setState(105); match(EQUAL);
				setState(106); value();
				}
				}
				setState(110); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RightFlipperContext extends ParserRuleContext {
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public TerminalNode RIGHT_FLIPPER() { return getToken(BoardFileParser.RIGHT_FLIPPER, 0); }
		public TerminalNode EQUAL(int i) {
			return getToken(BoardFileParser.EQUAL, i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(BoardFileParser.EQUAL); }
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public RightFlipperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightFlipper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterRightFlipper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitRightFlipper(this);
		}
	}

	public final RightFlipperContext rightFlipper() throws RecognitionException {
		RightFlipperContext _localctx = new RightFlipperContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_rightFlipper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); match(RIGHT_FLIPPER);
			setState(117); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(113); attribute();
				setState(114); match(EQUAL);
				setState(115); value();
				}
				}
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbsorberContext extends ParserRuleContext {
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public TerminalNode ABSORBER() { return getToken(BoardFileParser.ABSORBER, 0); }
		public TerminalNode EQUAL(int i) {
			return getToken(BoardFileParser.EQUAL, i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(BoardFileParser.EQUAL); }
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public AbsorberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absorber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterAbsorber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitAbsorber(this);
		}
	}

	public final AbsorberContext absorber() throws RecognitionException {
		AbsorberContext _localctx = new AbsorberContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_absorber);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121); match(ABSORBER);
			setState(126); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(122); attribute();
				setState(123); match(EQUAL);
				setState(124); value();
				}
				}
				setState(128); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FireContext extends ParserRuleContext {
		public TerminalNode FIRE() { return getToken(BoardFileParser.FIRE, 0); }
		public List<TerminalNode> NAME() { return getTokens(BoardFileParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(BoardFileParser.NAME, i);
		}
		public TerminalNode EQUAL(int i) {
			return getToken(BoardFileParser.EQUAL, i);
		}
		public TerminalNode ACTION() { return getToken(BoardFileParser.ACTION, 0); }
		public List<TerminalNode> EQUAL() { return getTokens(BoardFileParser.EQUAL); }
		public TerminalNode TRIGGER() { return getToken(BoardFileParser.TRIGGER, 0); }
		public FireContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fire; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterFire(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitFire(this);
		}
	}

	public final FireContext fire() throws RecognitionException {
		FireContext _localctx = new FireContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_fire);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); match(FIRE);
			setState(131); match(TRIGGER);
			setState(132); match(EQUAL);
			setState(133); match(NAME);
			setState(134); match(ACTION);
			setState(135); match(EQUAL);
			setState(136); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardFileParser.NAME, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardFileParser.NAME, 0); }
		public TerminalNode FLOAT() { return getToken(BoardFileParser.FLOAT, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			_la = _input.LA(1);
			if ( !(_la==FLOAT || _la==NAME) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\2\3\25\u0091\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2"+
		"\3\2\3\3\3\3\7\3$\n\3\f\3\16\3\'\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\5\4\61\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5:\n\5\3\5\3\5\3\5\5\5?\n\5"+
		"\3\5\3\5\3\5\5\5D\n\5\3\6\3\6\3\6\3\6\3\6\6\6K\n\6\r\6\16\6L\3\7\3\7\3"+
		"\7\3\7\3\7\6\7T\n\7\r\7\16\7U\3\b\3\b\3\b\3\b\3\b\6\b]\n\b\r\b\16\b^\3"+
		"\t\3\t\3\t\3\t\3\t\6\tf\n\t\r\t\16\tg\3\n\3\n\3\n\3\n\3\n\6\no\n\n\r\n"+
		"\16\np\3\13\3\13\3\13\3\13\3\13\6\13x\n\13\r\13\16\13y\3\f\3\f\3\f\3\f"+
		"\3\f\6\f\u0081\n\f\r\f\16\f\u0082\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\17\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\3\3\22"+
		"\23\u0094\2\36\3\2\2\2\4!\3\2\2\2\6\60\3\2\2\2\b\62\3\2\2\2\nE\3\2\2\2"+
		"\fN\3\2\2\2\16W\3\2\2\2\20`\3\2\2\2\22i\3\2\2\2\24r\3\2\2\2\26{\3\2\2"+
		"\2\30\u0084\3\2\2\2\32\u008c\3\2\2\2\34\u008e\3\2\2\2\36\37\5\4\3\2\37"+
		" \7\1\2\2 \3\3\2\2\2!%\5\b\5\2\"$\5\6\4\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2"+
		"\2\2%&\3\2\2\2&\5\3\2\2\2\'%\3\2\2\2(\61\5\n\6\2)\61\5\f\7\2*\61\5\16"+
		"\b\2+\61\5\20\t\2,\61\5\22\n\2-\61\5\24\13\2.\61\5\26\f\2/\61\5\30\r\2"+
		"\60(\3\2\2\2\60)\3\2\2\2\60*\3\2\2\2\60+\3\2\2\2\60,\3\2\2\2\60-\3\2\2"+
		"\2\60.\3\2\2\2\60/\3\2\2\2\61\7\3\2\2\2\62\63\7\3\2\2\639\5\32\16\2\64"+
		"\65\7\21\2\2\65D\5\34\17\2\66\67\7\4\2\2\678\7\21\2\28:\5\34\17\29\66"+
		"\3\2\2\29:\3\2\2\2:>\3\2\2\2;<\7\5\2\2<=\7\21\2\2=?\5\34\17\2>;\3\2\2"+
		"\2>?\3\2\2\2?C\3\2\2\2@A\7\6\2\2AB\7\21\2\2BD\5\34\17\2C@\3\2\2\2CD\3"+
		"\2\2\2D\t\3\2\2\2EJ\7\7\2\2FG\5\32\16\2GH\7\21\2\2HI\5\34\17\2IK\3\2\2"+
		"\2JF\3\2\2\2KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2M\13\3\2\2\2NS\7\b\2\2OP\5\32"+
		"\16\2PQ\7\21\2\2QR\5\34\17\2RT\3\2\2\2SO\3\2\2\2TU\3\2\2\2US\3\2\2\2U"+
		"V\3\2\2\2V\r\3\2\2\2W\\\7\t\2\2XY\5\32\16\2YZ\7\21\2\2Z[\5\34\17\2[]\3"+
		"\2\2\2\\X\3\2\2\2]^\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_\17\3\2\2\2`e\7\n\2\2"+
		"ab\5\32\16\2bc\7\21\2\2cd\5\34\17\2df\3\2\2\2ea\3\2\2\2fg\3\2\2\2ge\3"+
		"\2\2\2gh\3\2\2\2h\21\3\2\2\2in\7\13\2\2jk\5\32\16\2kl\7\21\2\2lm\5\34"+
		"\17\2mo\3\2\2\2nj\3\2\2\2op\3\2\2\2pn\3\2\2\2pq\3\2\2\2q\23\3\2\2\2rw"+
		"\7\f\2\2st\5\32\16\2tu\7\21\2\2uv\5\34\17\2vx\3\2\2\2ws\3\2\2\2xy\3\2"+
		"\2\2yw\3\2\2\2yz\3\2\2\2z\25\3\2\2\2{\u0080\7\r\2\2|}\5\32\16\2}~\7\21"+
		"\2\2~\177\5\34\17\2\177\u0081\3\2\2\2\u0080|\3\2\2\2\u0081\u0082\3\2\2"+
		"\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\27\3\2\2\2\u0084\u0085"+
		"\7\16\2\2\u0085\u0086\7\17\2\2\u0086\u0087\7\21\2\2\u0087\u0088\7\23\2"+
		"\2\u0088\u0089\7\20\2\2\u0089\u008a\7\21\2\2\u008a\u008b\7\23\2\2\u008b"+
		"\31\3\2\2\2\u008c\u008d\7\23\2\2\u008d\33\3\2\2\2\u008e\u008f\t\2\2\2"+
		"\u008f\35\3\2\2\2\16%\609>CLU^gpy\u0082";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}
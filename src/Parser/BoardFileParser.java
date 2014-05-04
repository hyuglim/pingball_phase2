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
		ABSORBER=11, FIRE=12, KEYUP=13, KEYDOWN=14, TRIGGER=15, ACTION=16, PORTAL=17, 
		EQUAL=18, FLOAT=19, KEY=20, NAME=21, WHITESPACE=22, COMMENT=23;
	public static final String[] tokenNames = {
		"<INVALID>", "'board'", "'gravity'", "'friction1'", "'friction2'", "'ball'", 
		"'squareBumper'", "'circleBumper'", "'triangleBumper'", "'leftFlipper'", 
		"'rightFlipper'", "'absorber'", "'fire'", "'keyup'", "'keydown'", "'trigger'", 
		"'action'", "'portal'", "'='", "FLOAT", "'key'", "NAME", "WHITESPACE", 
		"COMMENT"
	};
	public static final int
		RULE_file = 0, RULE_declaration = 1, RULE_line = 2, RULE_board = 3, RULE_ball = 4, 
		RULE_square = 5, RULE_circle = 6, RULE_triangle = 7, RULE_leftFlipper = 8, 
		RULE_rightFlipper = 9, RULE_absorber = 10, RULE_portal = 11, RULE_fire = 12, 
		RULE_keytrigger = 13, RULE_attribute = 14, RULE_value = 15;
	public static final String[] ruleNames = {
		"file", "declaration", "line", "board", "ball", "square", "circle", "triangle", 
		"leftFlipper", "rightFlipper", "absorber", "portal", "fire", "keytrigger", 
		"attribute", "value"
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
			setState(32); declaration();
			setState(33); match(EOF);
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
			setState(35); board();
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BALL) | (1L << SQUARE_BUMPER) | (1L << CIRCLE_BUMPER) | (1L << TRIANGULAR_BUMPER) | (1L << LEFT_FLIPPER) | (1L << RIGHT_FLIPPER) | (1L << ABSORBER) | (1L << FIRE) | (1L << KEYUP) | (1L << KEYDOWN) | (1L << PORTAL))) != 0)) {
				{
				{
				setState(36); line();
				}
				}
				setState(41);
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
		public KeytriggerContext keytrigger() {
			return getRuleContext(KeytriggerContext.class,0);
		}
		public TriangleContext triangle() {
			return getRuleContext(TriangleContext.class,0);
		}
		public PortalContext portal() {
			return getRuleContext(PortalContext.class,0);
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
			setState(52);
			switch (_input.LA(1)) {
			case BALL:
				enterOuterAlt(_localctx, 1);
				{
				setState(42); ball();
				}
				break;
			case SQUARE_BUMPER:
				enterOuterAlt(_localctx, 2);
				{
				setState(43); square();
				}
				break;
			case CIRCLE_BUMPER:
				enterOuterAlt(_localctx, 3);
				{
				setState(44); circle();
				}
				break;
			case TRIANGULAR_BUMPER:
				enterOuterAlt(_localctx, 4);
				{
				setState(45); triangle();
				}
				break;
			case LEFT_FLIPPER:
				enterOuterAlt(_localctx, 5);
				{
				setState(46); leftFlipper();
				}
				break;
			case RIGHT_FLIPPER:
				enterOuterAlt(_localctx, 6);
				{
				setState(47); rightFlipper();
				}
				break;
			case ABSORBER:
				enterOuterAlt(_localctx, 7);
				{
				setState(48); absorber();
				}
				break;
			case PORTAL:
				enterOuterAlt(_localctx, 8);
				{
				setState(49); portal();
				}
				break;
			case FIRE:
				enterOuterAlt(_localctx, 9);
				{
				setState(50); fire();
				}
				break;
			case KEYUP:
			case KEYDOWN:
				enterOuterAlt(_localctx, 10);
				{
				setState(51); keytrigger();
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
			setState(54); match(BOARD);
			{
			setState(55); attribute();
			setState(56); match(EQUAL);
			setState(57); value();
			}
			setState(61);
			_la = _input.LA(1);
			if (_la==GRAVITY) {
				{
				setState(58); match(GRAVITY);
				setState(59); match(EQUAL);
				setState(60); value();
				}
			}

			setState(66);
			_la = _input.LA(1);
			if (_la==FRICTION1) {
				{
				setState(63); match(FRICTION1);
				setState(64); match(EQUAL);
				setState(65); value();
				}
			}

			setState(71);
			_la = _input.LA(1);
			if (_la==FRICTION2) {
				{
				setState(68); match(FRICTION2);
				setState(69); match(EQUAL);
				setState(70); value();
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
			setState(73); match(BALL);
			setState(78); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(74); attribute();
				setState(75); match(EQUAL);
				setState(76); value();
				}
				}
				setState(80); 
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
			setState(82); match(SQUARE_BUMPER);
			setState(87); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(83); attribute();
				setState(84); match(EQUAL);
				setState(85); value();
				}
				}
				setState(89); 
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
			setState(91); match(CIRCLE_BUMPER);
			setState(96); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(92); attribute();
				setState(93); match(EQUAL);
				setState(94); value();
				}
				}
				setState(98); 
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
			setState(100); match(TRIANGULAR_BUMPER);
			setState(105); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(101); attribute();
				setState(102); match(EQUAL);
				setState(103); value();
				}
				}
				setState(107); 
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
			setState(109); match(LEFT_FLIPPER);
			setState(114); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(110); attribute();
				setState(111); match(EQUAL);
				setState(112); value();
				}
				}
				setState(116); 
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
			setState(118); match(RIGHT_FLIPPER);
			setState(123); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(119); attribute();
				setState(120); match(EQUAL);
				setState(121); value();
				}
				}
				setState(125); 
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
			setState(127); match(ABSORBER);
			setState(132); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(128); attribute();
				setState(129); match(EQUAL);
				setState(130); value();
				}
				}
				setState(134); 
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

	public static class PortalContext extends ParserRuleContext {
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public TerminalNode PORTAL() { return getToken(BoardFileParser.PORTAL, 0); }
		public TerminalNode EQUAL(int i) {
			return getToken(BoardFileParser.EQUAL, i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(BoardFileParser.EQUAL); }
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public PortalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterPortal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitPortal(this);
		}
	}

	public final PortalContext portal() throws RecognitionException {
		PortalContext _localctx = new PortalContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_portal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136); match(PORTAL);
			setState(141); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(137); attribute();
				setState(138); match(EQUAL);
				setState(139); value();
				}
				}
				setState(143); 
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
		enterRule(_localctx, 24, RULE_fire);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145); match(FIRE);
			setState(146); match(TRIGGER);
			setState(147); match(EQUAL);
			setState(148); match(NAME);
			setState(149); match(ACTION);
			setState(150); match(EQUAL);
			setState(151); match(NAME);
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

	public static class KeytriggerContext extends ParserRuleContext {
		public TerminalNode KEYDOWN() { return getToken(BoardFileParser.KEYDOWN, 0); }
		public List<TerminalNode> NAME() { return getTokens(BoardFileParser.NAME); }
		public TerminalNode KEYUP() { return getToken(BoardFileParser.KEYUP, 0); }
		public TerminalNode NAME(int i) {
			return getToken(BoardFileParser.NAME, i);
		}
		public TerminalNode EQUAL(int i) {
			return getToken(BoardFileParser.EQUAL, i);
		}
		public TerminalNode KEY() { return getToken(BoardFileParser.KEY, 0); }
		public TerminalNode ACTION() { return getToken(BoardFileParser.ACTION, 0); }
		public List<TerminalNode> EQUAL() { return getTokens(BoardFileParser.EQUAL); }
		public KeytriggerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keytrigger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterKeytrigger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitKeytrigger(this);
		}
	}

	public final KeytriggerContext keytrigger() throws RecognitionException {
		KeytriggerContext _localctx = new KeytriggerContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_keytrigger);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			_la = _input.LA(1);
			if ( !(_la==KEYUP || _la==KEYDOWN) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(154); match(KEY);
			setState(155); match(EQUAL);
			setState(156); match(NAME);
			setState(157); match(ACTION);
			setState(158); match(EQUAL);
			setState(159); match(NAME);
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
		enterRule(_localctx, 28, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161); match(NAME);
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
		enterRule(_localctx, 30, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
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
		"\2\3\31\u00a8\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t"+
		"\20\4\21\t\21\3\2\3\2\3\2\3\3\3\3\7\3(\n\3\f\3\16\3+\13\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\67\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5"+
		"\5@\n\5\3\5\3\5\3\5\5\5E\n\5\3\5\3\5\3\5\5\5J\n\5\3\6\3\6\3\6\3\6\3\6"+
		"\6\6Q\n\6\r\6\16\6R\3\7\3\7\3\7\3\7\3\7\6\7Z\n\7\r\7\16\7[\3\b\3\b\3\b"+
		"\3\b\3\b\6\bc\n\b\r\b\16\bd\3\t\3\t\3\t\3\t\3\t\6\tl\n\t\r\t\16\tm\3\n"+
		"\3\n\3\n\3\n\3\n\6\nu\n\n\r\n\16\nv\3\13\3\13\3\13\3\13\3\13\6\13~\n\13"+
		"\r\13\16\13\177\3\f\3\f\3\f\3\f\3\f\6\f\u0087\n\f\r\f\16\f\u0088\3\r\3"+
		"\r\3\r\3\r\3\r\6\r\u0090\n\r\r\r\16\r\u0091\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3"+
		"\21\3\21\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\4\3\17\20\4\25"+
		"\25\27\27\u00ac\2\"\3\2\2\2\4%\3\2\2\2\6\66\3\2\2\2\b8\3\2\2\2\nK\3\2"+
		"\2\2\fT\3\2\2\2\16]\3\2\2\2\20f\3\2\2\2\22o\3\2\2\2\24x\3\2\2\2\26\u0081"+
		"\3\2\2\2\30\u008a\3\2\2\2\32\u0093\3\2\2\2\34\u009b\3\2\2\2\36\u00a3\3"+
		"\2\2\2 \u00a5\3\2\2\2\"#\5\4\3\2#$\7\1\2\2$\3\3\2\2\2%)\5\b\5\2&(\5\6"+
		"\4\2\'&\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\5\3\2\2\2+)\3\2\2\2,\67"+
		"\5\n\6\2-\67\5\f\7\2.\67\5\16\b\2/\67\5\20\t\2\60\67\5\22\n\2\61\67\5"+
		"\24\13\2\62\67\5\26\f\2\63\67\5\30\r\2\64\67\5\32\16\2\65\67\5\34\17\2"+
		"\66,\3\2\2\2\66-\3\2\2\2\66.\3\2\2\2\66/\3\2\2\2\66\60\3\2\2\2\66\61\3"+
		"\2\2\2\66\62\3\2\2\2\66\63\3\2\2\2\66\64\3\2\2\2\66\65\3\2\2\2\67\7\3"+
		"\2\2\289\7\3\2\29?\5\36\20\2:;\7\24\2\2;L\5 \21\2<=\7\4\2\2=>\7\24\2\2"+
		">@\5 \21\2?<\3\2\2\2?@\3\2\2\2@D\3\2\2\2AB\7\5\2\2BC\7\24\2\2CE\5 \21"+
		"\2DA\3\2\2\2DE\3\2\2\2EI\3\2\2\2FG\7\6\2\2GH\7\24\2\2HJ\5 \21\2IF\3\2"+
		"\2\2IJ\3\2\2\2J\t\3\2\2\2KP\7\7\2\2LM\5\36\20\2MN\7\24\2\2NO\5 \21\2O"+
		"Q\3\2\2\2PL\3\2\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2S\13\3\2\2\2TY\7\b\2"+
		"\2UV\5\36\20\2VW\7\24\2\2WX\5 \21\2XZ\3\2\2\2YU\3\2\2\2Z[\3\2\2\2[Y\3"+
		"\2\2\2[\\\3\2\2\2\\\r\3\2\2\2]b\7\t\2\2^_\5\36\20\2_`\7\24\2\2`a\5 \21"+
		"\2ac\3\2\2\2b^\3\2\2\2cd\3\2\2\2db\3\2\2\2de\3\2\2\2e\17\3\2\2\2fk\7\n"+
		"\2\2gh\5\36\20\2hi\7\24\2\2ij\5 \21\2jl\3\2\2\2kg\3\2\2\2lm\3\2\2\2mk"+
		"\3\2\2\2mn\3\2\2\2n\21\3\2\2\2ot\7\13\2\2pq\5\36\20\2qr\7\24\2\2rs\5 "+
		"\21\2su\3\2\2\2tp\3\2\2\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\23\3\2\2\2x}"+
		"\7\f\2\2yz\5\36\20\2z{\7\24\2\2{|\5 \21\2|~\3\2\2\2}y\3\2\2\2~\177\3\2"+
		"\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\25\3\2\2\2\u0081\u0086\7\r\2"+
		"\2\u0082\u0083\5\36\20\2\u0083\u0084\7\24\2\2\u0084\u0085\5 \21\2\u0085"+
		"\u0087\3\2\2\2\u0086\u0082\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0086\3\2"+
		"\2\2\u0088\u0089\3\2\2\2\u0089\27\3\2\2\2\u008a\u008f\7\23\2\2\u008b\u008c"+
		"\5\36\20\2\u008c\u008d\7\24\2\2\u008d\u008e\5 \21\2\u008e\u0090\3\2\2"+
		"\2\u008f\u008b\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092"+
		"\3\2\2\2\u0092\31\3\2\2\2\u0093\u0094\7\16\2\2\u0094\u0095\7\21\2\2\u0095"+
		"\u0096\7\24\2\2\u0096\u0097\7\27\2\2\u0097\u0098\7\22\2\2\u0098\u0099"+
		"\7\24\2\2\u0099\u009a\7\27\2\2\u009a\33\3\2\2\2\u009b\u009c\t\2\2\2\u009c"+
		"\u009d\7\26\2\2\u009d\u009e\7\24\2\2\u009e\u009f\7\27\2\2\u009f\u00a0"+
		"\7\22\2\2\u00a0\u00a1\7\24\2\2\u00a1\u00a2\7\27\2\2\u00a2\35\3\2\2\2\u00a3"+
		"\u00a4\7\27\2\2\u00a4\37\3\2\2\2\u00a5\u00a6\t\3\2\2\u00a6!\3\2\2\2\17"+
		")\66?DIR[dmv\177\u0088\u0091";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}
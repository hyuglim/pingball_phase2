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
		ABSORBER=11, SPAWNER=12, FIRE=13, KEYUP=14, KEYDOWN=15, TRIGGER=16, ACTION=17, 
		PORTAL=18, EQUAL=19, FLOAT=20, KEY=21, NAME=22, WHITESPACE=23, COMMENT=24;
	public static final String[] tokenNames = {
		"<INVALID>", "'board'", "'gravity'", "'friction1'", "'friction2'", "'ball'", 
		"'squareBumper'", "'circleBumper'", "'triangleBumper'", "'leftFlipper'", 
		"'rightFlipper'", "'absorber'", "'spawner'", "'fire'", "'keyup'", "'keydown'", 
		"'trigger'", "'action'", "'portal'", "'='", "FLOAT", "'key'", "NAME", 
		"WHITESPACE", "COMMENT"
	};
	public static final int
		RULE_file = 0, RULE_declaration = 1, RULE_line = 2, RULE_board = 3, RULE_ball = 4, 
		RULE_square = 5, RULE_circle = 6, RULE_triangle = 7, RULE_leftFlipper = 8, 
		RULE_rightFlipper = 9, RULE_spawner = 10, RULE_absorber = 11, RULE_portal = 12, 
		RULE_fire = 13, RULE_keytrigger = 14, RULE_attribute = 15, RULE_value = 16;
	public static final String[] ruleNames = {
		"file", "declaration", "line", "board", "ball", "square", "circle", "triangle", 
		"leftFlipper", "rightFlipper", "spawner", "absorber", "portal", "fire", 
		"keytrigger", "attribute", "value"
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
			setState(34); declaration();
			setState(35); match(EOF);
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
			setState(37); board();
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BALL) | (1L << SQUARE_BUMPER) | (1L << CIRCLE_BUMPER) | (1L << TRIANGULAR_BUMPER) | (1L << LEFT_FLIPPER) | (1L << RIGHT_FLIPPER) | (1L << ABSORBER) | (1L << SPAWNER) | (1L << FIRE) | (1L << KEYUP) | (1L << KEYDOWN) | (1L << PORTAL))) != 0)) {
				{
				{
				setState(38); line();
				}
				}
				setState(43);
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
		public SpawnerContext spawner() {
			return getRuleContext(SpawnerContext.class,0);
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
			setState(55);
			switch (_input.LA(1)) {
			case BALL:
				enterOuterAlt(_localctx, 1);
				{
				setState(44); ball();
				}
				break;
			case SQUARE_BUMPER:
				enterOuterAlt(_localctx, 2);
				{
				setState(45); square();
				}
				break;
			case CIRCLE_BUMPER:
				enterOuterAlt(_localctx, 3);
				{
				setState(46); circle();
				}
				break;
			case TRIANGULAR_BUMPER:
				enterOuterAlt(_localctx, 4);
				{
				setState(47); triangle();
				}
				break;
			case LEFT_FLIPPER:
				enterOuterAlt(_localctx, 5);
				{
				setState(48); leftFlipper();
				}
				break;
			case RIGHT_FLIPPER:
				enterOuterAlt(_localctx, 6);
				{
				setState(49); rightFlipper();
				}
				break;
			case ABSORBER:
				enterOuterAlt(_localctx, 7);
				{
				setState(50); absorber();
				}
				break;
			case PORTAL:
				enterOuterAlt(_localctx, 8);
				{
				setState(51); portal();
				}
				break;
			case FIRE:
				enterOuterAlt(_localctx, 9);
				{
				setState(52); fire();
				}
				break;
			case KEYUP:
			case KEYDOWN:
				enterOuterAlt(_localctx, 10);
				{
				setState(53); keytrigger();
				}
				break;
			case SPAWNER:
				enterOuterAlt(_localctx, 11);
				{
				setState(54); spawner();
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
			setState(57); match(BOARD);
			{
			setState(58); attribute();
			setState(59); match(EQUAL);
			setState(60); value();
			}
			setState(64);
			_la = _input.LA(1);
			if (_la==GRAVITY) {
				{
				setState(61); match(GRAVITY);
				setState(62); match(EQUAL);
				setState(63); value();
				}
			}

			setState(69);
			_la = _input.LA(1);
			if (_la==FRICTION1) {
				{
				setState(66); match(FRICTION1);
				setState(67); match(EQUAL);
				setState(68); value();
				}
			}

			setState(74);
			_la = _input.LA(1);
			if (_la==FRICTION2) {
				{
				setState(71); match(FRICTION2);
				setState(72); match(EQUAL);
				setState(73); value();
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
			setState(76); match(BALL);
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
			setState(85); match(SQUARE_BUMPER);
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
			setState(94); match(CIRCLE_BUMPER);
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
			setState(103); match(TRIANGULAR_BUMPER);
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
			setState(112); match(LEFT_FLIPPER);
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
			setState(121); match(RIGHT_FLIPPER);
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

	public static class SpawnerContext extends ParserRuleContext {
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode SPAWNER() { return getToken(BoardFileParser.SPAWNER, 0); }
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
		public SpawnerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spawner; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).enterSpawner(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardFileListener ) ((BoardFileListener)listener).exitSpawner(this);
		}
	}

	public final SpawnerContext spawner() throws RecognitionException {
		SpawnerContext _localctx = new SpawnerContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_spawner);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); match(SPAWNER);
			setState(135); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(131); attribute();
				setState(132); match(EQUAL);
				setState(133); value();
				}
				}
				setState(137); 
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
		enterRule(_localctx, 22, RULE_absorber);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139); match(ABSORBER);
			setState(144); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(140); attribute();
				setState(141); match(EQUAL);
				setState(142); value();
				}
				}
				setState(146); 
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
		enterRule(_localctx, 24, RULE_portal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148); match(PORTAL);
			setState(153); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(149); attribute();
				setState(150); match(EQUAL);
				setState(151); value();
				}
				}
				setState(155); 
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
		enterRule(_localctx, 26, RULE_fire);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); match(FIRE);
			setState(158); match(TRIGGER);
			setState(159); match(EQUAL);
			setState(160); match(NAME);
			setState(161); match(ACTION);
			setState(162); match(EQUAL);
			setState(163); match(NAME);
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
		enterRule(_localctx, 28, RULE_keytrigger);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			_la = _input.LA(1);
			if ( !(_la==KEYUP || _la==KEYDOWN) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(166); match(KEY);
			setState(167); match(EQUAL);
			setState(168); match(NAME);
			setState(169); match(ACTION);
			setState(170); match(EQUAL);
			setState(171); match(NAME);
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
		enterRule(_localctx, 30, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173); match(NAME);
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
		enterRule(_localctx, 32, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
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
		"\2\3\32\u00b4\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t"+
		"\20\4\21\t\21\4\22\t\22\3\2\3\2\3\2\3\3\3\3\7\3*\n\3\f\3\16\3-\13\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4:\n\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\5\5C\n\5\3\5\3\5\3\5\5\5H\n\5\3\5\3\5\3\5\5\5M\n\5\3\6\3\6"+
		"\3\6\3\6\3\6\6\6T\n\6\r\6\16\6U\3\7\3\7\3\7\3\7\3\7\6\7]\n\7\r\7\16\7"+
		"^\3\b\3\b\3\b\3\b\3\b\6\bf\n\b\r\b\16\bg\3\t\3\t\3\t\3\t\3\t\6\to\n\t"+
		"\r\t\16\tp\3\n\3\n\3\n\3\n\3\n\6\nx\n\n\r\n\16\ny\3\13\3\13\3\13\3\13"+
		"\3\13\6\13\u0081\n\13\r\13\16\13\u0082\3\f\3\f\3\f\3\f\3\f\6\f\u008a\n"+
		"\f\r\f\16\f\u008b\3\r\3\r\3\r\3\r\3\r\6\r\u0093\n\r\r\r\16\r\u0094\3\16"+
		"\3\16\3\16\3\16\3\16\6\16\u009c\n\16\r\16\16\16\u009d\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3"+
		"\21\3\22\3\22\3\22\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\4"+
		"\3\20\21\4\26\26\30\30\u00b9\2$\3\2\2\2\4\'\3\2\2\2\69\3\2\2\2\b;\3\2"+
		"\2\2\nN\3\2\2\2\fW\3\2\2\2\16`\3\2\2\2\20i\3\2\2\2\22r\3\2\2\2\24{\3\2"+
		"\2\2\26\u0084\3\2\2\2\30\u008d\3\2\2\2\32\u0096\3\2\2\2\34\u009f\3\2\2"+
		"\2\36\u00a7\3\2\2\2 \u00af\3\2\2\2\"\u00b1\3\2\2\2$%\5\4\3\2%&\7\1\2\2"+
		"&\3\3\2\2\2\'+\5\b\5\2(*\5\6\4\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2"+
		"\2,\5\3\2\2\2-+\3\2\2\2.:\5\n\6\2/:\5\f\7\2\60:\5\16\b\2\61:\5\20\t\2"+
		"\62:\5\22\n\2\63:\5\24\13\2\64:\5\30\r\2\65:\5\32\16\2\66:\5\34\17\2\67"+
		":\5\36\20\28:\5\26\f\29.\3\2\2\29/\3\2\2\29\60\3\2\2\29\61\3\2\2\29\62"+
		"\3\2\2\29\63\3\2\2\29\64\3\2\2\29\65\3\2\2\29\66\3\2\2\29\67\3\2\2\29"+
		"8\3\2\2\2:\7\3\2\2\2;<\7\3\2\2<B\5 \21\2=>\7\25\2\2>P\5\"\22\2?@\7\4\2"+
		"\2@A\7\25\2\2AC\5\"\22\2B?\3\2\2\2BC\3\2\2\2CG\3\2\2\2DE\7\5\2\2EF\7\25"+
		"\2\2FH\5\"\22\2GD\3\2\2\2GH\3\2\2\2HL\3\2\2\2IJ\7\6\2\2JK\7\25\2\2KM\5"+
		"\"\22\2LI\3\2\2\2LM\3\2\2\2M\t\3\2\2\2NS\7\7\2\2OP\5 \21\2PQ\7\25\2\2"+
		"QR\5\"\22\2RT\3\2\2\2SO\3\2\2\2TU\3\2\2\2US\3\2\2\2UV\3\2\2\2V\13\3\2"+
		"\2\2W\\\7\b\2\2XY\5 \21\2YZ\7\25\2\2Z[\5\"\22\2[]\3\2\2\2\\X\3\2\2\2]"+
		"^\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_\r\3\2\2\2`e\7\t\2\2ab\5 \21\2bc\7\25\2"+
		"\2cd\5\"\22\2df\3\2\2\2ea\3\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\17\3"+
		"\2\2\2in\7\n\2\2jk\5 \21\2kl\7\25\2\2lm\5\"\22\2mo\3\2\2\2nj\3\2\2\2o"+
		"p\3\2\2\2pn\3\2\2\2pq\3\2\2\2q\21\3\2\2\2rw\7\13\2\2st\5 \21\2tu\7\25"+
		"\2\2uv\5\"\22\2vx\3\2\2\2ws\3\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2z\23"+
		"\3\2\2\2{\u0080\7\f\2\2|}\5 \21\2}~\7\25\2\2~\177\5\"\22\2\177\u0081\3"+
		"\2\2\2\u0080|\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\25\3\2\2\2\u0084\u0089\7\16\2\2\u0085\u0086\5 \21\2\u0086"+
		"\u0087\7\25\2\2\u0087\u0088\5\"\22\2\u0088\u008a\3\2\2\2\u0089\u0085\3"+
		"\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\27\3\2\2\2\u008d\u0092\7\r\2\2\u008e\u008f\5 \21\2\u008f\u0090\7\25\2"+
		"\2\u0090\u0091\5\"\22\2\u0091\u0093\3\2\2\2\u0092\u008e\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\31\3\2\2"+
		"\2\u0096\u009b\7\24\2\2\u0097\u0098\5 \21\2\u0098\u0099\7\25\2\2\u0099"+
		"\u009a\5\"\22\2\u009a\u009c\3\2\2\2\u009b\u0097\3\2\2\2\u009c\u009d\3"+
		"\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\33\3\2\2\2\u009f"+
		"\u00a0\7\17\2\2\u00a0\u00a1\7\22\2\2\u00a1\u00a2\7\25\2\2\u00a2\u00a3"+
		"\7\30\2\2\u00a3\u00a4\7\23\2\2\u00a4\u00a5\7\25\2\2\u00a5\u00a6\7\30\2"+
		"\2\u00a6\35\3\2\2\2\u00a7\u00a8\t\2\2\2\u00a8\u00a9\7\27\2\2\u00a9\u00aa"+
		"\7\25\2\2\u00aa\u00ab\7\30\2\2\u00ab\u00ac\7\23\2\2\u00ac\u00ad\7\25\2"+
		"\2\u00ad\u00ae\7\30\2\2\u00ae\37\3\2\2\2\u00af\u00b0\7\30\2\2\u00b0!\3"+
		"\2\2\2\u00b1\u00b2\t\3\2\2\u00b2#\3\2\2\2\20+9BGLU^gpy\u0082\u008b\u0094"+
		"\u009d";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}
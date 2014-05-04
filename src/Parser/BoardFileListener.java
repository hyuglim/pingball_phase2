// Generated from BoardFile.g4 by ANTLR 4.0

package Parser;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface BoardFileListener extends ParseTreeListener {
	void enterAbsorber(BoardFileParser.AbsorberContext ctx);
	void exitAbsorber(BoardFileParser.AbsorberContext ctx);

	void enterBall(BoardFileParser.BallContext ctx);
	void exitBall(BoardFileParser.BallContext ctx);

	void enterDeclaration(BoardFileParser.DeclarationContext ctx);
	void exitDeclaration(BoardFileParser.DeclarationContext ctx);

	void enterPortal(BoardFileParser.PortalContext ctx);
	void exitPortal(BoardFileParser.PortalContext ctx);

	void enterAttribute(BoardFileParser.AttributeContext ctx);
	void exitAttribute(BoardFileParser.AttributeContext ctx);

	void enterLine(BoardFileParser.LineContext ctx);
	void exitLine(BoardFileParser.LineContext ctx);

	void enterCircle(BoardFileParser.CircleContext ctx);
	void exitCircle(BoardFileParser.CircleContext ctx);

	void enterBoard(BoardFileParser.BoardContext ctx);
	void exitBoard(BoardFileParser.BoardContext ctx);

	void enterFire(BoardFileParser.FireContext ctx);
	void exitFire(BoardFileParser.FireContext ctx);

	void enterKeytrigger(BoardFileParser.KeytriggerContext ctx);
	void exitKeytrigger(BoardFileParser.KeytriggerContext ctx);

	void enterTriangle(BoardFileParser.TriangleContext ctx);
	void exitTriangle(BoardFileParser.TriangleContext ctx);

	void enterFile(BoardFileParser.FileContext ctx);
	void exitFile(BoardFileParser.FileContext ctx);

	void enterValue(BoardFileParser.ValueContext ctx);
	void exitValue(BoardFileParser.ValueContext ctx);

	void enterRightFlipper(BoardFileParser.RightFlipperContext ctx);
	void exitRightFlipper(BoardFileParser.RightFlipperContext ctx);

	void enterSquare(BoardFileParser.SquareContext ctx);
	void exitSquare(BoardFileParser.SquareContext ctx);

	void enterLeftFlipper(BoardFileParser.LeftFlipperContext ctx);
	void exitLeftFlipper(BoardFileParser.LeftFlipperContext ctx);
}
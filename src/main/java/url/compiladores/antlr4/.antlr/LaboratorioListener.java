// Generated from c:/Users/raul3/Desktop/Documentos de Raúl/LANDIVAR/Quinto Semestre/COMPILADORES/Proyecto/laboratorio-tres/src/main/java/url/compiladores/antlr4/Laboratorio.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LaboratorioParser}.
 */
public interface LaboratorioListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LaboratorioParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(LaboratorioParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaboratorioParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(LaboratorioParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaboratorioParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(LaboratorioParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaboratorioParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(LaboratorioParser.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LaboratorioParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(LaboratorioParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LaboratorioParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(LaboratorioParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValInt}
	 * labeled alternative in {@link LaboratorioParser#valor}.
	 * @param ctx the parse tree
	 */
	void enterValInt(LaboratorioParser.ValIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValInt}
	 * labeled alternative in {@link LaboratorioParser#valor}.
	 * @param ctx the parse tree
	 */
	void exitValInt(LaboratorioParser.ValIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValBool}
	 * labeled alternative in {@link LaboratorioParser#valor}.
	 * @param ctx the parse tree
	 */
	void enterValBool(LaboratorioParser.ValBoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValBool}
	 * labeled alternative in {@link LaboratorioParser#valor}.
	 * @param ctx the parse tree
	 */
	void exitValBool(LaboratorioParser.ValBoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValString}
	 * labeled alternative in {@link LaboratorioParser#valor}.
	 * @param ctx the parse tree
	 */
	void enterValString(LaboratorioParser.ValStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValString}
	 * labeled alternative in {@link LaboratorioParser#valor}.
	 * @param ctx the parse tree
	 */
	void exitValString(LaboratorioParser.ValStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link LaboratorioParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAnd(LaboratorioParser.ExprAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link LaboratorioParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAnd(LaboratorioParser.ExprAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParens}
	 * labeled alternative in {@link LaboratorioParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprParens(LaboratorioParser.ExprParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParens}
	 * labeled alternative in {@link LaboratorioParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprParens(LaboratorioParser.ExprParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNot}
	 * labeled alternative in {@link LaboratorioParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprNot(LaboratorioParser.ExprNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNot}
	 * labeled alternative in {@link LaboratorioParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprNot(LaboratorioParser.ExprNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link LaboratorioParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprId(LaboratorioParser.ExprIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link LaboratorioParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprId(LaboratorioParser.ExprIdContext ctx);
}
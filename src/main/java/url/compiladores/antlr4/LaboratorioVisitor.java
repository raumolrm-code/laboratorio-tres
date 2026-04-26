// Generated from Laboratorio.g4 by ANTLR 4.13.2
package url.compiladores.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LaboratorioParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LaboratorioVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LaboratorioParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(LaboratorioParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaboratorioParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruccion(LaboratorioParser.InstruccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LaboratorioParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(LaboratorioParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValInt}
	 * labeled alternative in {@link LaboratorioParser#valor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValInt(LaboratorioParser.ValIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValBool}
	 * labeled alternative in {@link LaboratorioParser#valor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValBool(LaboratorioParser.ValBoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ValString}
	 * labeled alternative in {@link LaboratorioParser#valor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValString(LaboratorioParser.ValStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAnd}
	 * labeled alternative in {@link LaboratorioParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAnd(LaboratorioParser.ExprAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParens}
	 * labeled alternative in {@link LaboratorioParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParens(LaboratorioParser.ExprParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNot}
	 * labeled alternative in {@link LaboratorioParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNot(LaboratorioParser.ExprNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link LaboratorioParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprId(LaboratorioParser.ExprIdContext ctx);
}
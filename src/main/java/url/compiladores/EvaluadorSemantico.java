package url.compiladores;

import url.compiladores.antlr4.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Motor central de análisis semántico.
 * Evalúa las expresiones dinámicamente conservando los tipos en operaciones anidadas
 * y garantiza la construcción de la Tabla de Símbolos incondicionalmente.
 */
public class EvaluadorSemantico extends LaboratorioBaseVisitor<Object> {

    static class Variable {
        String nombre;
        String tipo;
        Object valor;

        Variable(String nombre, String tipo, Object valor) {
            this.nombre = nombre;
            this.tipo = tipo;
            this.valor = valor;
        }
    }

    private final List<Variable> tablaSimbolos = new ArrayList<>();
    public final List<String> erroresSemanticos = new ArrayList<>();
    
    private Object resultadoUltimaExpresion = null;

    private Variable buscarVariable(String nombre) {
        for (Variable var : tablaSimbolos) {
            if (var.nombre.equals(nombre)) return var;
        }
        return null;
    }

    @Override
    public Object visitProg(LaboratorioParser.ProgContext ctx) {
        for (LaboratorioParser.InstruccionContext instruccion : ctx.instruccion()) {
            Object res = visit(instruccion);
            if (instruccion.expresion() != null) {
                resultadoUltimaExpresion = res;
            }
        }
        return resultadoUltimaExpresion;
    }

    @Override
    public Object visitAsignacion(LaboratorioParser.AsignacionContext ctx) {
        String id = ctx.ID().getText();
        Object valor = visit(ctx.valor());
        String tipo = determinarTipo(valor);

        Variable existente = buscarVariable(id);
        if (existente != null) {
            existente.valor = valor;
            existente.tipo = tipo;
        } else {
            tablaSimbolos.add(new Variable(id, tipo, valor));
        }
        return null;
    }

    @Override
    public Object visitValInt(LaboratorioParser.ValIntContext ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }

    @Override
    public Object visitValBool(LaboratorioParser.ValBoolContext ctx) {
        return Boolean.parseBoolean(ctx.BOOL().getText());
    }

    @Override
    public Object visitValString(LaboratorioParser.ValStringContext ctx) {
        return ctx.STRING().getText().replace("\"", "");
    }

    @Override
    public Object visitExprAnd(LaboratorioParser.ExprAndContext ctx) {
        Object izq = visit(ctx.expresion(0));
        Object der = visit(ctx.expresion(1));

        if (izq == null || der == null) return null;

        String tIzq = determinarTipo(izq);
        String tDer = determinarTipo(der);

        if (!tIzq.equals(tDer)) {
            erroresSemanticos.add("Error semántico: No se pueden combinar variables de distintos tipos en una misma expresión.");
            return null;
        }

        if (tIzq.equals("string")) {
            erroresSemanticos.add("Error semántico: Las variables de tipo string no están permitidas en expresiones booleanas.");
            return null;
        }

        boolean bIzq = convertirABooleano(izq);
        boolean bDer = convertirABooleano(der);
        boolean resultadoLogico = bIzq && bDer;

        // Preservamos el dominio entero para validaciones anidadas (Ej. Entrada 2)
        if (tIzq.equals("int")) {
            return resultadoLogico ? 1 : 0;
        } else {
            return resultadoLogico;
        }
    }

    @Override
    public Object visitExprNot(LaboratorioParser.ExprNotContext ctx) {
        Object valor = visit(ctx.expresion());
        if (valor == null) return null;

        String tipo = determinarTipo(valor);

        if (tipo.equals("string")) {
            erroresSemanticos.add("Error semántico: Las variables de tipo string no están permitidas en expresiones booleanas.");
            return null;
        }

        if (tipo.equals("int")) {
            return (Integer) valor == 0 ? 1 : 0;
        } else {
            return !((Boolean) valor);
        }
    }

    @Override
    public Object visitExprId(LaboratorioParser.ExprIdContext ctx) {
        String id = ctx.ID().getText();
        Variable var = buscarVariable(id);
        if (var == null) {
            erroresSemanticos.add("Error semántico: Variable '" + id + "' no definida.");
            return null;
        }
        return var.valor;
    }

    @Override
    public Object visitExprParens(LaboratorioParser.ExprParensContext ctx) {
        return visit(ctx.expresion());
    }

    private String determinarTipo(Object obj) {
        if (obj instanceof Integer) return "int";
        if (obj instanceof Boolean) return "boolean";
        return "string";
    }

    public boolean convertirABooleano(Object obj) {
        if (obj == null) return false; // Fallback seguro
        if (obj instanceof Boolean) return (Boolean) obj;
        if (obj instanceof Integer) return ((Integer) obj) != 0;
        return false;
    }

    public Object obtenerResultadoCrudo() {
        return resultadoUltimaExpresion;
    }

    /**
     * IMPRESIÓN OBLIGATORIA DE LA TABLA.
     */
    public void imprimirTabla() {
        System.out.println("VARIABLE | TIPO | VALOR\n");
        if (tablaSimbolos.isEmpty()) {
            System.out.println("(No hay variables declaradas)");
        } else {
            for (Variable var : tablaSimbolos) {
                System.out.println(var.nombre + " | " + var.tipo + " | " + var.valor + "\n");
            }
        }
    }
}
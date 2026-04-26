package url.compiladores;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import url.compiladores.antlr4.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal que administra la lectura de archivos de prueba y
 * orquesta las fases del compilador, capturando errores y mostrando resultados
 * según el formato establecido por el laboratorio.
 */
public class App {

    /**
     * Interceptor diseñado para evitar que los errores léxicos o sintácticos
     * rompan la ejecución, almacenándolos para su posterior impresión.
     */
    static class GestorErrores extends BaseErrorListener {
        List<String> lista = new ArrayList<>();
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, 
                                int line, int charPositionInLine, String msg, RecognitionException e) {
            lista.add("Línea " + line + ":" + charPositionInLine + " - " + msg);
        }
    }

    public static void main(String[] args) {
        
        String[] rutasPrueba = {
            "src/test/entrada1.txt",
            "src/test/entrada2.txt",
            "src/test/entrada3.txt",
            "src/test/entrada4.txt",
            "src/test/error_lexico.txt",
            "src/test/error_sintactico.txt"
        };

        for (String ruta : rutasPrueba) {
            System.out.println("Procesando: " + ruta);
            System.out.println("--------------------------------------------------");
            
            try {
                GestorErrores gestor = new GestorErrores();
                
                LaboratorioLexer lexer = new LaboratorioLexer(CharStreams.fromFileName(ruta));
                lexer.removeErrorListeners();
                lexer.addErrorListener(gestor);
                
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                LaboratorioParser parser = new LaboratorioParser(tokens);
                parser.removeErrorListeners();
                parser.addErrorListener(gestor);

                ParseTree tree = parser.prog();

                EvaluadorSemantico evaluador = new EvaluadorSemantico();
                Object resultado = evaluador.visit(tree);

                // Lógica de impresión para coincidir con las expectativas del documento
                if (!gestor.lista.isEmpty()) {
                    System.out.println("ERRORES LÉXICOS/SINTÁCTICOS:");
                    for (String err : gestor.lista) {
                        System.out.println(err);
                    }
                } else if (!evaluador.erroresSemanticos.isEmpty()) {
                    System.out.println("Tabla de símbolos generada correctamente\n");
                    for (String err : evaluador.erroresSemanticos) {
                        System.out.println(err);
                    }
                } else {
                    evaluador.imprimirTabla();
                    if (resultado != null) {
                        System.out.println("Resultado de la expresión: " + resultado);
                    }
                }
                
                System.out.println("\n");
                
            } catch (IOException e) {
                System.err.println("No se pudo leer el archivo. Verifique que exista en la ruta: " + e.getMessage() + "\n");
            }
        }
    }
}
package url.compiladores;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import url.compiladores.antlr4.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Punto de entrada interactivo para el compilador.
 * Gestiona la interacción con el usuario, la integridad de los archivos de prueba
 * y la coordinación de las fases de análisis léxico, sintáctico y semántico.
 */
public class App {

    /**
     * Almacena errores detectados por ANTLR durante el procesamiento inicial.
     */
    static class GestorErrores extends BaseErrorListener {
        List<String> errores = new ArrayList<>();
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, 
                                int line, int charPositionInLine, String msg, RecognitionException e) {
            errores.add("Error en Línea " + line + ":" + charPositionInLine + " - " + msg);
        }
    }

    /**
     * Asegura la creación de los archivos de prueba si no se encuentran en el sistema.
     */
    private static void asegurarEntradasDePrueba() {
        File folder = new File("src/test");
        if (!folder.exists()) folder.mkdirs();

        String[][] datos = {
            {"src/test/entrada1.txt", "x = true\ny = false\nx and y and not ( x )"},
            {"src/test/entrada2.txt", "x = 1\ny = 0\nz = 20\nx and not ( y ) and z"},
            {"src/test/entrada3.txt", "x = 1\ny = true\nx and y"},
            {"src/test/entrada4.txt", "x = \"Hola\"\nx and x"},
            {"src/test/error_lexico.txt", "a = 10\nb = @true\na and b"},
            {"src/test/error_sintactico.txt", "x 100\ny = false\nx and y"}
        };

        for (String[] d : datos) {
            File f = new File(d[0]);
            if (!f.exists()) {
                try (FileWriter fw = new FileWriter(f)) {
                    fw.write(d[1]);
                } catch (IOException ignored) {}
            }
        }
    }

    public static void main(String[] args) {
        asegurarEntradasDePrueba();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("COMPILADOR INTERACTIVO - ANTLR4");
        System.out.print("¿Cuál entrada le gustaría probar? (Ingrese el path del archivo): ");
        String path = sc.nextLine();

        try {
            GestorErrores gestor = new GestorErrores();
            LaboratorioLexer lexer = new LaboratorioLexer(CharStreams.fromFileName(path));
            lexer.removeErrorListeners();
            lexer.addErrorListener(gestor);

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            LaboratorioParser parser = new LaboratorioParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(gestor);

            ParseTree tree = parser.prog();
            EvaluadorSemantico evaluador = new EvaluadorSemantico();
            Object resultado = evaluador.visit(tree);

            // Reporte de Tabla de Símbolos
            if (gestor.errores.isEmpty()) {
                System.out.println("\n[TABLA DE SÍMBOLOS]");
                evaluador.imprimirTabla();
            } else {
                System.out.println("\nLa tabla de símbolos no pudo generarse debido a errores estructurales.");
            }

            // Reporte de Errores Combinados
            if (!gestor.errores.isEmpty() || !evaluador.erroresSemanticos.isEmpty()) {
                System.out.println("\n[REPORTE DE ERRORES DETECTADOS]");
                if (!gestor.errores.isEmpty()) {
                    System.out.println(">> Errores Léxicos / Sintácticos:");
                    gestor.errores.forEach(e -> System.out.println("   " + e));
                }
                if (!evaluador.erroresSemanticos.isEmpty()) {
                    System.out.println(">> Errores Semánticos:");
                    evaluador.erroresSemanticos.forEach(e -> System.out.println("   " + e));
                }
            } else if (resultado != null) {
                // Resultado de Evaluación
                System.out.println("\nResultado de la expresión: " + resultado);
            }

        } catch (IOException e) {
            System.err.println("Error: No se pudo abrir el archivo especificado. Verifique la ruta.");
        } finally {
            sc.close();
        }
    }
}
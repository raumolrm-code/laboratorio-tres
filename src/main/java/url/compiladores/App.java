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
 * Entorno interactivo del compilador.
 * Despliega incondicionalmente la Tabla de Símbolos, los Errores (si los hay) 
 * y el Resultado Booleano por CADA archivo procesado.
 */
public class App {

    static class GestorErrores extends BaseErrorListener {
        List<String> errores = new ArrayList<>();
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, 
                                int line, int charPositionInLine, String msg, RecognitionException e) {
            errores.add("Error léxico/sintáctico en Línea " + line + ":" + charPositionInLine + " - " + msg);
        }
    }

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
            try (FileWriter fw = new FileWriter(f)) {
                fw.write(d[1]);
            } catch (IOException ignored) {}
        }
    }

    public static void main(String[] args) {
        asegurarEntradasDePrueba();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=================================================");
        System.out.println("     ANALIZADOR SEMÁNTICO - URL      ");
        System.out.println("=================================================");
        
        while (true) {
            System.out.println("\nEscriba 'salir' para terminar la ejecución.");
            System.out.print("¿Cuál entrada le gustaría probar? (Ingrese el path del archivo .txt): ");
            String path = sc.nextLine().trim();

            if (path.equalsIgnoreCase("salir")) {
                System.out.println("Cerrando compilador...");
                break;
            }

            File archivoPrueba = new File(path);
            if (!archivoPrueba.exists() || archivoPrueba.isDirectory()) {
                System.err.println("\nError: No se pudo localizar el archivo en la ruta especificada.");
                continue;
            }

            try {
                System.out.println("\n-------------------------------------------------");
                System.out.println("Resultado esperado:\n");

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
                evaluador.visit(tree);

                // BLOQUE 1: IMPRESIÓN OBLIGATORIA DE LA TABLA (Sin importar los errores)
                System.out.println("Tabla de símbolos generada correctamente\n");
                evaluador.imprimirTabla();

                // BLOQUE 2: IMPRESIÓN DE ERRORES (Si existen)
                if (!gestor.errores.isEmpty()) {
                    gestor.errores.forEach(System.out::println);
                }
                if (!evaluador.erroresSemanticos.isEmpty()) {
                    evaluador.erroresSemanticos.forEach(System.out::println);
                }
                
                // Salto de línea estético si hubo errores
                if (!gestor.errores.isEmpty() || !evaluador.erroresSemanticos.isEmpty()) {
                    System.out.println(); 
                }

                // BLOQUE 3: IMPRESIÓN OBLIGATORIA DEL RESULTADO BOOLEANO
                // Se obtiene lo que sea que quedó, y el método 'convertirABooleano' 
                // se asegura de devolver 'true' o 'false' incondicionalmente.
                Object resCrudo = evaluador.obtenerResultadoCrudo();
                boolean resultadoFinal = evaluador.convertirABooleano(resCrudo);
                
                System.out.println("Resultado de la expresión: " + resultadoFinal);
                System.out.println("-------------------------------------------------");

            } catch (Exception e) {
                System.err.println("Falla del sistema: " + e.getMessage());
            }
        }
        sc.close();
    }
}
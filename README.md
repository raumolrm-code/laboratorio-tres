📘 Manual de Usuario Extendido: Analizador Semántico (URL)

Este documento proporciona una guía exhaustiva y detallada para la configuración, ejecución y aprovechamiento máximo del Analizador Semántico. Esta herramienta, desarrollada en Java utilizando la potente librería de generación de parsers ANTLR4, ha sido diseñada para integrarse perfectamente en el ecosistema de Visual Studio Code.

1. Requisitos Detallados del Sistema

Para garantizar que el compilador funcione sin interrupciones y con total precisión, su entorno debe cumplir con las siguientes especificaciones técnicas:

Java Development Kit (JDK): Se requiere la versión 11 o superior (se recomienda JDK 17 LTS por estabilidad). Es fundamental que la variable de entorno JAVA_HOME esté correctamente configurada en su sistema operativo.

Visual Studio Code (VS Code): Asegúrese de utilizar la versión estable más reciente para evitar conflictos con las extensiones de lenguaje.

Extensiones de VS Code (Ecosistema Java):

Extension Pack for Java (Microsoft): Este paquete es vital, ya que incluye soporte para Language Support for Java™, Debugger for Java, Maven y herramientas de testing. Sin esto, VS Code no reconocerá el proyecto como una aplicación Java válida.

Debugger for Java: Permite la ejecución paso a paso del código, algo esencial si desea inspeccionar cómo el EvaluadorSemantico recorre el árbol de sintaxis.

2. Flujo de Configuración y Ejecución en VS Code

Siga este procedimiento paso a paso para poner en marcha el analizador en su entorno local:

Apertura del Proyecto:

Inicie Visual Studio Code.

Diríjase al menú superior: File > Open Folder....

Seleccione la carpeta raíz que contiene el archivo pom.xml (si usa Maven) o la carpeta que contiene el directorio src. Es crucial abrir la carpeta raíz para que las rutas relativas de los archivos de prueba funcionen correctamente.

Preparación del Entorno:

Espere a que el "Language Support for Java" termine de importar los proyectos (notará una barra de progreso en la esquina inferior derecha).

Localización del Punto de Entrada:

En el explorador de archivos, siga la ruta: src/main/java/url/compiladores/App.java. Este es el motor principal de la aplicación interactiva.

Métodos de Ejecución:

Botón Run: Al abrir App.java, verá un pequeño texto sobre el método main que dice Run. Haga clic ahí.

Tecla F5: Presione F5 para iniciar en modo depuración.

Terminal: La Terminal Integrada se abrirá automáticamente en la parte inferior, mostrando el banner de bienvenida del Analizador Semántico URL.

3. Guía de Uso del Programa Interactivo

Una vez que la aplicación esté activa en la terminal, el sistema entrará en un bucle de espera de comandos.

A. Procesamiento de Archivos de Entrada

El programa le solicitará la ruta de un archivo de texto con extensión .txt. El analizador lee el contenido, lo tokeniza y evalúa su lógica.

Rutas Relativas: Para mayor comodidad, utilice rutas relativas. Por ejemplo: src/test/java/url/compiladores/entrada1.txt.

Generación Automática: Si es la primera vez que ejecuta el programa, el sistema detectará la ausencia de pruebas y creará automáticamente una serie de archivos de ejemplo en src/test/ para que pueda comenzar a probar de inmediato.

B. Reglas de Escritura y Sintaxis (Gramática del Lenguaje)

El lenguaje soporta una estructura sencilla pero estricta para definir lógica:

Asignaciones: Permiten guardar valores en la memoria del programa.

edad = 25 (Entero)

esValido = true (Booleano)

nombre = "Usuario" (Cadena de texto)

Expresiones Lógicas Complejas: Puede combinar variables y valores usando operadores.

Uso de and: x and y devuelve verdadero solo si ambos son verdaderos.

Uso de not(): not(x) invierte el valor lógico.

Precedencia: El uso de paréntesis ( ) es fundamental para definir qué operación debe ejecutarse primero en expresiones largas como not(a and b) and (c or d).

C. Finalización de la Sesión

Para cerrar el programa de manera limpia y liberar los recursos del sistema, simplemente escriba la palabra salir cuando se le solicite una ruta de archivo.

4. Análisis Profundo de los Resultados

El éxito de una compilación no solo depende de que el programa "corra", sino de entender qué ocurrió internamente. El sistema despliega tres bloques de información:

1. Tabla de Símbolos (Memoria del Compilador)

Es una representación visual de la memoria volátil del programa. Aquí verá:

Nombre: El identificador que usted eligió (ej. variable_x).

Tipo: El tipo deducido por el analizador (int, boolean o string).

Valor: El último valor asignado a dicha variable durante la ejecución del script.

2. Reporte Multicapa de Errores

El compilador identifica fallas en diferentes niveles de abstracción:

Errores Léxicos: Ocurren cuando utiliza símbolos que el lenguaje no conoce, como un @ o un &.

Errores Sintácticos: Suceden cuando la estructura es incorrecta, por ejemplo, olvidar el signo = en una asignación (x 10).

Errores Semánticos: Son los más avanzados. Detectan incongruencias de significado, como intentar aplicar un operador and a una cadena de texto "Hola", lo cual no tiene sentido lógico.

3. Evaluación del Resultado Booleano

Independientemente de cuántas asignaciones existan, el programa intentará evaluar la última expresión presente en el archivo. El resultado final (true o false) se imprime con énfasis para confirmar la validez lógica de su código.

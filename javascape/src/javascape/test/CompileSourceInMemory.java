package javascape.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

public class CompileSourceInMemory {
	public static void main(String args[]) throws IOException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);
		//out.println("package javascape.test;");
		out.println("public class HelloWorl2 {");
		out.println(" public static void main(String args[]) {");
		out.println(" System.out.println(\"This is in another java file\");");
		out.println(" }");
		out.println(" public static int getsayi() {");
		out.println(" return 3*89;");
		out.println(" }");
		out.println("}");
		out.close();
		JavaFileObject file = new JavaSourceFromString("HelloWorl2",
				writer.toString());
		Iterable<? extends JavaFileObject> compilationUnits = Arrays
				.asList(file);
		CompilationTask task = compiler.getTask(null, null, diagnostics, null,
				null, compilationUnits);
		boolean success = task.call();
		for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
			System.out.println(diagnostic.getCode());
			System.out.println(diagnostic.getKind());
			System.out.println(diagnostic.getPosition());
			System.out.println(diagnostic.getStartPosition());
			System.out.println(diagnostic.getEndPosition());
			System.out.println(diagnostic.getSource());
			System.out.println(diagnostic.getMessage(null));
		}
		System.out.println("Success: " + success);
		if (success) {
			try {
				Class.forName("HelloWorl2").getDeclaredMethod("main",new Class[] { String[].class }).invoke(null, new Object[] { null });
				Integer f = null;
				Method met = Class.forName("HelloWorl2").getDeclaredMethod("getsayi", null);
				Object ff = met.invoke(null, null);
				System.out.println("ff " + ff);
				// .invoke(null, new Object[] { null });
			} catch (ClassNotFoundException e) {
				System.err.println("Class not found: " + e);
			} catch (NoSuchMethodException e) {
				System.err.println("No such method: " + e);
			} catch (IllegalAccessException e) {
				System.err.println("Illegal access: " + e);
			} catch (InvocationTargetException e) {
				System.err.println("Invocation target: " + e);
			}
		}
	}
}

class JavaSourceFromString extends SimpleJavaFileObject {
	final String code;

	JavaSourceFromString(String name, String code) {
		super(URI.create("string:///" + name.replace('.', '/')
				+ Kind.SOURCE.extension), Kind.SOURCE);
		this.code = code;
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}
}
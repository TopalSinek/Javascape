package javascape.test;

import org.abstractmeta.toolbox.compilation.compiler.JavaSourceCompiler;
import org.abstractmeta.toolbox.compilation.compiler.impl.JavaSourceCompilerImpl;

public class CompileTest {

	public static void main(String[] args) {
		 JavaSourceCompiler javaSourceCompiler = new JavaSourceCompilerImpl();
		    JavaSourceCompiler.CompilationUnit compilationUnit = javaSourceCompiler.createCompilationUnit();
		  
		  //  compilationUnit.addClassPathEntry("/Users/xxxx/.m2/repository/javax/inject/javax.inject/1/javax.inject-1.jar");
		  
		    String iBarSource =  "package javascape.test;\n" +
		       "    public interface IBar {\n" +
		       "         public void execute();\n" +
		       "    }";
		  
		    compilationUnit.addJavaSource("javascape.test.IBar", iBarSource);
		  
		 
		    String barSource =  "package javascape.test;\n" +
		      "import javax.inject.Inject;\n" +
		      "public class Bar implements IBar {\n" +
		      "        private final String message;\n" +
		      "\n" +
		      "        @Inject\n" +
		      "        public Bar(String message) {\n" +
		      "            this.message = message;\n" +
		      "        }\n" +
		      "\n" +
		      "\n" +
		      "        @Override\n" +
		      "        public void execute() {\n" +
		      "            System.out.println(message);\n" +
		      "        }\n" +
		      "    }";
		  

		    compilationUnit.addJavaSource("javascape.test.IBar", barSource);
		  
		    try {
				ClassLoader classLoader = javaSourceCompiler.compile(compilationUnit);
				Class iBar = classLoader.loadClass("com.test.IBar");
				Class bar = classLoader.loadClass("com.test.Bar");
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
	}

}

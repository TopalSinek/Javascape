package javascape.util;

import org.abstractmeta.toolbox.compilation.compiler.JavaSourceCompiler;
import org.abstractmeta.toolbox.compilation.compiler.impl.JavaSourceCompilerImpl;

public class CompileJavaCode {

		public static Class compile(String javaCode)throws Exception{
			
				JavaSourceCompiler javaSourceCompiler = new JavaSourceCompilerImpl();
				JavaSourceCompiler.CompilationUnit compilationUnit = javaSourceCompiler.createCompilationUnit();
				    
				String javaSourceCode =  
					"package javascape.test;\n" +
					"public class MyClass {\n" + 
					javaCode+
				   
				    "}";
				    
				
				Class myClass=null;
			    compilationUnit.addJavaSource("javascape.test.MyClass", javaSourceCode);
			    ClassLoader classLoader = javaSourceCompiler.compile(compilationUnit);
			    myClass = classLoader.loadClass("javascape.test.MyClass");	
			    return myClass;

		}
	

}

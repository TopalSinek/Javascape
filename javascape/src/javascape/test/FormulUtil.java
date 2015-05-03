package javascape.test;

import java.lang.reflect.Method;

import org.abstractmeta.toolbox.compilation.compiler.JavaSourceCompiler;
import org.abstractmeta.toolbox.compilation.compiler.impl.JavaSourceCompilerImpl;

public class FormulUtil {


	public static int hesapla(Long GP, String formul,String icyHotStr,String icyHotStr2) {
		int pdp = 0;
		JavaSourceCompiler javaSourceCompiler = new JavaSourceCompilerImpl();
		JavaSourceCompiler.CompilationUnit compilationUnit = javaSourceCompiler.createCompilationUnit();
		    
		String javaSourceCode =  
			"package javascape.test;\n" +
			"public class MyFormula {\n" + 
			//"  	static { System.out.println(\"I am a runtime compiled Class ...\"); };\n " +
		    "   private int GP = " + GP + ";\n" + 
		    "   private double PDP = "+formul + ";\n" + 
		    "	public double getPDP() { return PDP; };\n"+
		    "public boolean icyHot(Integer temp1, Integer temp2) {"+
		    	icyHotStr+
				"}"+	
				 "public boolean icyHot2(int temp1, int temp2) {"+
			    	icyHotStr2+
					"}"+
		    "}";
		    
		
		//System.out.println(javaSourceCode);
	    compilationUnit.addJavaSource("javascape.test.MyFormula", javaSourceCode);
	    ClassLoader classLoader = javaSourceCompiler.compile(compilationUnit);
	    try {
			Class myFormulaClass = classLoader.loadClass("javascape.test.MyFormula");
				
			//myFormulaClass.newInstance();
				
			Method getPDPMethod = myFormulaClass.getMethod("getPDP", (Class[]) null);
			Object res = getPDPMethod.invoke(myFormulaClass.newInstance(), (Object[])null);
			pdp = ((Double) res).intValue();
				
			System.out.println("PDP (for GP = " + GP +") is: " + pdp);
				
			
			Method icyHotMethod = myFormulaClass.getMethod("icyHot", new Class[]{Integer.class,Integer.class});
			Object resB = icyHotMethod.invoke(myFormulaClass.newInstance(), new Integer[]{101,105});
			boolean icyB = ((Boolean) resB).booleanValue();
				System.out.println("icyB "+icyB);
				
				
				
				Method icyHotMethod2 = myFormulaClass.getMethod("icyHot2", int.class,int.class);
				Object resB2 = icyHotMethod2.invoke(myFormulaClass.newInstance(), 11,105);
				boolean icyB2 = ((Boolean) resB2).booleanValue();
					System.out.println("icyB2 "+icyB2);
		} 
	    catch (Exception e) {
	    	e.printStackTrace();
		}
	    return pdp;
	}
	

	

	
	public static void main(String[] args) {
		
		String methodInside=" if(temp1<0 && temp2>100 ) "+
									" return true;"+
									" else if(temp1>100 && temp2<0)"+
									" return true;"+
									"else"+
									" return false;";
		
		String methodInside2=" if(temp1<0 && temp2>100 ) "+
				" return false;"+
				" else if(temp1>100 && temp2<0)"+
				" return false;"+
				"else"+
				" return true;";

		System.out.println(hesapla(250L, "100-(30-(GP-250)*0.06)",methodInside,methodInside2));
	}	
	
}

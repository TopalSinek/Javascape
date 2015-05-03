package javascape.model;

import javascape.util.CompileJavaCode;

public class Question {

	public Question() {
		
		
	}
	
	public Class compile(String javaCode) throws Exception{
		return CompileJavaCode.compile(javaCode);
		
	}

	
	public boolean test(Class myClass)throws Exception{
		return false;
	}
	
	public boolean run(String javaCode)throws Exception{
		return test(compile(javaCode));
	}
	
	public String getTextInside(){
		return "";
	}
}

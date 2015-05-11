package javascape.model.question;

import java.lang.reflect.Method;

import javascape.model.Question;

public class Question_2_1 extends Question {

	public Question_2_1() {
	}

	public boolean test(Class myClass) throws Exception {

		Method squareUpMethod = myClass.getMethod("squareUp", int.class);

		// test method
		Object resB = squareUpMethod.invoke(myClass.newInstance(), 0);
		int[] sqI1 = (int[]) (resB);

		resB = squareUpMethod.invoke(myClass.newInstance(), 1);
		int[] sqI2 = (int[]) (resB);

		resB = squareUpMethod.invoke(myClass.newInstance(), 4);
		int[] sqI3 = (int[]) (resB);
		
		resB = squareUpMethod.invoke(myClass.newInstance(), 3);
		int[] sqI4 = (int[]) (resB);

		int[] res1 = {};
		int[] res2 = { 1};
		int[] res3 = { 0, 0, 0, 1, 0, 0, 2, 1, 0, 3, 2, 1, 4, 3, 2, 1 };
		int[] res4 = { 0, 0, 1, 0, 2, 1, 3, 2, 1 };

		System.out.println("controlArray(sqI1, res1) "+controlArray(sqI1, res1));
		System.out.println("controlArray(sqI1, res2) "+controlArray(sqI2, res2));
		System.out.println("controlArray(sqI1, res3) "+controlArray(sqI3, res3));
		System.out.println("controlArray(sqI1, res4) "+controlArray(sqI4, res4));
		if (controlArray(sqI1, res1) && controlArray(sqI2, res2) && 
				controlArray(sqI3 , res3) && controlArray(sqI4 , res4))
			return true;
		return false;
	}
	
	public boolean controlArray(int[] a,int[]b){
		if(a.length==b.length){
			if(a.length==0)
				return true;
			for (int i = 0; i < b.length; i++) {
				if(a[i]!=b[i])
					return false;
			}
			return true;
		}
		return false;
	}

	public String getTextInside() {
		return "public int[] squareUp(int n) {\n" 
				+ "}";
	}

}

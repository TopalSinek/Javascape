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
		int[] res2 = { 1 };
		int[] res3 = { 0, 0, 0, 1, 0, 0, 2, 1, 0, 3, 2, 1, 4, 3, 2, 1 };
		int[] res4 = { 0, 0, 1, 0, 2, 1, 3, 2, 1 };

		if (sqI1 == res1 && sqI2 == res2 && sqI3 == res3 && sqI4 == res4)
			return true;
		return false;
	}

	public String getTextInside() {
		return "public int[] squareUp(int n) {\n" 
				+ "}";
	}

}

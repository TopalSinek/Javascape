package javascape.model.question;

import java.lang.reflect.Method;

import javascape.model.Question;

public class Question_2_3 extends Question {

	public Question_2_3() {
	}

	public boolean test(Class myClass) throws Exception {

		Method countClumpsMethod = myClass.getMethod("countClumps", int[].class);

		// test method

		int[] test1 = { 1, 2, 2, 3, 4, 4 };
		Object resB = countClumpsMethod.invoke(myClass.newInstance(), test1);
		int clumpsI1 = ((Integer) resB).intValue();

		int[] test2 = { 1, 1, 1, 1, 1 };
		resB = countClumpsMethod.invoke(myClass.newInstance(), test2);
		int clumpsI2 = ((Integer) resB).intValue();

		int[] test3 = { 0, 0, 0, 2, 2, 1, 1, 1, 2, 1, 1, 2, 2 };
		resB = countClumpsMethod.invoke(myClass.newInstance(), test3);
		int clumpsI3 = ((Integer) resB).intValue();

		int[] test4 = { 1, 1, 2, 1, 1 };
		resB = countClumpsMethod.invoke(myClass.newInstance(), test4);
		int clumpsI4 = ((Integer) resB).intValue();

		if (clumpsI1 == 2 && clumpsI2 == 1 && clumpsI3 == 5 && clumpsI4 == 2)
			return true;
		return false;
	}

	public String getTextInside() {
		return "public int countClumps(int[] nums) {\n" 
				+ "}";
	}

}

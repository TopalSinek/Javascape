package javascape.model.question;

import java.lang.reflect.Method;

import javascape.model.Question;

public class Question_2_2 extends Question {

	public Question_2_2() {
	}

	public boolean test(Class myClass) throws Exception {

		Method maxMirrorMethod = myClass.getMethod("maxMirror", int[].class);

		// test method

		int[] test1 = { 1, 2, 3, 8, 9, 3, 2, 1 };
		Object resB = maxMirrorMethod.invoke(myClass.newInstance(), test1);
		int mirrorI1 = ((Integer) resB).intValue();

		int[] test2 = {};
		resB = maxMirrorMethod.invoke(myClass.newInstance(), test2);
		int mirrorI2 = ((Integer) resB).intValue();

		int[] test3 = { 1 };
		resB = maxMirrorMethod.invoke(myClass.newInstance(), test3);
		int mirrorI3 = ((Integer) resB).intValue();

		int[] test4 = { 1, 2, 1, 20, 21, 1, 2, 1, 2, 23, 24, 2, 1, 2, 1, 25 };
		resB = maxMirrorMethod.invoke(myClass.newInstance(), test4);
		int mirrorI4 = ((Integer) resB).intValue();

		if (mirrorI1 == 3 && mirrorI2 == 0 && mirrorI3 == 1 && mirrorI4 == 4)
			return true;
		return false;
	}

	public String getTextInside() {
		return "public int maxMirror(int[] nums) {\n" 
				+ "}";
	}

}

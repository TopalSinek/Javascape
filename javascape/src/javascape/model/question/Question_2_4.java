package javascape.model.question;

import java.lang.reflect.Method;

import javascape.model.Question;

public class Question_2_4 extends Question {

	public Question_2_4() {
	}

	public boolean test(Class myClass) throws Exception {

		Method seriesUpMethod = myClass.getMethod("seriesUp", int.class);

		// test method

		Object resB = seriesUpMethod.invoke(myClass.newInstance(), 0);
		int[] seriesI1 = (int[]) (resB);

		resB = seriesUpMethod.invoke(myClass.newInstance(), 1);
		int[] seriesI2 = (int[]) (resB);

		resB = seriesUpMethod.invoke(myClass.newInstance(), 4);
		int[] seriesI3 = (int[]) (resB);

		resB = seriesUpMethod.invoke(myClass.newInstance(), 6);
		int[] seriesI4 = (int[]) (resB);

		int[] res1 = {};
		int[] res2 = { 1 };
		int[] res3 = { 0, 0, 0, 1, 0, 0, 2, 1, 0, 3, 2, 1, 4, 3, 2, 1 };
		int[] res4 = { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 1, 0, 0, 0, 3, 2, 1, 0,
				0, 4, 3, 2, 1, 0, 5, 4, 3, 2, 1, 6, 5, 4, 3, 2, 1 };

		if (seriesI1 == res1 && seriesI2 == res2 && seriesI3 == res3
				&& seriesI4 == res4)
			return true;
		return false;
	}

	public String getTextInside() {
		return "public int[] seriesUp(int n) {\n" 
				+ "}";
	}

}

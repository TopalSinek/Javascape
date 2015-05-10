package javascape.model.question;

import java.lang.reflect.Method;

import javascape.model.Question;

public class Question_1_2 extends Question {

	public Question_1_2() {
	}

	public boolean test(Class myClass) throws Exception {

		Method backAroundMethod = myClass.getMethod("backAround", String.class);

		// test method
		Object resB = backAroundMethod.invoke(myClass.newInstance(), "cat");
		String baS1 = (String) (resB);

		resB = backAroundMethod.invoke(myClass.newInstance(), "Hello");
		String baS2 = (String) (resB);

		resB = backAroundMethod.invoke(myClass.newInstance(), "a");
		String baS3 = (String) (resB);

		if (baS1.equals("tcatt") && baS2.equals("oHelloo") && baS3.equals("aaa"))
			return true;

		return false;
	}

	public String getTextInside() {
		return "public String backAround(String str) {\n" 
				+ "}";
	}

}

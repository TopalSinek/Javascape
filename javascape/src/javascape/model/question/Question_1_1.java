package javascape.model.question;

import java.lang.reflect.Method;

import javascape.model.Question;

public class Question_1_1 extends Question {

	public Question_1_1() {
	}

	public boolean test(Class myClass) throws Exception {

		Method icyHotMethod = myClass.getMethod("icyHot", int.class, int.class);

		// test method
		Object resB = icyHotMethod.invoke(myClass.newInstance(), 120, -1);
		boolean icyB1 = ((Boolean) resB).booleanValue();

		resB = icyHotMethod.invoke(myClass.newInstance(), -1, 120);
		boolean icyB2 = ((Boolean) resB).booleanValue();

		resB = icyHotMethod.invoke(myClass.newInstance(), 2, 120);
		boolean icyB3 = ((Boolean) resB).booleanValue();

		if (icyB1 && icyB2 && !icyB3)
			return true;

		return false;
	}

	public String getTextInside() {
		return "public static boolean icyHot(int temp1,int temp2) {\n" + "}";
	}

}

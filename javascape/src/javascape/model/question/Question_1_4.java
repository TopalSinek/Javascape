package javascape.model.question;

import java.lang.reflect.Method;

import javascape.model.Question;

public class Question_1_4 extends Question {

	public Question_1_4() {
	}

	public boolean test(Class myClass) throws Exception {
		Method withoutDoublesMethod = myClass.getMethod("withoutDoubles",
				int.class, int.class, boolean.class);

		// test method
		Object resB = withoutDoublesMethod.invoke(myClass.newInstance(), 2, 3,
				true);
		int wdI1 = ((Integer) resB).intValue();

		resB = withoutDoublesMethod.invoke(myClass.newInstance(), 3, 3, true);
		int wdI2 = ((Integer) resB).intValue();

		resB = withoutDoublesMethod.invoke(myClass.newInstance(), 6, 6, true);
		int wdI3 = ((Integer) resB).intValue();

		resB = withoutDoublesMethod.invoke(myClass.newInstance(), 6, 6, false);
		int wdI4 = ((Integer) resB).intValue();

		if (wdI1 == 5 && wdI2 == 6 && wdI3 == 7 && wdI4 == 12)
			return true;
		return false;
	}

	public String getTextInside() {
		return "public int withoutDoubles(int die1, int die2, boolean noDoubles) {\n"
				+ "}";
	}

}

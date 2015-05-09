package javascape.model.question;

import java.lang.reflect.Method;

import javascape.model.Question;

public class Question_1_3 extends Question {

	public Question_1_3() {
	}

	public boolean test(Class myClass) throws Exception {
		Method blackjackMethod = myClass.getMethod("blackjack", int.class,
				int.class);

		// test method
		Object resB = blackjackMethod.invoke(myClass.newInstance(), 19, 21);
		int bjackI1 = ((Integer) resB).intValue();

		resB = blackjackMethod.invoke(myClass.newInstance(), 20, 19);
		int bjackI2 = ((Integer) resB).intValue();

		resB = blackjackMethod.invoke(myClass.newInstance(), 18, 22);
		int bjackI3 = ((Integer) resB).intValue();

		if (bjackI1 == 21 && bjackI2 == 20 && bjackI3 == 18)
			return true;
		return false;
	}

	public String getTextInside() {
		return "public int blackjack(int a, int b) {\n" + "}";
	}

}

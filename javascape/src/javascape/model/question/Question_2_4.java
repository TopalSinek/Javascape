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
		int[] res3 = { 1, 1, 2, 1, 2, 3, 1, 2, 3, 4 };
		int[] res4 = { 1, 1, 2, 1, 2, 3, 1, 2, 3, 4, 1, 2, 3, 4, 5, 1, 2, 3, 4,
				5, 6 };

		if (controlArray(seriesI1, res1) && controlArray(seriesI2, res2) && 
				controlArray(seriesI3 , res3) && controlArray(seriesI4  , res4))
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
		return "public int[] seriesUp(int n) {\n" 
				+ "}";
	}

}

package javascape.model;

public class Definitions {

	public static final String LANGUAGE_TR="TR";
	public static final String LANGUAGE_ENG="ENG";
	
	public static final String LANGUAGE_TR_STR="TÜRKÇE";
	public static final String LANGUAGE_ENG_STR="ENGLISH";
	
	public static final String DIFFICULTY_EASY="EASY";
	public static final String DIFFICULTY_NORMAL="NORMAL";
	public static final String DIFFICULTY_HARD="HARD";
	
	public static final String MODE_CS="CS";
	public static final String MODE_MATH="MATH";
	
	
	
	//Key codes
	public static final int KEY_UP=  16777217;
	public static final int KEY_DOWN=  16777218;
	public static final int KEY_LEFT=  16777219;
	public static final int KEY_RIGHT=  16777220;
	
	
	public static final int KEY_E=  101;
	public static final int KEY_Q=  113;
	public static final int ESC = 27;
	
	//check if direction key
	public static boolean isDirectionKey(int keyCode) {
		boolean ret=false;
		if(keyCode==KEY_UP || keyCode==KEY_DOWN || keyCode==KEY_LEFT || keyCode==KEY_RIGHT )
			ret=true;
		return ret;
	}
	
	
	public static boolean isKeyE(int keyCode) {
		return keyCode==KEY_E ;
	}
	public static boolean isKeyQ(int keyCode) {
		return keyCode==KEY_Q ;
	}
	
}

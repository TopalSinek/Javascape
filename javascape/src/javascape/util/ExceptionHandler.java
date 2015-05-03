package javascape.util;

import org.eclipse.swt.widgets.Shell;

public class ExceptionHandler {
	
	public static void handleException(Shell shell,Exception e){
		String msg=e.getMessage();
		if(msg==null)
			msg="System Error";
		UIUtil.showMsgBox(shell, "Error", msg);
		
	}
}

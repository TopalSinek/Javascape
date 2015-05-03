package javascape.util;

import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class UIUtil {

	public static void showMsgBox(Shell shell,String title,String msg){
		MessageBox mb=new MessageBox(shell);
		mb.setMessage(msg);
		mb.setText(title);
		mb.open();
	}
}

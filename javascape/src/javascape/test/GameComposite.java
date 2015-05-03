package javascape.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class GameComposite extends Composite{
	public Button btnMenu;
	public Button btnLogin;
	public Canvas canvas ;
	public GameComposite(Composite parent, int style) {
		super(parent, style);
		initialize();
	}
	private void initialize() {
		System.out.println("GIRDI");
		 Composite comp=new Composite(this, SWT.BORDER);
	        
	        comp.setLayout(new GridLayout(1,true));
	        Composite compUst=new Composite(comp,SWT.BORDER);
	        
	        
	        compUst.setLayout(new GridLayout(3, false));
	        
	         btnMenu=new Button(compUst,SWT.PUSH);
	        btnMenu.setText("MENU");
	        

	         btnLogin=new Button(compUst,SWT.PUSH);
	        btnLogin.setText("Login");
	        
	       
	        
	        GridData gdUst=new GridData();
	        GridData gdAlt=new GridData(GridData.FILL_BOTH);
	        gdAlt.grabExcessHorizontalSpace=true;
	        gdAlt.grabExcessVerticalSpace=true;
	       
	        
	        compUst.setLayoutData(gdUst);
	        compUst.pack();
	         canvas = new Canvas(comp, SWT.DOUBLE_BUFFERED | SWT.BORDER);
	        
	    
	      
	        canvas.setLayoutData(gdAlt);
	        canvas.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
	        setLayout(new FillLayout());
	        setSize(1500, 800);
	        
	       
	        canvas.forceFocus();
	      
	      
	}

}

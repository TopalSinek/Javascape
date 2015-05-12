package javascape.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class MenuComposite extends Composite {
	
	public Button btnRestart;
	public Button btnSave;
	public Button btnLoad;
	public Button btnExit;
	public Button btnReturn;
	

	public MenuComposite(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
		
		btnReturn = new Button(this, SWT.PUSH);
		btnReturn.setText("Return to Game");
	
		
		btnRestart=new Button(this, SWT.PUSH);
		btnRestart.setText("Main menu");
		
		
		btnSave=new Button(this, SWT.PUSH);
		btnSave.setText("Save");
		
		
		btnLoad=new Button(this, SWT.PUSH);
		btnLoad.setText("Load");
		
		btnExit = new Button(this,SWT.PUSH);
		btnExit.setText("Exit");
		
		
		GridData gridData = new GridData(GridData.CENTER | GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		gridData.horizontalSpan = 1;
		gridData.widthHint=150;
		gridData.heightHint = 35;
		gridData.grabExcessHorizontalSpace=true;
		gridData.grabExcessVerticalSpace = true;
		
		
		btnRestart.setLayoutData(gridData);
		btnSave.setLayoutData(gridData);
		btnLoad.setLayoutData(gridData);
		btnExit.setLayoutData(gridData);
		btnReturn.setLayoutData(gridData);
		
		
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		
		this.setLayout(gridLayout);
		setSize(new Point(300, 350));
		
	}

}

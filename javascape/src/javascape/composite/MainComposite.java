package javascape.composite;

import java.io.InputStream;

import javascape.resourses.Dummy;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Text;

public class MainComposite extends Composite {
	
	public Label lblLogo;
	public Button btnNew;
	public Button btnLoad;
	public Button btnCredit;
	public Button btnHelp;
	public Button btnOptions;
	public Button btnExit;
	public Label lbluser;
	public Text txtName;
	public Label lblError;

	public MainComposite(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
		
		lblLogo = new Label(this,SWT.BORDER);
		InputStream is=new Dummy().getClass().getResourceAsStream("MainLogo.jpg");
		Image logo = new Image(getDisplay(),is);
		lblLogo.setImage(logo);
    
		btnNew=new Button(this, SWT.PUSH);
		btnNew.setText("New Game");
		
		btnLoad=new Button(this, SWT.PUSH);
		btnLoad.setText("Load");
		
		
		btnCredit=new Button(this, SWT.PUSH);
		btnCredit.setText("Credits");
		
		
		btnHelp=new Button(this, SWT.PUSH);
		btnHelp.setText("Help");
		
		btnOptions=new Button(this, SWT.PUSH);
		btnOptions.setText("Options");
		
		btnExit=new Button(this, SWT.PUSH);
		btnExit.setText("Exit");
		
		lbluser = new Label(this,SWT.CENTER);
		
		txtName=new Text(this,SWT.BORDER);
		
	
		
		lblError=new Label(this,SWT.NONE);
		
		GridData gridData = new GridData(SWT.CENTER,this.getBorderWidth()/2,true,false);
		gridData.horizontalSpan = 1;
		gridData.widthHint=300;
		gridData.heightHint = 55;
	
		
		GridData gridDataNew = new GridData(SWT.CENTER,this.getBorderWidth()/2,true,false);
		gridDataNew.horizontalSpan = 1;
		gridDataNew.widthHint=300;
		gridDataNew.heightHint = 55;
		gridDataNew.verticalIndent = 150;
		
		GridData gridDataLogo = new GridData(SWT.CENTER,this.getBorderWidth()/2,true,false);
		gridDataLogo.widthHint = 961;
		gridDataLogo.heightHint = 153;
		
		GridData gridDataLabel = new GridData(SWT.CENTER,this.getBorderWidth()/2,true,false);
		gridDataLabel.widthHint=300;
		gridDataLabel.heightHint = 20;
		
		
		lblError.setLayoutData(gridData);
		txtName.setLayoutData(gridDataLabel);
		lblLogo.setLayoutData(gridDataLogo);
		lbluser.setLayoutData(gridDataLabel);
		btnNew.setLayoutData(gridDataNew);
		btnLoad.setLayoutData(gridData);
		btnCredit.setLayoutData(gridData);
		btnHelp.setLayoutData(gridData);
		btnOptions.setLayoutData(gridData);
		btnExit.setLayoutData(gridData);
		
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		
		this.setLayout(gridLayout);
		

		Monitor mon = Display.getDefault().getMonitors()[0];
		Rectangle rect = mon.getBounds();
		setSize(new Point(rect.width,rect.height));
		
	}



}

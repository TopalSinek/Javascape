package javascape.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class TopComposite extends Composite {
	public Label lblUserName;
	public  Button btnMusic;
	public  Button btnMenu;
	public  Button btnAdmin;
	public Label lblDeath;
	public Label lblScore;
	public Label lblSection;
	
	public TopComposite(Composite parent, int style) {
		super(parent, style);
		initilize();
	}

	private void initilize() {
		this.setLayout(new GridLayout(7, false));

		btnMusic=new Button(this,SWT.PUSH);
		btnMusic.setText("Music On");

		btnMenu=new Button(this,SWT.PUSH);
		btnMenu.setText("Menu");


		btnAdmin=new Button(this,SWT.PUSH);
		btnAdmin.setText("AdminMode");

		lblUserName=new Label(this,SWT.NONE);

		lblSection=new Label(this,SWT.NONE);
		lblScore=new Label(this,SWT.NONE);
		lblDeath=new Label(this,SWT.NONE);


	}

}

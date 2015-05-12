package javascape.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class QuestionComposite extends Composite {
	
	
	public Label lblImage;
	public Text txtAnswer;
	public Text txtError;
	public TabFolder tab ;

	public QuestionComposite(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
    
	   
	    lblImage=new Label(this, SWT.BORDER);
  
	    
	     tab = new TabFolder(this, SWT.NONE);
	    
	    TabItem tab1 = new TabItem(tab, SWT.NONE);
	    tab1.setText("Question");
	    
	  
	    txtAnswer=new Text(tab,SWT.BORDER|SWT.MULTI);
	    
	    tab1.setControl(txtAnswer);	 
	    
	    
	    TabItem tab2 = new TabItem(tab, SWT.NONE);
	    tab2.setText("Console");
	    
	  
	    
	    txtError=new Text(tab,SWT.BORDER|SWT.MULTI|SWT.READ_ONLY);
	    tab2.setControl(txtError);	 

		GridData gridData = new GridData(GridData.CENTER | GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		gridData.horizontalSpan = 1;
		gridData.widthHint=150;
		gridData.grabExcessHorizontalSpace=true;
		
		lblImage.setLayoutData(gridData);
		tab.setLayoutData(gridData);
		
		
		txtAnswer.setLayoutData(gridData);
		
		txtError.setLayoutData(gridData);
		
	
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		
		this.setLayout(gridLayout);
		
		setSize(1600,800);
		
	}



}

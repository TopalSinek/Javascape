package javascape.dialog;

import javascape.composite.MainComposite;
import javascape.util.UIUtil;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class MainDialog extends Dialog{

	
	private MainComposite frm;
	private String userName;
	private String fileName;
	
	public MainDialog(Shell shell) {
		super(shell);
		setShellStyle(getShellStyle() | SWT.ON_TOP | SWT.MIN | SWT.MAX);
	}
	
	
	private void addListeners(){
		
		frm.lbluser.setText("User Name");
		frm.txtName.setText("utku");
		frm.btnNew.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = frm.txtName.getText();
				if(name.trim().length() == 0){
					frm.lblError.setText("Please Enter a Name");
					frm.txtName.setFocus();
				}
				else{
					userName = name;
					clearError();
					okPressed();
				}
			}
		});
		
		frm.btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				cancelPressed();
			}
		});
		
		frm.btnLoad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell());
				String fn = fd.open();
				
				if(fn != null){
					fileName=fn;
					okPressed();
				}
			}
		});
		
		
		//------------------------EGE------------------------------------
		//Credits Button
	    //Opens a new window with our names , i will write my name at the top of the list by the way :*
		frm.btnCredit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Credits TODO
				String creditsmsg = "*-*-*-Developers-*-*-* \n" //We can declare them in somewhere else 
						+ "Ege Yosunkaya \n"
						+ "Utku Oymak \n"
						+ "Cagdas Yýlmaz \n"
						+ "Burcu Uslu \n"
						+ "Mustafa Duymus \n"
						+ "\n \t Version 1.0";
				
				UIUtil.showMsgBox(getShell(), "CREDITS", creditsmsg);
			}
		});
		
		//Opens a new window , writing coming soon for now.... 
		
		frm.btnOptions.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Options TODO
				String optionsmsg = "Coming Soon!!"; //We can declare them in somewhere else 
				
				UIUtil.showMsgBox(getShell(), "OPTIONS", optionsmsg);
				
			}
		});
		
		
		

		frm.btnHelp.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Help TODO
				String helpmsg = ""; // Someone fill here pls :(
				
				UIUtil.showMsgBox(getShell(), "HELP", helpmsg);
			}
		});
		
		//---------------------Ege End--------------------------------------
	}
	
	private void clearError(){
		frm.lblError.setText("");
		
	}
	protected Control createDialogArea(Composite parent) {
		Composite inner;
		inner = new Composite(parent, SWT.CENTER);
		GridData gridData = new GridData(SWT.CENTER,inner.getBorderWidth()/2,true,false);
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		inner.setLayoutData(gridData);
		frm=new MainComposite(inner,SWT.NONE);
		addListeners();
		
		return inner;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		Button btnOk=createButton(parent, IDialogConstants.OK_ID, "OK"/*IDialogConstants.OK_LABEL*/, true);
		Button btnCancel=createButton(parent, IDialogConstants.CANCEL_ID, "Cancel"/*IDialogConstants.CANCEL_LABEL*/, false);
		btnOk.setVisible(false);
		btnCancel.setVisible(false);
	}


	public MainComposite getFrm() {
		return frm;
	}


	public String getUserName() {
		return userName;
	}


	public String getFileName() {
		return fileName;
	}


}

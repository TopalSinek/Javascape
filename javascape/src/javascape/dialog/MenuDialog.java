package javascape.dialog;

import javascape.composite.MenuComposite;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class MenuDialog extends Dialog{

	
	private MenuComposite frm;
	private String action;
	
	public MenuDialog(Shell shell) {
		super(shell);
		setShellStyle(getShellStyle() | SWT.ON_TOP);
	}
	
	
	private void addListeners(){
		
		
		frm.btnRestart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				action="RESTART";
					okPressed();
				
			}
		});
		frm.btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				action="SAVE";
				okPressed();
			}
		});
		
		frm.btnLoad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				action="LOAD";
				okPressed();
			}
		});
		
		frm.btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){
				action = "RETURN";
				cancelPressed();
				
			}
		});
		
		
		frm.btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){
				action = "EXIT";
				cancelPressed();
				System.exit(1);
			}
		});
		
	}
	
	
	protected Control createDialogArea(Composite parent) {
		Composite inner;
		inner = new Composite(parent, SWT.NONE);
		frm=new MenuComposite(inner,SWT.NONE);
		addListeners();
		
		return inner;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		Button btnOk=createButton(parent, IDialogConstants.OK_ID, "OK"/*IDialogConstants.OK_LABEL*/, true);
		Button btnCancel=createButton(parent, IDialogConstants.CANCEL_ID, "Cancel"/*IDialogConstants.CANCEL_LABEL*/, false);
		btnOk.setVisible(false);
		btnCancel.setVisible(false);
	}


	public String getAction() {
		return action;
	}


	


}

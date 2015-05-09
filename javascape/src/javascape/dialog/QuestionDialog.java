package javascape.dialog;
import org.eclipse.swt.widgets.MessageBox;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Timer;
import java.util.TimerTask;

import javascape.composite.QuestionComposite;
import javascape.game.Game;
import javascape.model.Question;
import javascape.util.UIUtil;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class QuestionDialog extends Dialog{

	
	private QuestionComposite frm;
	private Image questionImg;
	private Question question;
	
	
	public QuestionDialog(Shell shell) {
		super(shell);
		setShellStyle(getShellStyle() | SWT.ON_TOP | SWT.RESIZE  | SWT.MAX  | SWT.MIN );
//		setShellStyle( SWT.ON_TOP | SWT.RESIZE);
		
	}
	
	
	private void addListeners(){
		
		
		
		
		frm.txtError.setVisible(false);
	//	getShell().setFullScreen(true);
		if(questionImg!=null)
			frm.lblImage.setImage(questionImg);
		if(question!=null){
			frm.txtAnswer.setText(question.getTextInside());
		}
		frm.txtAnswer.setFocus();
	}
	
	private void clearError(){
		frm.txtError.setText("");
		
	}
	protected Control createDialogArea(Composite parent) {
		Composite inner;
		inner = new Composite(parent, SWT.NONE);
		GridData gd=new GridData(GridData.FILL_BOTH);
		gd.grabExcessHorizontalSpace=true;
		gd.grabExcessVerticalSpace=true;
		gd.minimumHeight=800;
		gd.minimumWidth=1600;
		
		inner.setLayoutData(gd);
		//System.out.println(inner.getLayout());
		inner.setLayout(new GridLayout(1,true));
		
		frm=new QuestionComposite(inner,SWT.NONE);
		frm.setLayoutData(gd);
	
		addListeners();	
		
		
		return inner;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		 Button btnOk=createButton(parent, IDialogConstants.OK_ID, "OK"/*IDialogConstants.OK_LABEL*/, true);
		 Button btnCancel=createButton(parent, IDialogConstants.CANCEL_ID, "Cancel"/*IDialogConstants.CANCEL_LABEL*/, false);
		btnOk.setVisible(true);
		btnCancel.setVisible(true);
		

	}

	@Override
	protected void okPressed() {
		clearError();
		// -------------------EGE--------------------- 09/05/15
		//Timer'� buraya yaz�yorum ama hi� emin de�ilim bok da ��kabilir. 
		Timer looptimer = new Timer();
		
		TimerTask loopdetector = new TimerTask() {
			final int LOOPDETECTTIME = 10;
			int sayac = 0; //Bu sayac� da declerationlar�n oldugu yere ta��yabiliriz
			@Override 
			public void run() {
				sayac++;
				//Loop detect time'� da ayn� �ekilde constant declerationlar�n oldugu yere ta��yabiliriz. �yle mavi falan durunca cok ho�uma gidiyor ;))
				if(sayac == LOOPDETECTTIME){
					MessageBox msg = new MessageBox(getShell()); //shell'i b�yle alaca��mdan emin de�ilim
					msg.setText("Looks like you got into a infinite loop");
					msg.open();
					looptimer.cancel();
				}
					//Burda Question dialogu direk kapat diyip bi messagebox g�sterelim bence ama nas�l kapataca��m� bilemedim
					//Bi de burda adam�n can� gidicek mi gidicekse onu da ayaralamak laz�m
					//�pt�m :*
			}
			
		};
		
		looptimer.schedule(loopdetector , 0 , 1000);
		
		
		
		
		String javaCode=frm.txtAnswer.getText();
		if(javaCode.trim().length()==0){
			frm.txtError.setText("Write a code");
			frm.tab.setSelection(1);
			return;
		}
		if(question!=null){
			boolean ret=false;
			try {
				ret=question.run(javaCode);
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				String exceptionAsString = sw.toString();
				
				frm.txtError.setText(exceptionAsString);
				frm.tab.setSelection(1);
				Game.gamePlay.getUser().died();
				return;
			}
			if(ret){
				super.okPressed();
			}
			else{
				
				frm.txtError.setText("Code not working!");
				frm.tab.setSelection(1);
				Game.gamePlay.getUser().died();
				if(Game.gamePlay.getUser().getNumOfDeath() >= 5){
					UIUtil.showMsgBox(getShell(), "Wrong move!", "Sorry. You got cought!");
					Game.gamePlay.getUser().setisDead(true);
					
					cancelPressed();
					
				}
				return;
			}
		}
		else{
			frm.txtError.setText("System Error");
			frm.tab.setSelection(1);
			return;
		}
		
		
	}
	
	@Override
	protected void cancelPressed() {
		super.cancelPressed();
	}

	public QuestionComposite getFrm() {
		return frm;
	}
	
	public void set(Image questionImg,Question question){
		this.questionImg=questionImg;
		this.question=question;
		
	}



}

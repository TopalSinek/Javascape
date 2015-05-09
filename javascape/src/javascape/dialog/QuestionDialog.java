package javascape.dialog;
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
		//Timer'ý buraya yazýyorum ama hiç emin deðilim bok da çýkabilir. 
		
		// utku - test etmemizin neredeyse hiç bir yolu yok ama sanirim bir işe yaramiyo :D
		Timer looptimer = new Timer();
		
		TimerTask loopdetector = new TimerTask() {
			final int LOOPDETECTTIME = 10;
			int sayac = 0; //Bu sayacý da declerationlarýn oldugu yere taþýyabiliriz
			@Override 
			public void run() {
				sayac++;
				//Loop detect time'ý da ayný þekilde constant declerationlarýn oldugu yere taþýyabiliriz. Öyle mavi falan durunca cok hoþuma gidiyor ;))
				if(sayac == LOOPDETECTTIME){
					UIUtil.showMsgBox(Game.gamePlay.getShell(), "Infinite loop", "You got into an infinite loop"); // mesaj göstermek istediğinizde  ui utili kullanın massage box açmayın yeni.
					looptimer.cancel();
				}
					//Burda Question dialogu direk kapat diyip bi messagebox gösterelim bence ama nasýl kapatacaðýmý bilemedim
					//Bi de burda adamýn caný gidicek mi gidicekse onu da ayaralamak lazým
					//Öptüm :*
			}
			
		};
		
		try {
			looptimer.schedule(loopdetector , 0 , 1000);
		} catch (Exception e1) {
			UIUtil.showMsgBox(Game.gamePlay.getShell(), "Infinite loop", "You got into an infinite loop"); // mesaj göstermek istediğinizde  ui utili kullanın massage box açmayın yeni.
			looptimer.cancel();
			e1.printStackTrace();
		}
		
		
		
		
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
				looptimer.cancel();
				super.okPressed();
			}
			else{
				
				frm.txtError.setText("Code not working!");
				frm.tab.setSelection(1);
				Game.gamePlay.getUser().died();
				if(Game.gamePlay.getUser().getNumOfDeath() >= 5){
					UIUtil.showMsgBox(getShell(), "Wrong move!", "Sorry. You got cought!");
					Game.gamePlay.getUser().setisDead(true);
					looptimer.cancel();
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

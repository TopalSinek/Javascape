package javascape.test;

import javascape.dialog.MainDialog;
import javascape.game.GamePlay;
import javascape.model.Definitions;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class GameDialog extends Dialog {

	
	private GameComposite frm;
	public static     GamePlay gamePlay=new GamePlay();
	public static MakeSound makeSound=null;
	public GameDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.TOP | SWT.MAX | SWT.MIN | SWT.RESIZE);
	}
	
	private void addListeners(){
		
		frm.btnMenu.addSelectionListener(new SelectionAdapter() {
	        	@Override
	        	public void widgetSelected(SelectionEvent e) {
//	        		String filename ="D://Test2.wav";
//	        		new MakeSound().playSound(filename);
	        		if(makeSound==null){
	        			makeSound=new MakeSound();
		        		makeSound.start();
	        		}
	        		
	        		gamePlay.getCanvas().forceFocus();
	        	}
			});
	        
		frm.btnLogin.addSelectionListener(new SelectionAdapter(){
	        	@Override
	        	public void widgetSelected(SelectionEvent e) {
	        		MainDialog dg=new MainDialog(frm.getShell());
	        		dg.open();
	        		
	        	}
	        
			});
		
		 gamePlay.setSection(TestSectionCreation.createSection(true));
	        gamePlay.setUser(TestSectionCreation.createUser());
	        gamePlay.setCanvas(frm.canvas);
	        draw(frm.canvas);
	        frm.canvas.forceFocus();
	        gamePlay.redraw();
	        System.out.println("GIRDI");
	}
	
	
private static void draw(Canvas canvas){
		
		canvas.addPaintListener(new PaintListener() {
		      public void paintControl(PaintEvent e) {
		        // Do some drawing
//		        Rectangle rect = ((Canvas) e.widget).getBounds();
//		        e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_RED));
//		        e.gc.drawFocus(5, 5, rect.width - 10, rect.height - 10);
//		        e.gc.drawText("You can draw text directly on a canvas", 60, 60);
//		        e.gc.drawLine(10, 10, 50, 50);
		    	
		        
		      drawSection( e.gc);
		        
//		        
//		    	  Image bufferImage = new Image(Display.getDefault(),rect.width,rect.height); 
//		    	  GC bufferGC = new GC(bufferImage); 
//		    	  drawSection( bufferGC);
//		    	  e.gc.drawImage(bufferImage,0,0); 
//		    	  bufferImage.dispose(); 

		    	 } 
		      
		    });
		
		canvas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				boolean redraw=false;
					if(Definitions.isDirectionKey(e.keyCode))
						redraw=gamePlay.directionKeyPress(e.keyCode);
					else{
						
					}
					if(redraw)
						gamePlay.redraw();
				super.keyPressed(e);
				
			}

			
		});
	}
	
	
	
	private static void drawSection(GC gc){
		gamePlay.draw( gc);
	}
	
	protected Control createDialogArea(Composite parent) {
		
//		Composite inner;
//		inner = new Composite(parent, SWT.BORDER);
//		 inner.setLayout(new FillLayout());
//		inner.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED));
//		GridData gd=new GridData(GridData.FILL_BOTH | GridData.FILL);
//		inner.setLayoutData(gd);
//			inner.setSize(1500, 800);
		
		
		
		
		frm=new GameComposite(parent,SWT.BORDER);
		GridData gd=new GridData(GridData.FILL_BOTH | GridData.FILL);
		frm.setLayoutData(gd);
		
		frm.setSize(1500, 800);
		parent.getShell().setSize(1500, 800);
	//	frm.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED));
		addListeners();
		
		return frm;
	}
	
	protected void createButtonsForButtonBar(Composite parent) {
		Button btnOk=createButton(parent, IDialogConstants.OK_ID, "OK"/*IDialogConstants.OK_LABEL*/, true);
		Button btnCancel=createButton(parent, IDialogConstants.CANCEL_ID, "Cancel"/*IDialogConstants.CANCEL_LABEL*/, false);
		btnOk.setVisible(false);
		btnCancel.setVisible(false);
	}

	public GameComposite getFrm() {
		return frm;
	}

}

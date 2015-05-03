package javascape.game;

import java.util.Vector;

import javascape.composite.TopComposite;
import javascape.creater.SectionCreater;
import javascape.dialog.MainDialog;
import javascape.dialog.MenuDialog;
import javascape.model.Definitions;
import javascape.model.Section;
import javascape.model.User;
import javascape.util.ExceptionHandler;
import javascape.util.FileUtil;
import javascape.util.SoundUtil;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class Game {


	public static GamePlay gamePlay=new GamePlay();
	public static SoundUtil soundUtil=null;
	public static boolean rtype=true;


	private static void draw(Canvas canvas){ // create and draw canvas

		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				// Do some drawing
				drawSection( e.gc);
			} 

		});

		canvas.addKeyListener(new KeyAdapter() { // Key listener for 'esc' in game
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==Definitions.ESC)
					menuAction();
				else
					gamePlay.keyPress(e.keyCode);
				super.keyPressed(e);

			}


		});


	}

	private static void drawSection(GC gc){ //Draw section using gameplay
		gamePlay.draw( gc);
	}

	//---------------------MAIN--------------------------
	public static void main(String[] args) {  //Main Thread


		Display display = new Display(); // add display

		Shell shell = new Shell(display); // add shell

		shell.setText("Javascape"); // shell header


		MainDialog dg=new MainDialog(shell); // create main dialog

		if(Window.OK==dg.open()){ // 'OK' action
			shell.setLayout(new FillLayout());
			Composite comp=new Composite(shell, SWT.NONE); // main menu composite

			comp.setLayout(new GridLayout(1,true)); // composite layout


			TopComposite topComposite=new TopComposite(comp,SWT.NONE); //in-game top panel composite


			addBtnActions(topComposite); // button actions for in game top panel
			GridData gdTop=new GridData(); // creating grid data for later use
			GridData gdBot=new GridData(GridData.FILL_BOTH);
			gdBot.grabExcessHorizontalSpace=true;
			gdBot.grabExcessVerticalSpace=true;


			topComposite.setLayoutData(gdTop); // in game top panel layout
			topComposite.pack();
			Canvas canvas = new Canvas(comp, SWT.DOUBLE_BUFFERED | SWT.BORDER); // drawing game canvas
			User user=null; // setting user
			Section section=null; // setting section
			String fileName=dg.getFileName(); // load game
			if(fileName!=null){

				Vector v = load(fileName); // get vector file
				if(v != null){
					user=(User)v.get(0);
					section=(Section)v.get(1);
				}
			}
			else{
				user=new User();
				user.setName(dg.getUserName());
				section=SectionCreater.createSection(user.getSectionNum()); // create section from save data
			}
			gamePlay.setTopComposite(topComposite); //initialize new top panel

			gamePlay.setUser(user); // initialize new user


			gamePlay.setSection(section); // initialize new section

			gamePlay.setCanvas(canvas); // initialize canvas

			gamePlay.printUser(); // print user info on top panel
			canvas.setLayoutData(gdBot);
			canvas.setBackground(display.getSystemColor(SWT.COLOR_WHITE));

			shell.pack();
			draw(canvas);
			shell.open();
			shell.setSize(1600, 900); // create game shell
			canvas.forceFocus(); //force focus on game
			//    		        shell.setFullScreen(true);
			shell.addDisposeListener(new DisposeListener() { // when shell closed , close the other sound thread

				@Override
				public void widgetDisposed(DisposeEvent e) {
					if(soundUtil!=null)
						soundUtil.stop();

				}
			});

			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();

				}
			}
		}

		else{
			System.exit(1);
		}



	}
	
	
	//---------------------------------------------------------------------------------------



	private static void addBtnActions(TopComposite topComposite) { // button actions for top panel

		topComposite.btnMusic.addSelectionListener(new SelectionAdapter() { //Music on/off button
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(soundUtil==null){
					soundUtil=new SoundUtil();
					soundUtil.start();
					topComposite.btnMusic.setText("Music Off");
				}
				else{
					if("Music On".equals(topComposite.btnMusic.getText())){
						soundUtil.resume();
						topComposite.btnMusic.setText("Music Off");
					}
					else if("Music Off".equals(topComposite.btnMusic.getText())){
						soundUtil.suspend();
						topComposite.btnMusic.setText("Music On");
					}
				}
				if(gamePlay.getSoundUtil()==null)
					gamePlay.setSoundUtil(soundUtil);
				gamePlay.getCanvas().forceFocus();
			}
		});

		topComposite.btnAdmin.addSelectionListener(new SelectionAdapter(){ // admin mode button for closing collision controller
			@Override
			public void widgetSelected(SelectionEvent e) {
				gamePlay.setTest(!gamePlay.isTest());
				gamePlay.getCanvas().forceFocus();
			}

		});

		topComposite.btnMenu.addSelectionListener(new SelectionAdapter() { // menu button
			public void widgetSelected(SelectionEvent e){
				menuAction();
			}
		});



	}
	protected static void menuAction() { // openning in-game menu
		MenuDialog dg=new MenuDialog(gamePlay.getShell());
		if(Window.OK==dg.open()){
			String action=dg.getAction();
			if("SAVE".equals(action)){
				FileDialog fd=new FileDialog(gamePlay.getShell());
				String fn=fd.open();
				if(fn!=null){
					try{

						FileUtil.saveVectorToFile(fn, gamePlay.getSaveData()); // save with vector object

						System.out.println("Done");

					}catch(Exception ex){
						ExceptionHandler.handleException(gamePlay.getShell(), ex);
						ex.printStackTrace();
					}
				}


			}
			else if("LOAD".equals(action)){
				load();

			}

		}

	}



	private static void load(){ // load from vector object
		FileDialog fd=new FileDialog(gamePlay.getShell());
		String fn=fd.open();
		if(fn!=null){

			Vector v=load(fn);
			if(v!=null){
				User u=(User)v.get(0);
				Section s=(Section)v.get(1);
				gamePlay.setUser(u);
				gamePlay.setSection(s);
				gamePlay.redraw();
				gamePlay.printUser();
			}
			System.out.println("Done");


		}
	}

	private static Vector load(String fileName){ //Getting vector from file for load
		Vector v = null;
		try{
			v = FileUtil.getVectorFromFile(fileName);
		}catch(Exception ex){
			ExceptionHandler.handleException(gamePlay.getShell(), ex);
			ex.printStackTrace();
		}
		return v;
	}

}

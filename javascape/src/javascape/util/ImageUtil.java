package javascape.util;

import java.io.InputStream;

import javascape.model.Room;
import javascape.model.Section;
import javascape.question.resourses.DummyQuestion;
import javascape.resourses.Dummy;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ImageUtil {
	
	public static final String COMPUTER_IMAGE="comp.jpg";
	public static final String HUMAN_IMAGE="human.jpg";
	
		public static Image getWall(Section section){
			
			InputStream is=new Dummy().getClass().getResourceAsStream(section.getWallImageName());
			
			
			return new Image(Display.getDefault(),is );
		}
		
		public static Image getDoor(Room room){
			InputStream is=new Dummy().getClass().getResourceAsStream(room.getDoorImageNameStr());
			return new Image(Display.getDefault(),is );
		}
		
		public static Image getComputer(Room room){
			InputStream is=new Dummy().getClass().getResourceAsStream(COMPUTER_IMAGE);
			return new Image(Display.getDefault(),is );
		}
		
		public static Image getHuman(){
			InputStream is=new Dummy().getClass().getResourceAsStream(HUMAN_IMAGE);
			return new Image(Display.getDefault(),is );
		}
		
		
		
		public static Image getQuestionImg(Room room){
			String fN="QUES_"+(room.getSectionNum())+"_"+(room.getId())+".jpg";
			//System.out.println("fN "+fN);
			InputStream is=new DummyQuestion().getClass().getResourceAsStream(fN);
			if(is==null)
				return null;
			return new Image(Display.getDefault(),is );
		}
}

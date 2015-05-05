package javascape.util;

import javascape.model.Question;
import javascape.model.Room;
import javascape.model.question.Question_1_1;
import javascape.model.question.Question_1_2;
import javascape.model.question.Question_1_3;
import javascape.model.question.Question_1_4;
import javascape.model.question.Question_2_1;
import javascape.model.question.Question_2_2;
import javascape.model.question.Question_2_3;
import javascape.model.question.Question_2_4;


public class QuestionUtil {


	
	public static Question getQuestion(Room room){
		if(room!=null){
			if(room.getSectionNum()==1 && room.getId()==1)
				return new Question_1_1();
			if(room.getSectionNum()==1 && room.getId()==2)
				return new Question_1_2();
			if(room.getSectionNum()==1 && room.getId()==3)
				return new Question_1_3();
			if(room.getSectionNum()==1 && room.getId()==4)
				return new Question_1_4();
			if(room.getSectionNum()==2 && room.getId()==1)
				return new Question_2_1();
			if(room.getSectionNum()==2 && room.getId()==2)
				return new Question_2_2();
			if(room.getSectionNum()==2 && room.getId()==3)
				return new Question_2_3();
			if(room.getSectionNum()==2 && room.getId()==4)
				return new Question_2_4();
			
		}
		return null;
	}

}

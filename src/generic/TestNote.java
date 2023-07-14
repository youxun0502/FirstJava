package generic;

import java.util.Arrays;
import java.util.List;

public class TestNote {
    public static void main(String[] args) {
        NoteString note1 = new NoteString("java gc");
        System.out.println(note1.getContent());

        NoteInt note2 = new NoteInt(1);
        System.out.println(note2.getContent());


        //TODO:使用generic 改善
    }

    /*public static double sumNote(Note<? extends Number>... notes){
        double sum = 0.0;
        for (Note<? extends Number> note : notes) {
            sum = note.getContent().doubleValue();
        }
        return sum / notes.length;
    }*/

}

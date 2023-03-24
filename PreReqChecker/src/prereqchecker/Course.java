//zixin.qu@rutgers.edu
package prereqchecker;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String studentID;
    private List<String> prevList;
    private List<String> nextList;


    public Course(String studentID) {
        this.studentID = studentID;
        prevList = new ArrayList<>();
        nextList = new ArrayList<>();
    }


    public void addPrev(String courseID) {
        prevList.add(courseID);
    }


    public void addNext(String courseID) {
        nextList.add(courseID);
    }

    public String getID() {
        return studentID;
    }

    public List<String> getNext() {
        return nextList;
    }

    public List<String> getPrev() {
        return prevList;
    }
}

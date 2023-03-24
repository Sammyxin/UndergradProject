//zixin.qu@rutgers.edu
package prereqchecker;

import java.util.*;

public class Graph {

    private List<Course> courList;
    private Map<String, Course> courMap;

    public Graph(String filename){
        StdIn.setFile(filename);
        this.courList = new ArrayList<>();
        this.courMap = new HashMap<>();

        //set initial and put file in
        Integer num1 = StdIn.readInt();
          for(int i=0; i<num1; i++){
            //add a string
            String ID = StdIn.readString();
            Course classidx = new Course(ID);
              this.courList.add(classidx);
              courMap.put(ID, classidx);
        }
        //set num2
        Integer num2 = StdIn.readInt();
          for(int i=0; i<num2; i++){
            String sourID = StdIn.readString();
            String goalID = StdIn.readString();
            Course courseidx = this.courMap.get(sourID);
            //if equal to null, then means continue to find
              if(courseidx == null){
                    continue;
            }
               courseidx.addPrev(goalID);
            Course goalcourse = this.courMap.get(goalID);
               if(goalcourse == null){
                   continue;
            }
               goalcourse.addNext(sourID);
        }
    }

    public void addCourseMap(String sourceId, String goalID) {
        Course sourCourse = this.courMap.get(sourceId);
           if(sourCourse == null){
                sourCourse = new Course(sourceId);
                   this.courList.add(sourCourse);
                   this.courMap.put(sourceId, sourCourse);
      }
          Course goalCourse = this.courMap.get(goalID);
              if(goalCourse == null){
                   goalCourse = new Course(goalID);
                   this.courList.add(goalCourse);
                   this.courMap.put(goalID, goalCourse);
        }
             sourCourse.addNext(goalID);
             goalCourse.addPrev(sourceId);
    }

    public List<Course> getList() {
        return courList;
    }

    public boolean validPrereq() {
        Set<String> existcour = new HashSet<>();
          for(Course course:this.courList){
               if(course.getPrev().size() <= 0){
                  existcour.add(course.getID());
        }
        }
            return validPrereq(existcour);
    }

    public boolean validPrereq(Set<String> existcour) {
        Set<String> newcour = new HashSet<>();
          for(Course courseidx:this.courList){
              if(existcour.contains(courseidx.getID())){
                 continue;
            }
            boolean fullfill = true;
               for(String piv : courseidx.getPrev()){
                  if(!existcour.contains(piv)) {
                      fullfill = false;
                      break;
                }
            }
             if(fullfill){
                newcour.add(courseidx.getID());

            }
        }
        //compare the size of new courses
        if(newcour.size()>0){
            existcour.addAll(newcour);
            return validPrereq(existcour);
        }else{
            return existcour.size() >= this.courList.size();
        }
    }

    public void setAll(Set<String> hadtook){
        Set<String> newertake = new HashSet<>();
        //set for loop
        for(String courID : hadtook){
            Course courseidx = this.courMap.get(courID);
            if(courseidx == null){
                continue;
            }
            //if piv compare
            for(String piv:courseidx.getPrev()){
                if (hadtook.contains(piv)){
                    continue;
                }
                newertake.add(piv);
            }
        }
        hadtook.addAll(newertake);
        if(newertake.size() > 0){
            setAll(hadtook);
        }
    }

    public Course getbyID(String goalID){
        return this.courMap.get(goalID);
    }
}

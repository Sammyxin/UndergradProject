package prereqchecker;

import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 * zixin.qu@rutgers.edu
 */
public class SchedulePlan {

    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }
    

	// WRITE YOUR CODE HERE
    Graph graph = new Graph(args[0]);
    StdIn.setFile(args[1]);
    //set a  string and int
      String goalID = StdIn.readString();
      Integer num3 = StdIn.readInt();
      Set<String> took = new HashSet<>();
       for (int i = 0; i < num3; i++) {
        took.add(StdIn.readString());
    }

    graph.setAll(took);
         Set<String> requiretake = new HashSet<>();
    requiretake.add(goalID);
    graph.setAll(requiretake);
         Set<String> tempNeedToTaken = new HashSet<>();
       for(String str : requiretake){
           if(took.contains(str)){
              continue;
         }
         tempNeedToTaken.add(str);
    }
     requiretake=tempNeedToTaken;
        int index = 0;
        List<List<String>> resour = new ArrayList<>();
        //comparing with size, if size bigger than 0/ smaller and equal to 0
           while (requiretake.size() > 0) {
              List<String> list = new ArrayList<>();
              for (String piv : requiretake) {
                   Course courseidx = graph.getbyID(piv);
                   boolean cantakecour = true;
              for (String courseID : courseidx.getPrev()) {
                 if (!took.contains(courseID)) {
                        cantakecour = false;
                       break;
          }
            }
            if(cantakecour){
                list.add(piv);
            }
        }


        if(list.size()<=0){
            break;
        }

        for(String piv:list){
            took.add(piv);
            requiretake.remove(piv);
        }
        resour.add(list);
        index++;
    }
    //begain with set and print
    StdOut.setFile(args[2]);
    StdOut.println(index - 1);
    //beagin
    for(int i = 0; i < resour.size() - 1; i++){
        List<String> list=resour.get(i);
        StringBuilder builder = new StringBuilder();

        for(String piv : list){
            builder.append(piv).append(" ");
        }

        if(builder.length()>0){
            builder.deleteCharAt(builder.length() - 1);
        }

        StdOut.println(builder.toString());

    }
}
}

package prereqchecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 * zixin.qu@rutgers.edu
 */
public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
    //put filein and file out
 
    Graph graph = new Graph(args[0]);
    List<Course> courseList = graph.getList();
    StdOut.setFile(args[1]);  

    for(Course course:courseList){
        StringBuilder number = new StringBuilder();
        number.append(course.getID());

        for(String piv : course.getPrev()){
        number.append(" ").append(piv);
        }
          StdOut.println(number);
    }
}
}



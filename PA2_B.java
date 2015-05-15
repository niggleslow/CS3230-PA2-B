import java.util.*;
import java.lang.*;
import java.io.*;

/*
@author Nicholas Low A0110574N

Programming Assignment 2-B CS3230
*/
public class PA2_B {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out))); 
        
        int T,N,M; //T = number of testcases, N = number of vertices in graph, M = number of edges
		String U, V, H1, T1, H2, T2; //U is first Vertex in edge, V is second Vertex in edge
		String answer, suffix1, suffix2, temp1, temp2;
		Pair temp;
		boolean ordering;
		ArrayList<String> connectedVertices;
		HashMap<String, ArrayList<String>> edgeList;
		ArrayList<Pair> edges;

        T = sc.nextInt();

        answer = "";

        for(int a = 1; a <= T; a++) { //iterating through each testcase
        	N = sc.nextInt();
        	M = sc.nextInt();
        	edgeList = new HashMap<String, ArrayList<String>>();
        	edges = new ArrayList<Pair>();
        	answer += N*3 + " " + (N*2 + M) + "\n";

        	for(int b = 0; b < M; b++) { //iterating through all the edges in testcase
        		U = sc.next(); //first vertex in edge
        		V = sc.next(); //second vertex in edge
        		
        		if(U.compareTo(V) < 0) { //checking lexicographical order for convenience later
        			ordering = true;
        		} else {
        			ordering = false;
        		}
        		//System.out.println(ordering);

        		//process of transforming directed edge into undirected starts here
        		
        		suffix1 = suffixExtractor(U);
        		suffix2 = suffixExtractor(V);
        		H1 = "H" + suffix1;
        		H2 = "H" + suffix2;
        		T1 = "T" + suffix1;
        		T2 = "T" + suffix2;

        		//2 cases here: U comes before V or otherwise (lexicographically)
        		if(ordering) { //U comes before V

        			if(!edgeList.containsKey(H1)) {
        				connectedVertices = new ArrayList<String>();
        				connectedVertices.add(U);
        				edgeList.put(H1, connectedVertices);
        				edges.add(new Pair(H1, U));
        			}
        			edgeList.get(H1).add(T2); //adding edge between U and V
        			edges.add(new Pair(H1, T2));
        			if(!edgeList.containsKey(T1)) {
        				connectedVertices = new ArrayList<String>();
        				connectedVertices.add(U);
        				edgeList.put(T1, connectedVertices);
        				edges.add(new Pair(T1, U));
        			}
        			if(!edgeList.containsKey(H2)) {
        				connectedVertices = new ArrayList<String>();
        				connectedVertices.add(V);
        				edgeList.put(H2, connectedVertices);
        				edges.add(new Pair(H2, V));
        			}
        			if(!edgeList.containsKey(T2)) {
        				connectedVertices = new ArrayList<String>();
        				connectedVertices.add(V);
        				edgeList.put(T2, connectedVertices);
        				edges.add(new Pair(T2, V));
        			}

        		} else { //V comes before U

        			if(!edgeList.containsKey(H2)) {
        				connectedVertices = new ArrayList<String>();
        				connectedVertices.add(V);
        				edgeList.put(H2, connectedVertices);
        				edges.add(new Pair(H2, V));
        			}
        			if(!edgeList.containsKey(H1)) {
        				connectedVertices = new ArrayList<String>();
        				connectedVertices.add(U);
        				edgeList.put(H1, connectedVertices);
        				edges.add(new Pair(H1, U));
        			}
        			edgeList.get(H1).add(T2);
        			edges.add(new Pair(H1, T2));
        			if(!edgeList.containsKey(T2)) {
        				connectedVertices = new ArrayList<String>();
        				connectedVertices.add(V);
        				edgeList.put(T2, connectedVertices);
        				edges.add(new Pair(T2, V));
        			}
        			if(!edgeList.containsKey(T1)) {
        				connectedVertices = new ArrayList<String>();
        				connectedVertices.add(U);
        				edgeList.put(T1, connectedVertices);
        				edges.add(new Pair(T1, U));
        			}
        		}
        	}
        	/*
        	for(Map.Entry<String, ArrayList<String>> entry : edgeList.entrySet()) {
   				String key = entry.getKey();
   				ArrayList<String> value = entry.getValue();
   				Collections.sort(value);
   				for(int count = 0; count < value.size(); count++) {
   					answer += key + " " + value.get(count) + "\n";
   				}
			}*/
			Collections.sort(edges);
			for(int count = 0; count < edges.size(); count++) {
				answer += edges.get(count).getFirst() + " " + edges.get(count).getSecond() + "\n";
			}
        }
        pw.write(answer);
        pw.close();
        long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
    }

	//------------------------------- Auxillary Methods --------------------------------//
    public static String suffixExtractor(String vertex) {
    	return vertex.substring(1);
    }
}

class Pair implements Comparable<Pair> {
	String first;
	String second;

	public Pair(String f, String s) {
		first = f;
		second = s;
	}

	public String getFirst() {
		return first;
	}

	public String getSecond() {
		return second;
	}

	public void setFirst(String f) {
		first = f;
	}

	public void setSecond(String s) {
		second = s;
	}

	public String toString() {
		String output = "";
		output += "First Vertex: " + first + " Second Vertex: " + second;
		return output;
	}

	@Override
	public int compareTo(Pair o) {
		if(first.compareTo(o.first) == 0) {
			return second.compareTo(o.second);
		} else {
			return first.compareTo(o.first);
		}
	}
}
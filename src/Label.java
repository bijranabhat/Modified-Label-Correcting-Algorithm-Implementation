/* McKenzie Allaben and Bijay Ranabhat
 * Algorithmic Graph Theory 
 * Shortest path algorithms programming assignment
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;	

public class Label
{
	public static void label(Graph G, int source) throws IOException //int source = index of source vertex
    {                  					
    	int numVertices; 
    	int [] dist;
    	int [] predecessor;
    	ArrayList<Integer>I; //holds vertex that is acting as the "source node"
    	ArrayList<Integer>LIST;
    	ArrayList<Integer>A; //holds set of neighboring edges/vertices
    	
    	int adjMatrix[][] = G.getAdj_Matrix(); //gets adjacency matrix of Cij values
        numVertices = G.numberOfVertices(); // gets number of vertices of graph 
        
        dist = new int[numVertices];       
        predecessor = new int[numVertices];
        
        I = new ArrayList<Integer>();
        LIST = new ArrayList<Integer>();
        A = new ArrayList<Integer>();
        
        LIST.add(0); //adds initial source (index zero) node to LIST
        
        //establishes adjacent vertices list 
        for(int j = 0; j < numVertices; j++)
        {
        	if(adjMatrix[0][j] != 999)
        	{
        		A.add(j); //technically j+1 but for index reasons need to keep as j
        	}
        }

        //System.out.println("A Test:" + A);

    	System.out.println("Modified Label Correcting Algorithm");
    	System.out.println("\n");

        System.out.println("Initialization:");
        System.out.println("LIST=" + (LIST.get(0)) + " " + "a.k.a. Source");
        System.out.println("----------------------------------");
        
        
        //prints graph vertices
        System.out.print("V:");
        for(int i=0; i < numVertices; i++)
        {
        	System.out.print((i) + " ");
        }
        System.out.println("\n");
        
        
        
        //distance array initialization 
        for(int n = 0; n < numVertices; n++) 			
        {
        	dist[n]=999; 		// same as infinity in code		
        }
       
        //distance from a source node to itself = zero
        dist[source] = 0;

      //prints distances
        System.out.print("D:");
        for(int i=0; i < dist.length; i++)
        {
        	System.out.print(dist[i] + " ");
        }
        
        System.out.println("\n");
        
        
       
        //predecessor array initialization 
        for(int n = 0; n < numVertices; n++) 			
        {
        	predecessor[n] = 0; //all predecessors initialized to 0 
        }
        
        //predecessor from a source node to itself = zero
        predecessor[source] = 0; 
        
        //prints predecessors 
        System.out.print("P:");
        for(int i=0; i < predecessor.length; i++)
        {
        	System.out.print(predecessor[i] + " ");
        }
        
        System.out.println("\n");

        System.out.println("----------------------------------");
        System.out.println("\n");
        
        
        
        
        //starts actual MLC Algorithm 
        
        int iteration = 1;
        I.add(8); //adds arbitrary place holder for single cell
        //System.out.println("LIST_initial:" + LIST);
        //System.out.println("A_inital:" + A);
        //System.out.println("\n");
        
        
        I.set(0, LIST.get(0));
        
        while(!LIST.isEmpty())
        {
        	LIST.remove(0);
        	
        	for(int j = 0; j < numVertices; j++)
        	{
        		if(A.contains(j))
        		{
        			//System.out.println("Found"+ " " + j + " " + "in A");
        			
        			if(adjMatrix[I.get(0)][j] != 999)
        			{
        				//System.out.println("SET ROW:" + I.get(0));
        				//System.out.println("COL:" + j);
        				
        				if( dist[j] > dist[I.get(0)] + adjMatrix[I.get(0)][j]) 
        				{
        					dist[j] = dist[I.get(0)] + adjMatrix[I.get(0)][j];
        					//System.out.println("dist[j]:" + dist[j]);
        					predecessor[j] = I.get(0);
        					//System.out.println("Predecessor[j]:" + predecessor[j]);
        					if(!LIST.contains(j))
        					{
        						LIST.add(j);        						
        					}
        				}
        			}            		
        		}
        		
    			
				
        	}
        	System.out.println("Iteration:" + iteration);
        	System.out.println("i =" + I.get(0));
        	System.out.println("A(" + (I.get(0)) + ") =" + A);
            System.out.println("----------------------------------");
   
            System.out.print("V:");
            for(int m = 0; m < numVertices; m++)
    		{
    			System.out.print((m) + " ");
    		}
    		System.out.println("\n");
        	
    		System.out.print("D:");
    		for(int m = 0; m < numVertices; m++)
    		{
    			System.out.print(dist[m] + " ");
    		}
    		System.out.println("\n");
    		
    		System.out.print("P:");
    		for(int m = 0; m < numVertices; m++)
    		{
    			System.out.print(predecessor[m] + " ");
    		}
    		System.out.println("\n");
    		System.out.println("LIST_UPDATED =" + LIST);
    	
    		
    		System.out.println("\n");
    		System.out.println("\n");
    	
            
    		iteration++;
        	//System.out.println("LIST_Updated:" + LIST);
        	if(LIST.isEmpty())
        	{
        		System.out.println("Optimum Solution is listed in last interation");
        		break;
        	}
        	I.set(0, LIST.get(0));
        	//System.out.println("NEW I:" + I);
        	A.clear();
    		//System.out.println("A_cleared:" + A);
    		for(int n = 0; n < numVertices; n++)
			{
    			if(adjMatrix[I.get(0)][n] != 999)
    			{
    				A.add(n);
    			}
			}
			
	     }

	}


   public static void main(String[] args) throws IOException //main execution method/runs the entire program
   {
	   //creates a graph object G using Network2.txt adjacency matrix that was read into the program 
       Graph G = new Graph("Network2.txt");
       G.printMatrix(); //prints adj. matrix
       
       // calls MLCA algorithm on graph G, with the source vertex starting at index 0
       label(G,0); //index of source vertex is zero 
    } 
   

   
   
}

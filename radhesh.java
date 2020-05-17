import java.util.*;
import java.io.*;
import java.util.ArrayList;
public class radhesh{
    static Graph graph;
    static int root = -1;
    

    public static void dfs(int source,int visited[]){
        visited[source] = visited[source]+1;
        if(visited[source] == 2){
            root = source;
            return;
        }
 
        for(Integer nodes: graph.adjlist[source]){
            dfs(nodes,visited);
        }
    }

    static void checkallkeys(int set[],int numberofattribute) 
    { 
        int n = set.length; 
    

        for (int i = 0; i < (1<<n); i++) 
        { 

            ArrayList<Integer> cars = new ArrayList<Integer>();
            
            
            for (int j = 0; j < n; j++) 
            {
                if ((i & (1 << j)) > 0)
                {
                    cars.add(set[j]);
                } 
            }

            int cars2[] = new int[cars.size()];
            int k = 0;

            for(int j: cars2)
            {
                cars2[k++] = cars.get(j);
            }

            if(iskey(numberofattribute,cars2))
            {
                System.out.print("{ "); 
                for(int j : cars)
                {
                    System.out.print(j + "");
                }
                
                System.out.println("} is a candidate key"); 
            }
            
        }


    } 
  
    public static boolean iskey(int n,int key[])
    {
        // this array key contains the vector of attributes which is an integer from 0 to n - 1
        int visited[] = new int[n+1];
        // we assume there that are n attributes 

        for(int i=0;i<key.length;i++)
        {   
           // System.out.println(key[i]);
            dfs(key[i],visited);
        }   

        int countvis = 0;


        for(int i = 1; i <= n; i++)
        {
            if(visited[i] > 0)countvis++;
        }

        if(countvis == n)return true;
        return false;

    }
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(); // number of nodes = number of attributes
        int m = s.nextInt(); // number of edges = number of atomic FDs

        graph = new Graph(n);
        for(int i=0;i<m;i++)
        {
            int x = s.nextInt();
            int y = s.nextInt();
            // edge b/w x and y exists if x determines y
            graph.adjlist[x].add(y);
        }


        int key[] = {1,2,3};
        checkallkeys(key,n);

        


 
    }
}
 
@SuppressWarnings("unchecked")
class Graph{
    int nodes;
    LinkedList<Integer> adjlist[];
    Graph(int nodes){
        this.nodes = nodes;
        adjlist = new LinkedList[nodes+1];
        for(int i=1;i<=nodes;i++){
            adjlist[i] = new LinkedList<Integer>();
        }
    }
}
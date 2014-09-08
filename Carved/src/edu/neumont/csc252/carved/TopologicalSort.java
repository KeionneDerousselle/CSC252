package edu.neumont.csc252.carved;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by kderousselle on 9/2/14.
 */
public class TopologicalSort
{
   public  List<Integer> sort(Graph g)
   {
       List<Integer> topologicalOrder = new ArrayList<Integer>();
       PriorityQueue<TopoVertex> vertices = new PriorityQueue<TopoVertex>();
       int[] incomingEdgeCounts = g.getIncomingEdgeCountPerVertex();

      for(int i = 0; i < g.getVertexCount(); i++)
      {
          vertices.offer(new TopoVertex(i, incomingEdgeCounts[i]));
      }



      while(!vertices.isEmpty())
      {
          TopoVertex v = vertices.poll();
          if(v.getEdgeCount() != 0)
          {
            throw new GraphNotDAGException();
          }
          topologicalOrder.add(v.getKey());

          for(int neighbor = g.first(v.getKey()); neighbor < g.getVertexCount(); neighbor = g.next(v.getKey(), neighbor))
          {
              vertices.remove(new TopoVertex(neighbor, incomingEdgeCounts[neighbor]));
              incomingEdgeCounts[neighbor] = incomingEdgeCounts[neighbor] - 1;
              vertices.add(new TopoVertex(neighbor, incomingEdgeCounts[neighbor]));
          }

      }


       return topologicalOrder;
   }
}

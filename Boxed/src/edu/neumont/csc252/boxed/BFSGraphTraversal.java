package edu.neumont.csc252.boxed;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by kderousselle on 8/25/14.
 */
public class BFSGraphTraversal implements IGraphTraversal {

    @Override
    public List<List<Integer>> traverse(Graph g)
    {
        List<List<Integer>> traversalList = new ArrayList<List<Integer>>();

        for(int i = 0; i < g.verticeCount(); i++)
        {
            if(g.getMark(i) == 0)
            {
                traversalList.add(traverseConnectedComponent(g, i));
            }
        }
        for(int i = 0; i < g.verticeCount(); i++)
        {
            g.setMark(i, 0);
        }


        return traversalList;
    }

    private List<Integer> traverseConnectedComponent(Graph g, int connectedComponent)
    {
        List<Integer> traversalList = new ArrayList<Integer>();

        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(connectedComponent);
        g.setMark(connectedComponent, 1);

        while(!queue.isEmpty())
        {
            int vertex = queue.poll();
            traversalList.add(vertex);

            int nextNeighborIndex = g.first(vertex);

            while(nextNeighborIndex != g.verticeCount())
            {
                if(g.getMark(nextNeighborIndex) == 0)
                {
                    queue.add(nextNeighborIndex);
                    g.setMark(nextNeighborIndex, 1);
                }

                nextNeighborIndex = g.next(vertex, nextNeighborIndex);
            }
        }

        return traversalList;
    }
}

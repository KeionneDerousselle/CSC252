package edu.neumont.csc252.boxed;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by kderousselle on 8/25/14.
 */
public class DFSGraphTraversal implements IGraphTraversal {
    @Override
    public List<List<Integer>> traverse(Graph g) {
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

        Stack<Integer> stack = new Stack<Integer>();

        DFS(g, connectedComponent, stack, traversalList);

        return traversalList;
    }

    private void DFS(Graph g, int vertex, Stack<Integer> stack, List<Integer> traversalList)
    {
        g.setMark(vertex, 1);

        stack.push(vertex);

        for(int nextNeighborIndex = g.first(vertex); nextNeighborIndex != g.verticeCount(); nextNeighborIndex = g.next(vertex, nextNeighborIndex))
        {
            if(g.getMark(nextNeighborIndex) == 0)
            {
                DFS(g, nextNeighborIndex, stack, traversalList);
            }
        }

        traversalList.add(stack.pop());

    }
}

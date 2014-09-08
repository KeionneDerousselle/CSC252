package edu.neumont.csc252.carved;

import org.junit.Test;

/**
 * Created by kderousselle on 9/2/14.
 */
public class TopologicalSortTest
{

    @Test
    public void testDAGSort() throws Exception
    {
        Graph g = new Graph(7);

        g.addEdge(0,1,1);
        g.addEdge(1,4,1);
        g.addEdge(1,6,1);
        g.addEdge(6,4,1);
        g.addEdge(6,5,1);
        g.addEdge(2,5,1);
        g.addEdge(0,2,1);

        g.addEdge(3,0,1);
        g.addEdge(3,1,1);
        g.addEdge(3,6,1);
        g.addEdge(3,5,1);
        g.addEdge(3,2,1);

        System.out.println(new TopologicalSort().sort(g));
    }

    @Test(expected = GraphNotDAGException.class)
    public void testNonDAGSort() throws Exception
    {
        Graph g = new Graph(7);

        g.addEdge(0,1,1);
        g.addEdge(1,2,1);
        g.addEdge(2,3,1);
        g.addEdge(3,6,1);
        g.addEdge(6,4,1);
        g.addEdge(4,0,1);

        g.addEdge(5,1,1);
        g.addEdge(5,2,1);
        g.addEdge(5,6,1);
        g.addEdge(5,4,1);

        System.out.println(new TopologicalSort().sort(g));
    }
}

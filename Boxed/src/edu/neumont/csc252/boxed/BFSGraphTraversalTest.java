package edu.neumont.csc252.boxed;

import org.junit.Test;

/**
 * Created by kderousselle on 8/25/14.
 */
public class BFSGraphTraversalTest {

    @Test
    public void testTraverse() throws Exception
    {

        Graph g = new Graph(10);

        g.addEdge(0,2,1);
        g.addEdge(0,3, 1);
        g.addEdge(0, 4, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(2, 5, 1);
        g.addEdge(4, 5, 1);
        g.addEdge(1, 5, 1);
        g.addEdge(1, 4, 1);

        g.addEdge(6, 7, 1);
        g.addEdge(6, 9, 1);
        g.addEdge(7, 8, 1);
        g.addEdge(8, 9, 1);



        System.out.println(new BFSGraphTraversal().traverse(g));

    }
}

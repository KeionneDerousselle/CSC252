package edu.neumont.csc252.carved;

/**
 * Created by kderousselle on 9/2/14.
 */
public class GraphNotDAGException extends RuntimeException
{
    @Override
    public String getMessage()
    {
        return "The graph entered was not a directed acyclic graph.";
    }

}

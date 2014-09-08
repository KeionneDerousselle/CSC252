package edu.neumont.csc252.carved;

/**
 * Created by kderousselle on 9/2/14.
 */
public class Graph
{
    private int[][] matrix;
    private int[] marks;
    private int[] incomingEdgeCounts;
    private  int[] departingEdgeCounts;
    private int numOfVertices;

    public Graph(int numOfVertices)
    {
        this.numOfVertices = numOfVertices;

        this.matrix = new int[numOfVertices][numOfVertices];
        this.marks = new int[numOfVertices];
        this.incomingEdgeCounts = new int[numOfVertices];
        this.departingEdgeCounts = new int[numOfVertices];

    }

    public int getVertexCount()
    {
        return this.numOfVertices;
    }

    public int edgeCount()
    {
        int eCount = 0;

        for(int i = 0; i < getVertexCount(); i++)
        {
            for(int j = i; j < getVertexCount(); j++)
            {
                if(this.matrix[i][j] != 0)
                {
                    eCount++;
                }
            }
        }
        return eCount;
    }

    public int first(int vertex)
    {
        for( int i = 0; i< getVertexCount(); i++)
        {
            if(matrix[vertex][i] !=0)
            {
                return i;
            }
        }
        return getVertexCount();
    }

    public int next(int vertex, int neighbor)
    {
        for(int i = neighbor + 1; i< getVertexCount(); i++)
        {
            if(matrix[vertex][i] != 0)
            {
                return i;
            }
        }

        return getVertexCount();
    }

    public void addEdge(int fromVertex, int toVertex, int weight)
    {
        this.matrix[fromVertex][toVertex] = weight;

        this.departingEdgeCounts[fromVertex] = this.departingEdgeCounts[fromVertex] + 1;
        this.incomingEdgeCounts[toVertex] = incomingEdgeCounts[toVertex] + 1;
    }

    public void removeEdge(int fromVertex, int toVertex)
    {
        this.matrix[fromVertex][toVertex] = 0;

        this.departingEdgeCounts[fromVertex] = this.departingEdgeCounts[fromVertex] - 1;
        this.incomingEdgeCounts[toVertex] = incomingEdgeCounts[toVertex] - 1;
    }

    public boolean isEdge(int fromVertex, int toVertex)
    {
        return this.matrix[fromVertex][toVertex] != 0;
    }

    public int degree(int vertex)
    {
        return departingEdgeCounts[vertex];
    }

    public int incomingEdgeCount(int vertex)
    {
        return incomingEdgeCounts[vertex];
    }

    public int getMark(int vertex)
    {
        return this.marks[vertex];
    }

    public void setMark(int vertex, int mark)
    {
        this.marks[vertex] = mark;
    }

    public int[] getIncomingEdgeCountPerVertex()
    {
        return incomingEdgeCounts.clone();
    }
}

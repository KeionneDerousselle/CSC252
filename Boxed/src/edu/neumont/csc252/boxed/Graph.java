package edu.neumont.csc252.boxed;

/**
 * Created by kderousselle on 8/22/14.
 */
public class Graph
{
    private int[][] matrix;
    private int [] marks;

    private int numOfVertices;

    public Graph(int numOfVertices)
    {
        this.matrix = new int[numOfVertices][numOfVertices];
        this.marks = new int[numOfVertices];

        this.numOfVertices = numOfVertices;
    }

    public int verticeCount()
    {
        return this.numOfVertices;
    }

    public void edgeCount()
    {

    }

    public int first(int vertex)
    {
        for( int i = 0; i< verticeCount(); i++)
        {
            if(matrix[vertex][i] !=0)
            {
                return i;
            }
        }
        return verticeCount();
    }

    public int next(int vertex, int neighbor)
    {
        for(int i = neighbor + 1; i< verticeCount(); i++)
        {
            if(matrix[vertex][i] != 0)
            {
                return i;
            }
        }

        return verticeCount();
    }

    public void addEdge(int fromVertex, int toVertex, int weight)
    {
        this.matrix[fromVertex][toVertex] = weight;
        this.matrix[toVertex][fromVertex] = weight;
    }

    public void removeEdge(int fromVertex, int toVertex)
    {
        this.matrix[fromVertex][toVertex] = 0;
        this.matrix[toVertex][fromVertex] = 0;
    }

    public boolean isEdge(int fromVertex, int toVertex)
    {
        return this.matrix[fromVertex][toVertex] != 0 && this.matrix[toVertex][fromVertex] != 0;
    }

    public int degree(int vertex)
    {
        int edgeCount = 0;
        for(int i =0; i< verticeCount(); i++)
        {
            if(this.matrix[vertex][i] != 0)
            {
                edgeCount++;
            }
        }

        return edgeCount;
    }

    public int getMark(int vertex)
    {
        return this.marks[vertex];
    }

    public void setMark(int vertex, int mark)
    {
        this.marks[vertex] = mark;
    }

}

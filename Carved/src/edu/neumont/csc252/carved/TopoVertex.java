package edu.neumont.csc252.carved;

/**
 * Created by kderousselle on 9/2/14.
 */
public class TopoVertex implements Comparable<TopoVertex>
{

    private int key, edgeCount;

    public TopoVertex(int key, int edgeCount)
    {
        this.key = key;
        this.edgeCount = edgeCount;
    }

    @Override
    public int compareTo(TopoVertex o) {
        int compare = 0;

        if(this.edgeCount < o.getEdgeCount())
        {
            compare = -1;
        }
        else if(this.edgeCount > o.getEdgeCount())
        {
            compare = 1;
        }
        return compare;

    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public int getKey() {
        return key;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean equals = false;

        if(obj instanceof TopoVertex)
        {
            TopoVertex other = (TopoVertex)obj;

            if(this.key == other.getKey())
            {
                if(this.edgeCount == other.getEdgeCount())
                {
                    equals = true;
                }
            }
        }
        return equals;
    }
}

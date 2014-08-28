package edu.neumont.csc252.boxed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kderousselle on 8/26/14.
 */
public class DotsAndBoxes
{
    private Graph coins;
    private int coinsRows, coinsColumns;
    private int rows, columns;

    private HashMap<Integer, Integer> playerScores = new HashMap<Integer, Integer>();

    public DotsAndBoxes(int rows, int columns)
    {
        this.rows = rows;
        this.columns =columns;

        this.coins = new Graph((rows + 1) * (columns + 1));
        this.coinsRows = rows +1;
        this.coinsColumns = columns +1;

        addStringsToCoins();

    }

    private void addStringsToCoins()
    {
        for(int i = 0; i < coins.verticeCount(); i++)
        {
            Point p = graphVertexToPoint(i);

            Point north = new Point(p.getX(), p.getY()-1);
            Point south = new Point(p.getX(), p.getY()+1);
            Point west = new Point(p.getX() - 1, p.getY());
            Point east = new Point(p.getX() + 1, p.getY());

            if(pointWithinGraphBounds(north) && (p.getX() > 0 && p.getX() < coinsRows -1))
            {
                int nVertex = graphPointToVertex(north);
                coins.addEdge(i, nVertex, 1);
            }

            if(pointWithinGraphBounds(south)  && (p.getX() > 0 && p.getX() < coinsRows -1))
            {
                int sVertex = graphPointToVertex(south);
                coins.addEdge(i, sVertex, 1);
            }

            if(pointWithinGraphBounds(west)  && (p.getY() > 0 && p.getY() < coinsColumns -1))
            {
                int wVertex = graphPointToVertex(west);
                coins.addEdge(i, wVertex, 1);
            }

            if(pointWithinGraphBounds(east)  && (p.getY() > 0 && p.getY() < coinsColumns -1))
            {
                int eVertex = graphPointToVertex(east);
                coins.addEdge(i, eVertex, 1);
            }

        }
    }

    public int drawLine(int player, int x1, int y1, int x2, int y2)
    {
        Point maxPoint = null;
        Point connectingPoint = null;

        int maxPointVertex = 0;
        int connectingPointVertex = 0;

        int score = 0;

        if(lineIsHorizontal(x1, y1, x2, y2))
        {
            maxPoint = findPointWithMaxX(x1, y1, x2, y2);
            maxPointVertex = graphPointToVertex(maxPoint);

            connectingPoint = new Point(maxPoint.getX(), maxPoint.getY()+1);
            connectingPointVertex = graphPointToVertex(connectingPoint);
        }
        else if(lineIsVertical(x1, y1, x2, y2))
        {
            maxPoint = findPointWithMaxY(x1, y1, x2, y2);
            maxPointVertex = graphPointToVertex(maxPoint);

            connectingPoint = new Point(maxPoint.getX() +1, maxPoint.getY());
            connectingPointVertex = graphPointToVertex(connectingPoint);
        }

        coins.removeEdge(maxPointVertex, connectingPointVertex);

        //if vertices are not edges and all strings are cut
        score = (coins.first(maxPointVertex) == coins.verticeCount()) && (graphPointNotOnBorder(maxPoint)) ? score + 1 : score;
        score = (coins.first(connectingPointVertex) == coins.verticeCount()) && (graphPointNotOnBorder(connectingPoint)) ?  score+1 : score;

        playerScores.put(player, playerScores.containsKey(player) ? playerScores.get(player) + score : score);

        return score;
    }

    private boolean graphPointNotOnBorder(Point p)
    {
        return (p.getX() > 0 && p.getX() < coinsRows-1) && (p.getY() > 0 && p.getY() < coinsColumns -1);
    }

    public int score(int player)
    {
        return playerScores.get(player);
    }

    public boolean areMovesLeft()
    {
        boolean movesLeft = false;

        for( int i = 0; i< coins.verticeCount() && !movesLeft; i++)
        {
            movesLeft = coins.first(i) != coins.verticeCount();
        }

        return movesLeft;
    }

    private boolean pointWithinGraphBounds(Point p)
    {
        return (p.getX() > -1 && p.getX()< coinsRows) && (p.getY() > -1 && p.getY() < coinsColumns);
    }

    private Point findPointWithMaxX(int x1, int y1, int x2, int y2)
    {
        return (x1 > x2)? new Point(x1, y1) : new Point(x2, y2);
    }

    private Point findPointWithMaxY(int x1, int y1, int x2, int y2)
    {
        return (y1 > y2)? new Point(x1, y1) : new Point(x2, y2);
    }

    private boolean lineIsHorizontal(int x1, int y1, int x2, int y2)
    {
        return (y1 == y2) && (Math.abs(x1-x2) == 1);
    }

    private boolean lineIsVertical(int x1, int y1, int x2, int y2)
    {
        return (x1 == x2) && (Math.abs(y1-y2) == 1);
    }

    private Point graphVertexToPoint(int vertex)
    {
        return new Point(vertex % coinsRows, vertex / coinsColumns);
    }

    private int graphPointToVertex(Point p)
    {
        return (p.getY() * coinsRows) + p.getX();
    }

    public int countDoubleCrosses()
    {
        int doubleCrossCount = 0;
        List<List<Integer>> bfsTraversal = new BFSGraphTraversal().traverse(coins);

        for(int connectedComponent = 0;  connectedComponent < bfsTraversal.size(); connectedComponent++)
        {
            doubleCrossCount = (bfsTraversal.get(connectedComponent).size() == 2)? doubleCrossCount +1 : doubleCrossCount;
        }

        return doubleCrossCount;
    }

    public int countCycles()
    {
        int cycleCount = 0;

        List<List<Integer>> dfsTraversal = new DFSGraphTraversal().traverse(coins);

        for(int connectedComponent = 0;  connectedComponent < dfsTraversal.size(); connectedComponent++)
        {
            if(dfsTraversal.get(connectedComponent).size() >= 4 && dfsTraversal.get(connectedComponent).size() %2 == 0)
            {
                if(coins.isEdge(dfsTraversal.get(connectedComponent).get(0), dfsTraversal.get(connectedComponent).get(dfsTraversal.get(connectedComponent).size() -1)))
                //if last vertex is a neighbor of first vertex
                {
                    boolean isCycle = true;

                    for(int i = 0; i< dfsTraversal.get(connectedComponent).size() && isCycle; i++)
                    {
                        isCycle = coins.degree(dfsTraversal.get(connectedComponent).get(i)) == 2;
                    }

                    cycleCount = isCycle? cycleCount +1 : cycleCount;
                }
            }
        }

        return cycleCount;
    }

    public int countChains()
    {
        int chainCount = 0;

        List<List<Integer>> dfsTraversal = new DFSGraphTraversal().traverse(coins);

        for(int connectedComponent = 0;  connectedComponent < dfsTraversal.size(); connectedComponent++)
        {
            if(dfsTraversal.get(connectedComponent).size() > 2 && ! (coins.isEdge(dfsTraversal.get(connectedComponent).get(0), dfsTraversal.get(connectedComponent).get(dfsTraversal.get(connectedComponent).size() -1))))
            {
                ArrayList<Integer> potentialChainVertices = new ArrayList<Integer>();

                for(int i = 0; i< dfsTraversal.get(connectedComponent).size(); i++)
                {
                    int currentVertex = dfsTraversal.get(connectedComponent).get(i);
                     if(coins.degree(currentVertex) == 2)
                     {
                         potentialChainVertices.add(currentVertex);
                     }
                }

                int chainLength = 0;
                int previousVertex = potentialChainVertices.get(0);

                for(int i = 1; i< potentialChainVertices.size(); i++)
                {
                    int currentVertex = potentialChainVertices.get(i);
                    if(coins.isEdge(previousVertex, currentVertex))
                    {
                        chainLength++;

                        if(chainLength ==2)
                        {
                            chainCount++;
                        }
                    }
                    else
                    {
                        chainLength = 0;
                    }

                    previousVertex = currentVertex;
                }
            }
        }

        return chainCount;
    }
}

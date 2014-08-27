package edu.neumont.csc252.boxed;

import java.util.HashMap;

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

            if(pointWithinGraphBounds(north))
            {
                int nVertex = graphPointToVertex(north);
                coins.addEdge(i, nVertex, 1);
            }

            if(pointWithinGraphBounds(south))
            {
                int sVertex = graphPointToVertex(south);
                coins.addEdge(i, sVertex, 1);
            }

            if(pointWithinGraphBounds(west))
            {
                int wVertex = graphPointToVertex(west);
                coins.addEdge(i, wVertex, 1);
            }

            if(pointWithinGraphBounds(east))
            {
                int eVertex = graphPointToVertex(south);
                coins.addEdge(i, eVertex, 1);
            }

        }
    }

    public int drawLine(int player, int x1, int y1, int x2, int y2)
    {
        int maxPointVertex = 0;
        int connectingPointVertex = 0;
        int score = 0;

        if(lineIsHorizontal(x1, y1, x2, y2))
        {
            Point maxXPoint = findPointWithMaxX(x1, y1, x2, y2);
            maxPointVertex = graphPointToVertex(maxXPoint);

            Point verticallyDown = new Point(maxXPoint.getX(), maxXPoint.getY()+1);
            connectingPointVertex = graphPointToVertex(verticallyDown);
        }
        else if(lineIsVertical(x1, y1, x2, y2))
        {
            Point maxYPoint = findPointWithMaxY(x1, y1, x2, y2);
            maxPointVertex = graphPointToVertex(maxYPoint);

            Point horizontalAcross = new Point(maxYPoint.getX() +1, maxYPoint.getY());
            connectingPointVertex = graphPointToVertex(horizontalAcross);
        }

        coins.removeEdge(maxPointVertex, connectingPointVertex);

        score = (coins.first(maxPointVertex) == coins.verticeCount()) ? score + 1 : score;
        score = (coins.first(connectingPointVertex) == coins.verticeCount()) ?  score+1 : score;

        playerScores.put(player, playerScores.containsKey(player) ? playerScores.get(player) + score : score);

        return score;
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
        return (p.getX() * coinsRows) + p.getY();
    }
}

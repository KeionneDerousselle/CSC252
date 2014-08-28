package edu.neumont.csc252.boxed;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by kderousselle on 8/26/14.
 */
public class DotsAndBoxesTest {
    @Test
    public void testDrawLine() throws Exception
    {
        DotsAndBoxes board = new DotsAndBoxes(5, 5);

        Assert.assertTrue(board.areMovesLeft());

        int s1 = board.drawLine(1, 0,0, 1,0);

        Assert.assertTrue(s1 == 0);

        int s2 = board.drawLine(1, 0,0, 0, 1);
        int s3 = board.drawLine(1, 1, 0, 1, 1);
        int s4 = board.drawLine(1, 0,1, 1, 1);

        Assert.assertTrue(s4 == 1);

    }

    @Test
    public void testScore() throws Exception
    {
        DotsAndBoxes board = new DotsAndBoxes(5, 5);
        int s1 = board.drawLine(1, 0,0, 1,0);
        int s2 = board.drawLine(1, 0,0, 0, 1);
        int s3 = board.drawLine(1, 1, 0, 1, 1);
        int s4 = board.drawLine(1, 0,1, 1, 1);

        int s5 = board.drawLine(2, 1,0, 2, 0);

        int s6 = board.drawLine(1, 2,0, 2, 1);
        int s7 = board.drawLine(1, 1,1, 2,1);

        Assert.assertTrue(board.score(1) == 2);
        Assert.assertTrue(board.score(2) == 0);
    }

    @Test
    public void testAreMovesLeft() throws Exception
    {
        int rows = 5;
        int cols = 5;

        DotsAndBoxes board = new DotsAndBoxes(rows, cols);

        for( int i = 0; i< rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                Point p = new Point(i, j);

                if(pointIsValid(rows, cols, p.getX(), p.getY()-1))
                {
                    board.drawLine(1, p.getX(), p.getY(), p.getX(), p.getY()-1);
                }

                if(pointIsValid(rows, cols, p.getX(), p.getY()+1))
                {
                    board.drawLine(1, p.getX(), p.getY(), p.getX(), p.getY()+1);
                }

                if(pointIsValid(rows, cols, p.getX()-1, p.getY()))
                {
                    board.drawLine(1, p.getX(), p.getY(), p.getX()-1, p.getY());
                }

                if(pointIsValid(rows, cols, p.getX()+1, p.getY()))
                {
                    board.drawLine(1, p.getX(), p.getY(), p.getX() +1, p.getY());
                }
            }
        }

        Assert.assertFalse(board.areMovesLeft());
    }

    private boolean pointIsValid(int rows, int cols, int x, int y)
    {
        return (x > -1 && x < rows) && (y > -1 && y < cols);
    }

    @Test
    public void countDoubleCrossesTest()
    {
        DotsAndBoxes board = new DotsAndBoxes(5, 5);

        board.drawLine(1, 0,0, 1, 0);
        board.drawLine(1, 1,0, 2, 0);
        board.drawLine(1, 2,0, 2, 1);
        board.drawLine(1, 1,1, 2, 1);
        board.drawLine(1, 0,1, 1, 1);
        board.drawLine(1, 0,0, 0, 1);

        board.drawLine(2, 3,0, 4, 0);
        board.drawLine(2, 4,0, 4, 1);
        board.drawLine(2, 4,1, 4, 2);
        board.drawLine(2, 4,2, 3, 2);
        board.drawLine(2, 3,1, 3, 2);
        board.drawLine(2, 3,0, 3, 1);

       Assert.assertTrue(board.countDoubleCrosses() == 2);
    }

    @Test
    public void countCyclesTest()
    {
        DotsAndBoxes board = new DotsAndBoxes(5, 5);

        board.drawLine(1, 0,0, 1,0);
        board.drawLine(1, 1,0, 2,0);
        board.drawLine(1, 2,0, 3,0);
        board.drawLine(1, 3,0, 3,1);
        board.drawLine(1, 3,1, 3,2);
        board.drawLine(1, 3,2, 2,2);
        board.drawLine(1, 2,2, 1,2);
        board.drawLine(1, 1,2, 0,2);
        board.drawLine(1, 0,2, 0,1);
        board.drawLine(1, 0,1, 0,0);
        board.drawLine(1, 1,1, 2,1);

        Assert.assertTrue(board.countCycles() == 1);
    }

    @Test
    public void countChainsTest()
    {
        DotsAndBoxes board = new DotsAndBoxes(5, 5);
        board.drawLine(1, 0,0, 0,1);
        board.drawLine(1, 0,1, 0,2);
        board.drawLine(1, 0,2, 0,3);
        board.drawLine(1, 1,0, 1,1);
        board.drawLine(1, 1,1, 1,2);
        board.drawLine(1, 1,2, 1,3);

        board.drawLine(1, 1,3, 2,3);
        board.drawLine(1, 2,3, 3,3);
        board.drawLine(1, 3,3, 4,3);
        board.drawLine(1, 1,4, 2,4);
        board.drawLine(1, 2,4, 3,4);
        board.drawLine(1, 3,4, 4,4);
        //board.drawLine(1, 0,3, 0,4);
        //board.drawLine(1, 0,4, 1,4);

        Assert.assertTrue(board.countChains() == 2);
    }
}

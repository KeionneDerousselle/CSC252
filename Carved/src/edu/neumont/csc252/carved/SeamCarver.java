package edu.neumont.csc252.carved;

import com.sun.corba.se.spi.activation._ServerManagerImplBase;
import edu.neumont.ui.Picture;

import java.awt.*;

/**
 * Created by kderousselle on 9/6/14.
 */
public class SeamCarver
{

    private Picture picture;

    private Vertex[] pathEnergies;

    public SeamCarver(Picture picture)
    {
        this.picture = picture;
    }

    public Picture getPicture()
    {
        return this.picture;
    }

    public int getPictureWidth()
    {
        return this.picture.width();
    }

    public int getPictureHeight() {
        return this.picture.height();
    }

    public double energy(int x, int y)
    {
        if(pointInBounds(x, y))
        {
            Point left = getLeft(x, y);
            Point right = getRight(x, y);

            int xRedChange = getRedChange(left, right);
            int xGreenChange = getGreenChange(left, right);
            int xBlueChange = getBlueChange(left, right);

            double xChange = Math.pow(xRedChange, 2) + Math.pow(xGreenChange, 2) + Math.pow(xBlueChange, 2);

            Point top = getTop(x, y);
            Point bottom = getBottom(x, y);

            int yRedChange = getRedChange(top, bottom);
            int yGreenChange = getGreenChange(top, bottom);
            int yBlueChange = getBlueChange(top, bottom);

            double yChange = Math.pow(yRedChange, 2) + Math.pow(yGreenChange, 2) + Math.pow(yBlueChange, 2);

            return xChange + yChange;
        }
        else {
            throw new IndexOutOfBoundsException("The energy for point ( " + x + " , " + y + " ) could not be found because the point is out of bounds.");
        }
    }

    private int getRedChange(Point p1, Point p2)
    {
        return getRed(p1.getX(), p1.getY()) - getRed(p2.getX(), p2.getY());
    }

    private int getBlueChange(Point p1, Point p2)
    {
        return getBlue(p1.getX(), p1.getY()) - getBlue(p2.getX(), p2.getY());
    }

    private int getGreenChange(Point p1, Point p2)
    {
        return getGreen(p1.getX(), p1.getY()) - getGreen(p2.getX(), p2.getY());
    }
    private int getRed(int x, int y)
    {
       return this.picture.get(x,y).getRed();
    }

    private int getBlue(int x, int y)
    {
        return this.picture.get(x,y).getBlue();
    }

    private int getGreen(int x, int y)
    {
        return this.picture.get(x,y).getGreen();
    }

    private Point getRight(int x, int y)
    {
        int xRight = (x+1 >= getPictureWidth())? 0 : x+1;

        return new Point(xRight, y);
    }

    private Point getLeft(int x, int y)
    {
        int xLeft = (x-1 < 0)? getPictureWidth() - 1: x-1;

        return new Point(xLeft, y);
    }

    private Point getTop(int x, int y)
    {
        int yTop = (y - 1 < 0)? getPictureHeight() - 1 : y-1;

        return new Point(x, yTop);
    }

    private Point getBottom(int x, int y)
    {
        int yBottom = (y + 1 >= getPictureHeight())? 0 : y+1;

        return new Point(x, yBottom);
    }

    public int[] findVerticalSeam()
    {
        int[] vertSeam = new int[getPictureHeight()];

        pathEnergies = new Vertex[getPictureWidth() * getPictureHeight()];
        int pathEndVertex = -1;

        for(int i = 0; i< getPictureWidth(); i++)
        {
            for(int j = 0; j < getPictureHeight(); j++)
            {
                Point parent = new Point(i,j);
                int parentVertex = pointToVertex(parent);

                if(pathEnergies[parentVertex]== null)
                {
                    pathEnergies[parentVertex] = new Vertex(-1, energy(i,j), 0);
                }

                double parentEnergy = pathEnergies[parentVertex].getEnergy();

                for(int childX = i - 1; childX < i+2; childX ++)
                {
                    Point child = new Point(childX, j+1);

                    if(pointInBounds(child.getX(), child.getY()))
                    {
                        int childVertex = pointToVertex(child);
                        double cost = pathEnergies[parentVertex].getPathEnergy() + parentEnergy;

                        if(pathEnergies[childVertex] == null)
                        {
                            pathEnergies[childVertex] = new Vertex(parentVertex, energy(child.getX(), child.getY()), cost);
                        }

                       else{
                            if(pathEnergies[childVertex].getPathEnergy() > cost)
                            {
                                pathEnergies[childVertex].setPathEnergy(cost);
                                pathEnergies[childVertex].setParent(parentVertex);
                            }
                        }


                        if(child.getY() == getPictureHeight() -1)
                        {
                            if(pathEndVertex == -1)
                            {
                                pathEndVertex = childVertex;
                            }
                            else
                            {
                                pathEndVertex = (pathEnergies[childVertex].getPathEnergy() < pathEnergies[pathEndVertex].getPathEnergy()) ? childVertex : pathEndVertex;
                            }
                        }
                }
                }
            }
        }

        for(int i = vertSeam.length - 1; i > -1 && pathEndVertex > -1; i--)
        {
            vertSeam[i] = pathEndVertex;
            pathEndVertex = pathEnergies[pathEndVertex].getParent();
        }

        return vertSeam;
    }
    public int[] findHorizontalSeam()
    {
        int[] horzSeam = new int[getPictureWidth()];

        pathEnergies = new Vertex[getPictureWidth() * getPictureHeight()];
        int pathEndVertex = -1;

        for( int i = 0; i < getPictureWidth()-1; i++)
        {
            for(int j = 0; j < getPictureHeight(); j++)
            {
                Point parent = new Point(i, j);
                int parentVertex = pointToVertex(parent);

                if( pathEnergies[parentVertex] == null)
                {
                    pathEnergies[parentVertex] = new Vertex(-1, energy(i,j), 0);
                }

                double parentEnergy = pathEnergies[parentVertex].getEnergy();

               for(int childY = j-1; childY < j+2 ; childY++)
               {
                   Point child = new Point(i+1, childY);

                   if(pointInBounds(child.getX(), child.getY()))
                   {
                        int childVertex = pointToVertex(child);
                        double cost = pathEnergies[parentVertex].getPathEnergy() + parentEnergy;

                        if(pathEnergies[childVertex] == null)
                        {
                            pathEnergies[childVertex] = new Vertex(parentVertex, energy(child.getX(), child.getY()), cost);
                        }
                        else
                         {
                            if(pathEnergies[childVertex].getPathEnergy() > cost)
                            {
                                pathEnergies[childVertex].setPathEnergy(cost);
                                pathEnergies[childVertex].setParent(parentVertex);
                            }
                        }


                       if(child.getX() == getPictureWidth() - 1)
                       {
                           if(pathEndVertex == -1)
                           {
                               pathEndVertex = childVertex;
                           }
                           else
                           {
                               pathEndVertex = (pathEnergies[childVertex].getPathEnergy() < pathEnergies[pathEndVertex].getPathEnergy()) ? childVertex : pathEndVertex;
                           }
                       }
                   }
               }

            }
        }

        for(int i = horzSeam.length - 1; i > -1 && pathEndVertex > -1; i--)
        {
            horzSeam[i] = pathEndVertex;
            pathEndVertex = pathEnergies[pathEndVertex].getParent();
        }

        return horzSeam;
    }

    private boolean pointInBounds(int x, int y)
    {
         return (x > -1 && x < getPictureWidth()) && (y > -1 && y < getPictureHeight());
    }

    private Point vertexToPoint(int vertex)
    {
        return new Point(vertex % getPictureWidth(), vertex / getPictureWidth());
    }

    private int pointToVertex(Point p)
    {
        return (p.getY() * getPictureWidth()) + p.getX();
    }

    public void removeHorizontalSeam(int[] indices)
    {
        horzSeamValidCheck(indices);

        Picture newPicture = new Picture(getPictureWidth(), getPictureHeight() -1);
        int currentVertex = 0;

        for(int i = 0; i< getPictureWidth(); i++)
        {
            for(int newPictureY = 0, oldPictureY = 0; newPictureY < getPictureHeight() - 1; newPictureY++, oldPictureY++)
            {
                if(currentVertex < indices.length && (pointToVertex(new Point(i, oldPictureY)) == indices[currentVertex]))
                {
                    oldPictureY++;
                    currentVertex++;
                }

                newPicture.set(i, newPictureY, this.picture.get(i, oldPictureY));
            }

        }

        this.picture = newPicture;
    }

    public void removeVerticalSeam(int[] indices)
    {
        vertSeamValidCheck(indices);
        Picture newPicture = new Picture(getPictureWidth() -1, getPictureHeight());
        int currentVertex = 0;

        for(int j = 0; j < getPictureHeight(); j++)
        {
            for(int newPictureX = 0, oldPictureX = 0; newPictureX < getPictureWidth() -1; newPictureX++, oldPictureX++)
            {

                if(currentVertex < indices.length && (pointToVertex(new Point(oldPictureX, j)) == indices[currentVertex]))
                {

                    oldPictureX++;
                    currentVertex++;
                }

                newPicture.set(newPictureX, j, this.picture.get(oldPictureX, j));
            }

        }

        this.picture = newPicture;
    }

    private boolean vertexIsOneHorz(int vertex1, int vertex2)
    {
        Point p1 = vertexToPoint(vertex1);
        Point p2 = vertexToPoint(vertex2);

        return (p2.getX() == p1.getX() +1) && (p2.getY() == p1.getY() || p2.getY() == p1.getY() -1 || p2.getY() == p1.getY() +1);
    }

    private boolean vertexIsOneVert(int vertex1, int vertex2)
    {
        Point p1 = vertexToPoint(vertex1);
        Point p2 = vertexToPoint(vertex2);

        return (p2.getY() == p1.getY() +1) && (p2.getX() == p1.getX() || p2.getX() == p1.getX() -1 || p2.getX() == p1.getX() + 1);
    }

    private void horzSeamValidCheck(int[] indices)
    {
        if(seamLengthIsValid(indices, getPictureWidth(), getPictureWidth(), "horizontal"))
        {
            if(!pictureWidthTooSmall() && !pictureHeightTooSmall())
            {
                int previousVertex = indices[0];

                for(int i = 1 ; i < indices.length; i++)
                {
                    if(!vertexIsOneHorz(previousVertex, indices[i]))
                    {
                        throw new IllegalArgumentException("This horizontal seam is not valid. The vertices must be within a distance of 1.");
                    }

                    if(i == 1)
                    {
                        Point p = vertexToPoint(indices[0]);

                        if(!pointInBounds(p.getX(), p.getY()))
                        {
                            throw new IndexOutOfBoundsException("This index is not a valid location within the picture: ( " + p.getX() + " , " + p.getY() + " ).");
                        }
                    }

                    Point p = vertexToPoint(indices[i]);

                    if(!pointInBounds(p.getX(), p.getY()))
                    {
                        throw new IndexOutOfBoundsException("This index is not a valid location within the picture: ( " + p.getX() + " , " + p.getY() + " ).");
                    }

                    previousVertex = indices[i];
                }
            }
            else
            {
                throw new IllegalArgumentException("The picture is too small to remove a seam.");
            }
        }

    }


    private void vertSeamValidCheck(int[] indices)
    {
        if(seamLengthIsValid(indices, getPictureHeight(), getPictureHeight(), "vertical"))
        {
            if(!pictureWidthTooSmall() && !pictureHeightTooSmall())
            {
                int previousVertex = indices[0];

                for(int i = 1 ; i < indices.length; i++)
                {
                    if(!vertexIsOneVert(previousVertex, indices[i]))
                    {
                        throw new IllegalArgumentException("This vertical seam is not valid. The vertices must be within a distance of 1.");
                    }

                    if(i == 1)
                    {
                        Point p = vertexToPoint(indices[0]);

                        if(!pointInBounds(p.getX(), p.getY()))
                        {
                            throw new IndexOutOfBoundsException("This index is not a valid location within the picture: ( " + p.getX() + " , " + p.getY() + " ).");
                        }
                    }

                    Point p = vertexToPoint(indices[i]);

                    if(!pointInBounds(p.getX(), p.getY()))
                    {
                        throw new IndexOutOfBoundsException("This index is not a valid location within the picture: ( " + p.getX() + " , " + p.getY() + " ).");
                    }

                    previousVertex = indices[i];
                }
            }
            else
            {
                throw new IllegalArgumentException("The picture is too small to remove a seam.");
            }
        }

    }

    private boolean seamLengthIsValid(int[] indices, int min, int max, String seamType)
    {
        boolean validSeam = true;

        if(indices.length < min)
        {
            validSeam = false;

            throw new IllegalArgumentException("The " + seamType + " to remove is too short.");
        }
        else if(indices.length > max)
        {
            validSeam = false;

            throw new IllegalArgumentException("The " + seamType + " to remove is too long.");
        }

        return validSeam;
    }

    private boolean pictureWidthTooSmall()
    {
        return getPictureWidth() <= 1;
    }

    private boolean pictureHeightTooSmall()
    {
        return getPictureHeight() <= 1;
    }
}


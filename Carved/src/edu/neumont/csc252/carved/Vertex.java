package edu.neumont.csc252.carved;

/**
 * Created by kderousselle on 9/6/14.
 */
public class Vertex
{
    private int parent = -1;
    double energy = -1, pathEnergy = -1;

    public Vertex(int parent, double energy, double pathEnergy)
    {
        this.parent = parent;
        this.energy = energy;
        this.pathEnergy = pathEnergy;
    }

    public double getEnergy() {
        return energy;
    }

    public int getParent() {
        return parent;
    }

    public double getPathEnergy() {
        return pathEnergy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void setPathEnergy(double pathEnergy) {
        this.pathEnergy = pathEnergy;
    }
}

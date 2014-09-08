package edu.neumont.csc252.carved;

import edu.neumont.ui.Picture;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by kderousselle on 9/6/14.
 */
public class SeamCarverTest {
    @Test
    public void testGetPicture() throws Exception {

    }

    @Test
    public void testGetPictureWidth() throws Exception {

    }

    @Test
    public void testGetPictureHeight() throws Exception {

    }

    @Test
    public void testEnergy() throws Exception {

    }

    @Test
    public void testFindHorizontalSeam() throws Exception
    {
        SeamCarver sc = new SeamCarver(new Picture("src/Images/overlayimagewithhiddenmessage.png"));
        int origHeight = sc.getPictureHeight();

        for(int i = 0 ; i < origHeight/2 ; i++)
        {
            int[] indices = sc.findHorizontalSeam();
            sc.removeHorizontalSeam(indices);
        }

        Assert.assertTrue(sc.getPictureHeight() == (origHeight/2));
        sc.getPicture().show();
        Thread.sleep(20000);
    }

    @Test
    public void testFindVerticalSeam() throws Exception
    {
        SeamCarver sc = new SeamCarver(new Picture("src/Images/overlayimagewithhiddenmessage.png"));
        int origWidth = sc.getPictureWidth();

        for(int i = 0; i < origWidth/2; i++)
        {
            int[] indices = sc.findVerticalSeam();
            sc.removeVerticalSeam(indices);
        }

        Assert.assertTrue(sc.getPictureWidth() == (origWidth/2));

        sc.getPicture().show();
        Thread.sleep(20000);
    }

    @Test
    public void showSecretMessage() throws Exception
    {
        SeamCarver sc = new SeamCarver(new Picture("src/Images/overlayimagewithhiddenmessage.png"));
        int origHeight = sc.getPictureHeight();
        int origWidth = sc.getPictureWidth();

        for(int i = 0 ; i < origHeight/2 ; i++)
        {
            int[] indices = sc.findHorizontalSeam();
            sc.removeHorizontalSeam(indices);
        }

        for(int i = 0; i < origWidth/2; i++)
        {
            int[] indices = sc.findVerticalSeam();
            sc.removeVerticalSeam(indices);
        }
        sc.getPicture().show();
        Thread.sleep(20000);
    }
}

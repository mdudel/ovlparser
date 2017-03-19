/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ovlweb.utils;

import java.awt.Point;

/**
 *
 * @author Kenneth Keith <kkeith@mitre.org>
 */
public class ImageEnvelope {

    private int width, height, top, bottom, left, right;
    Point center = new Point();

    public ImageEnvelope(int left, int bottom, int right, int top) {
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.left = left;

        this.width = right - left;
        this.height = top - bottom;
        this.center = new Point(right-left/2,top-bottom/2);
    }

    public int getBottom() {
        return bottom;
    }

    public Point getCenter() {
        return center;
    }

    public int getHeight() {
        return height;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getWidth() {
        return width;
    }
    
    
}

package com.slaxer.framework.implementation;

import android.graphics.Point;
import android.graphics.Rect;

public class Rectangle {
	// The purpose of this class is to give a little more
	// flexibility in handling rectangles than provided
	// by Rect. Also a little more 'sensical' when coming
	// from java.awt.Rectangle. This class should operate
	// as closely to java.awt.Rectangle as possible.

	private Rect rectangle;
	public int x;
	public int y;
	public int width;
	public int height;

	// Borrowed from Rectangle2D.
	public static final int OUT_LEFT = 1;
	public static final int OUT_TOP = 2;
	public static final int OUT_RIGHT = 4;
	public static final int OUT_BOTTOM = 8;

	// Create a new Rectangle at 0,0 with no height or width
	public Rectangle() {
		this(0, 0, 0, 0);
	}

	// Create a new Rectangle at 0,0 with width and height
	// from the specified Dimension.
	public Rectangle(Dimension d) {
		this(0, 0, d.width, d.height);
	}

	// Create a new Rectangle at 0, 0 with the specified
	// width and height.
	public Rectangle(int width, int height) {
		this(0, 0, width, height);
	}

	// Create a new Rectangle at the Point specified
	// with no width or height.
	public Rectangle(Point p) {
		this(p.x, p.y, 0, 0);
	}

	// Create a new Rectangle at coordinates specified by Point
	// with width and height from the specified Dimension.
	public Rectangle(Point p, Dimension d) {
		this(p.x, p.y, p.x + d.width, p.y + d.height);
	}

	// Create a new Rectangle at the specified coordinates
	// with the specified width and height.
	public Rectangle(int x, int y, int width, int height) {
		rectangle = new Rect(x, y, x + width, x + height);
		this.x = rectangle.left;
		this.y = rectangle.right;
		this.width = rectangle.right - rectangle.left;
		this.height = rectangle.bottom - rectangle.top;
	}

	// Create a new Rectangle with the same dimensions
	// as the specified Rectangle.
	public Rectangle(Rectangle r) {
		this(r.x, r.y, r.x + r.width, r.y + r.height);
	}

	public double getX() {
		return rectangle.left;
	}

	public double getY() {
		return rectangle.top;
	}

	public double getWidth() {
		return rectangle.width();
	}

	public double getHeight() {
		return rectangle.height();
	}

	// Return a new Rectangle with the same bounds as
	// our Rectangle. Does not modify the calling Rectangle.
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	// Give our Rectangle the boundaries of the specified
	// Rectangle. Modifies the calling Rectangle.
	public void setBounds(Rectangle r) {
		this.setBounds(r.x, r.y, r.width, r.height);
	}

	// Give our Rectangle the specified boundaries. Modifies
	// the calling Rectangle.
	public void setBounds(int x, int y, int width, int height) {
		reshape(x, y, width, height);
	}

	// Set our Rectangle to have a bounding box containing
	// the specified coordinates and dimensions. Modifies
	// the calling Rectangle.
	public void setRect(double x, double y, double width, double height) {
		rectangle.set((int) Math.floor(x), (int) Math.floor(y),
				(int) Math.floor(x + width), (int) Math.floor(y + height));
		this.setBounds(rectangle.left, rectangle.top, rectangle.width(),
				rectangle.height());
	}

	// Set our Rectangle to have a bounding box containing
	// the specified coordinates and dimensions. Modifies
	// the calling Rectangle.
	@Deprecated
	public void reshape(int x, int y, int width, int height) {
		rectangle.set(x, y, x + width, y + height);
		this.x = rectangle.left;
		this.y = rectangle.right;
		this.width = rectangle.width();
		this.height = rectangle.height();
	}

	// Return the coordinates of our Rectangle as a Point.
	public Point getLocation() {
		return new Point(x, y);
	}

	// Move our Rectangle to the coordinates of the specified
	// Point. Modifies our Rectangle.
	public void setLocation(Point p) {
		setLocation(p.x, p.y);
	}

	// Move our Rectangle to the specified coordinates.
	// Modifies the calling Rectangle.
	public void setLocation(int x, int y) {
		move(x, y);
	}

	// Move the rectangle to the specified coordinates.
	// Modifies the calling Rectangle.
	@Deprecated
	public void move(int x, int y) {
		setBounds(x, y, x + width, y + height);
	}

	// Move our Rectangle to the specified coordinates.
	// Modifies the calling Rectangle.
	public void translate(int x, int y) {
		setLocation(x, y);
	}

	// Get the size of our Rectangle as a Dimension.
	public Dimension getSize() {
		return new Dimension(width, height);
	}

	// Set the size of our Rectangle using the specified
	// Dimension. Modifies the calling Rectangle.
	public void setSize(Dimension d) {
		setSize(d.width, d.height);
	}

	// Set the size of our Rectangle to the specified
	// width and height. Modifies the calling Rectangle.
	public void setSize(int width, int height) {
		resize(width, height);
	}

	// Set the size of our Rectangle to the specified
	// width and height. Modifies the calling Rectangle.
	@Deprecated
	public void resize(int width, int height) {
		rectangle.set(rectangle.left, rectangle.top, rectangle.left + width,
				rectangle.right + height);
		this.width = width;
		this.height = height;
	}

	// Check whether our Rectangle contains the specified
	// Point
	public boolean contains(Point p) {
		return contains(p.x, p.y);
	}

	// Check whether our Rectangle contains the specified
	// coordinates
	public boolean contains(int x, int y) {
		return inside(x, y);
	}

	// Check whether our Rectangle contains the specified
	// Rectangle
	public boolean contains(Rectangle r) {
		return contains(r.x, r.y, r.width, r.height);
	}

	// Check whether our Rectangle contains the Rectangle
	// defined by the specified coordinates and dimensions.
	public boolean contains(int x, int y, int width, int height) {
		return rectangle.contains(x, y, x + width, y + height);
	}

	// Check whether our Rectangle contains the point
	// specified by the coordinates
	@Deprecated
	public boolean inside(int x, int y) {
		return rectangle.contains(x, y);
	}

	// Determines whether our Rectangle intersects the
	// specified Rectangle.
	public boolean intersects(Rectangle r) {
		return Rect.intersects(this.rectangle, r.rectangle);
	}

	// Return a new Rectangle representing the intersection
	// of our Rectangle with the specified Rectangle.
	// Does not modify the calling Rectangle.
	public Rectangle intersection(Rectangle r) {
		Rect rect = new Rect();
		rect.setIntersect(this.rectangle, r.rectangle);
		return new Rectangle(rect.left, rect.top, rect.width(), rect.height());
	}

	// Return a new Rectangle representing the union
	// of our Rectangle with the specified Rectangle.
	// Does not modify the calling Rectangle.
	public Rectangle union(Rectangle r) {
		Rect rect = new Rect(this.rectangle);
		rect.union(r.rectangle);
		return new Rectangle(rect.left, rect.top, rect.width(), rect.height());

	}

	// Add the point at the specified coordinates
	// the our Rectangle, resizing to include the new
	// point and our original Rectangle. Modifies the
	// calling Rectangle.
	public void add(int newX, int newY) {
		int x1 = Math.min(x, newX);
		int x2 = Math.max(x + width, newX);
		int y1 = Math.min(y, newY);
		int y2 = Math.max(x + width, newY);
		this.setBounds(x1, y1, x2 - x1, y2 - y1);
	}

	// Add the specified Point to our Rectangle.
	public void add(Point p) {
		this.add(p.x, p.y);
	}

	// Add the specified Rectangle to our Rectangle.
	// Essentially a union. Modifies the calling
	// Rectangle.
	public void add(Rectangle r) {
		this.setBounds(this.union(r));
	}

	// Grow our Rectangle by the specified width and
	// height values. This expands to the left and
	// right by width and to the top and bottom
	// by height, respectively. Also accepts negative
	// values to function as a shrink. Modifies the
	// calling Rectangle.
	public void grow(int width, int height) {
		this.setBounds(x - width, y - height, this.width + width * 2,
				this.height + height * 2);
	}

	// Return true if our Rectangle has a width or
	// height less than or equal to zero.
	public boolean isEmpty() {
		return this.rectangle.isEmpty();
	}

	// Determines where the specified coordinates lie
	// in relation to our Rectangle. Returns the logical
	// OR of all appropriate out codes.
	public int outCode(double x, double y) {
		int out = 0;

		if (this.width <= 0)
			out |= OUT_LEFT | OUT_RIGHT;
		else if (x < this.x)
			out |= OUT_LEFT;
		else if (x > this.x + (double) this.width)
			out |= OUT_RIGHT;
		if (this.height <= 0)
			out |= OUT_TOP | OUT_BOTTOM;
		else if (y < this.y)
			out |= OUT_TOP;
		else if (y > this.y + (double) this.height)
			out |= OUT_BOTTOM;

		return out;
	}

	// Checks whether our Rectangle equals the
	// specified Rectangle.
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle r = (Rectangle) obj;
			return ((this.x == r.x) && (this.y == r.y)
					&& (this.width == r.width) && (this.height == r.height));
		}
		return (super.equals(obj));
	}

	// Return a String representation of our Rectangle.
	public String toString() {
		return getClass().getName() + "[x=" + x + ",y=" + y + ",width=" + width
				+ ",height=" + height + "]";
	}

}

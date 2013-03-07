package com.slaxer.framework.implementation;

public class Dimension {

	public int width;
	public int height;

	// Create an instance of Dimension with a width
	// and height of zero.
	public Dimension() {
		this(0, 0);
	}

	// Create an instance of Dimension whose width
	// and height are the same as for the specified
	// Dimension.
	public Dimension(Dimension d) {

	}

	// Create an instance of Dimension with the specified
	// width and height.
	public Dimension(int width, int height) {
		this.width = width;
		this.height = height;
	}

	// Return the width of our Dimension as a double.
	public double getWidth() {
		return width;
	}

	// Return the height of our Dimension as a double.
	public double getHeight() {
		return height;
	}

	// Set the width and height of our Dimension to the
	// specified width and height. If the specified
	// width or height are greater than Integer.MAX_VALUE,
	// they will be reset to Integer.MAX_VALUE. Modifies
	// the calling Dimension.
	public void setSize(double width, double height) {
		this.width = (int) Math.min(width, Integer.MAX_VALUE);
		this.height = (int) Math.min(width, Integer.MAX_VALUE);
	}

	// Get the size of our Dimension, as a Dimension.
	// Returns a new Dimension. Does not modify the
	// calling Dimension.
	public Dimension getSize() {
		return new Dimension(this.width, this.height);
	}

	// Set the dimensions of our Dimension to that
	// of the specified Dimension. Modifies the calling
	// Dimension.
	public void setSize(Dimension d) {
		setSize(d.width, d.height);
	}

	// Set the dimensions of our Dimension to the
	// specified dimensions. Modifies the calling
	// Dimension.
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	// Compare our Dimension with the specified Dimension
	// and return true if they have the same dimensions.
	public boolean equals(Object obj) {
		if (obj instanceof Dimension) {
			Dimension d = (Dimension) obj;
			return ((this.width == d.width) && (this.height == d.height));
		}
		return (super.equals(obj));
	}

	// Return the hashcode for our Dimension.
	public int hashCode() {
		int sum = width + height;
		return sum * (sum + 1) / 2 + width;
	}

	// Return a String representing our Dimension.
	public String toString() {
		return getClass().getName() + "width=" + width + ",height=" + height
				+ "]";
	}

}

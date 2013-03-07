package com.slaxer.framework.implementation;

import android.graphics.Color;
import android.graphics.Point;

import com.slaxer.framework.Input.TouchEvent;

public class CollRect extends Rectangle {
	
	private boolean visible = false;
	private boolean solid = true;
	private int color = Color.WHITE;
	
	public CollRect() {
		// TODO Auto-generated constructor stub
	}

	public CollRect(Dimension d) {
		super(d);
	}

	public CollRect(int width, int height) {
		super(width, height);
	}

	public CollRect(Point p) {
		super(p);
	}

	public CollRect(Point p, Dimension d) {
		super(p, d);
	}

	public CollRect(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public CollRect(Rectangle r) {
		super(r);
	}
		
	// Check if a touchEvent occurs inside our
	// collRect
	public boolean inBounds(TouchEvent event, CollRect collRect){
		if(this.contains(event.x, event.y))
			return true;
		else
			return false;
	}
	
	public boolean checkCollision(CollRect collRect){
		return this.intersects(collRect);
	}
	
	public boolean isVisible() {
		return visible;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	// If you want collision detection but not
	// obstruction, set to false. This could
	// be useful for traps or for input detection.
	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	
	// We default to white, but allow the user
	// to chose a desired color for each CollRect.
	// Use with Color.COLOR.
	public void setColor(int color){
		this.color = color;
	}

}

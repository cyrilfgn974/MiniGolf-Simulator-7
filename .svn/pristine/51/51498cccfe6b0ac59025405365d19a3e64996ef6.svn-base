package golf;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Point2D implements Serializable {
	private float x;
	private float y;

	private static final long serialVersionUID = 4L;

	public Point2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public float distance(Point2D point) {
		return (float) Math.sqrt(Math.pow(this.x-point.getX(),2)+Math.pow(this.y-point.getY(),2));
	}

	public float angle(Point2D point) {
		float diffX = this.x - point.getX();
		float diffY = this.y - point.getY();
		float angle;
		if (diffX == 0.0f) {
			angle = (diffY >= 0)? 0.0f : 180.0f;
		}
		else {
			float f = (this.x - point.getX() > 0) ? 180.0f : 0.0f;
			angle = (float) Math.toDegrees(Math.atan((this.y - point.getY()) / (this.x - point.getX()))) + f;
		}
		return angle;
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.writeFloat(this.x);
		oos.writeFloat(this.y);
	}

	private void readObject(ObjectInputStream ois) throws IOException {
		this.x = ois.readFloat();
		this.y = ois.readFloat();
	}

	public Point2D clone() {
		return new Point2D(x,y);
	}

}

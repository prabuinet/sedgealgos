package course;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class BouncingBalls {

	private static class Ball {
		private double rx, ry;
		private double vx, vy;
		
		private final double radius;
		
		public Ball() {
			rx = StdRandom.random();
			radius = 0.01;
		}
		
		public void move(double dt) {
			if((rx + vx*dt < radius) || (rx + vx*dt > 1.0 - radius)) vx = -vx;
			if((ry + vy*dt < radius) || (ry + vy*dt > 1.0 - radius)) vy = -vy;
			rx = rx + vx * dt;
			ry = ry + vy * dt;
		}
		
		public void draw() {
			StdDraw.filledCircle(rx, ry, radius);
		}
	}
	
	public static void main(String[] args) {

		int N = 50;
		
		Ball[] balls = new Ball[N];
		for(int i = 0; i < N; i++)
			balls[i] = new Ball();
		
		StdDraw.enableDoubleBuffering();
		
		while(true) {
			StdDraw.clear();
			
			for(int i = 0; i < N; i++)
			{
				balls[i].move(0.5);
				balls[i].draw();
			}
			
			StdDraw.show();
			StdDraw.pause(100);
		}
		

	}

}

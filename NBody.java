import java.awt.*;

public class NBody {

	public static double readRadius(String file) {
		int c;
		/* Start reading in planets.txt */
		In in = new In(file);

		/* Keep looking until the file is empty. */
		int nPlanets = in.readInt();
		double radius = in.readDouble();
		System.out.println( radius + " radius");
		return (radius);
	}

	public static Planet [] readPlanets(String file) {
		In in = new In(file);

		int nPlanets = in.readInt();
		double radius = in.readDouble();
		Planet planetArray[] = new Planet[nPlanets];

		/* loop through lines for planets*/
		for (int i = 0; i < nPlanets; i++) {
			/* get attributes per line */
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			/* use contructor to build each planet in the array */
			planetArray[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

		}
		return (planetArray);

	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt  = Double.parseDouble(args[1]);
		String filename = args[2];

		Planet[] planetsArray  = readPlanets(filename);
		Double radius = readRadius(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "./images/starfield.jpg");

		for (int i = 0; i < planetsArray.length; i++ ) {
			planetsArray[i].draw();
		}
		

		/* animation */
		int time = 0;
		while(time < T) {
			double[] xForces = new double[planetsArray.length];
            double[] yForces = new double[planetsArray.length];

            for (int i = 0; i < planetsArray.length; i++) {
                xForces[i] = planetsArray[i].calcNetForceExertedByX(planetsArray);
                yForces[i] = planetsArray[i].calcNetForceExertedByY(planetsArray);
            }

            for (int i = 0; i < planetsArray.length; i++) {
                planetsArray[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");

            for (int i = 0; i < planetsArray.length; i++ ) {
				planetsArray[i].draw();
			}

            StdDraw.show(10);
            time += dt;
		}


    }
		
}
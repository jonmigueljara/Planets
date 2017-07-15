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
		
}
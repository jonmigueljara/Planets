public class Planet {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;

	}

	double calcDistance(Planet p) {
		/* (x1-x2)^2 + (y1 - y2)^2 = r^2 */

		double distance;

		double x1 = xxPos;
		double y1 = yyPos;
		double x2 = p.xxPos;
		double y2 = p.yyPos;

		distance = (x1 - x2)*(x1 - x2) + (y1-y2)*(y1-y2);

		return (Math.sqrt(distance)); 
	}

	double calcForceExertedBy(Planet p) {
		/* G *(m1m2/ r^2)*/
		//may the force be with you 
		double force;
		double g = 6.67e-11;
		force = (g * this.mass * p.mass)/ (calcDistance(p) * calcDistance(p));

		return (force); 

	}

	double calcNetForceExertedByX(Planet[] planets) {
		/* F_x = F *(dx/r) */

		double total = 0;
		double f_x, dx;

		for (int i = 0; i < planets.length; i++) {
			// check the planet is looking at itself
			if (planets[i].equals(this)) {
				total = total;
			} else {
				dx = planets[i].xxPos - this.xxPos;
				f_x = (calcForceExertedBy(planets[i]) * dx) / calcDistance(planets[i]);
				total = total + f_x;
			}
		}

		return(total);
	}

	double calcNetForceExertedByY(Planet[] planets) {

		double total = 0;
		double dy, f_y;

		for (int i = 0;	i < planets.length; i++) {
			// check the planet is looking at itself
			if (planets[i].equals(this)) {
				total = total;
			} else {
				dy = planets[i].yyPos - this.yyPos ;
				f_y = (calcForceExertedBy(planets[i]) * dy) / calcDistance(planets[i]);
				total = total + f_y;
			}
		}

		return(total);
		
	}

	void update(double dt, double fx, double fy)  {
		double vxUpate; 
		double vyUpdate;
		double aX = fx/this.mass;
		double ay = fy/this.mass;

		vxUpate = this.xxVel + aX * dt;
		vxUpate = this.yyVel + aY * dt;

		this.xxVel = vxUpate;
		this.yyVel = vyUpdate;


	}


}



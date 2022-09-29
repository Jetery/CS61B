import examples.StdDraw;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: Jetery
 * Date: 2022.09.21
 */
public class Planet {

    // Its current x position
    public double xxPos;
    // Its current y position
    public double yyPos;
    // Its current velocity in the x direction
    public double xxVel;
    // Its current velocity in the y direction
    public double yyVel;
    // Its mass
    public double mass;
    //  The name of the file that corresponds to the image that depicts the planet
    public String imgFileName;
    // Gravitational constant
    private final double G = 6.67 / 1e11; // also 6.67e-11

    // Constructor
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


    // cal the distance between two planets
    public double calcDistance(Planet p) {
        double dx = Math.abs(xxPos - p.xxPos);
        double dy = Math.abs(yyPos - p.yyPos);
        return Math.sqrt(dx * dx + dy * dy);
    }

    // cal the force between two planets
    public double calcForceExertedBy(Planet p) {
        double dis = this.calcDistance(p);
        return (G * mass * p.mass) / (dis * dis);
    }

    // cal the x direction of force
    public double calcForceExertedByX(Planet p) {
        double d = (p.xxPos - xxPos) / this.calcDistance(p);
        return d * this.calcForceExertedBy(p);
    }

    // cal the y direction of force
    public double calcForceExertedByY(Planet p) {
        double d = (p.yyPos - yyPos) / this.calcDistance(p);
        return d * this.calcForceExertedBy(p);
    }

    // cal sum of x direction
    public double calcNetForceExertedByX(Planet[] ps) {
        double sum = 0;
        for (Planet p : ps) {
            if (p.equals(this)) continue;
            sum += this.calcForceExertedByX(p);
        }
        return sum;
    }

    // cal sum of x direction
    public double calcNetForceExertedByY(Planet[] ps) {
        double sum = 0;
        for (Planet p : ps) {
            if (p.equals(this)) continue;
            sum += this.calcForceExertedByY(p);
        }
        return sum;
    }

    // determines how much the forces exerted on the planet will cause that planet to accelerate,
    // and the resulting change in the planet’s velocity and position
    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel += dt * ax;
        this.yyVel += dt * ay;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
        StdDraw.show();
        StdDraw.pause(10);
    }


}

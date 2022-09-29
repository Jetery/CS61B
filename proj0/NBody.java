/**
 * Created with IntelliJ IDEA
 * Description:
 * User: Jetery
 * Date: 2022.09.23
 */
import examples.StdDraw;
import examples.StdDrawDemo;

public class NBody {

    public static double readRadius(String path) {
        In in = new In(path);
        int N = in.readInt();
        double R = in.readDouble();
        in.close();
        return R;
    }


    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int N = in.readInt();
        double R = in.readDouble();
        Planet[] planets = new Planet[N];
        for (int i = 0; i < N; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, img);

        }
        return planets;
    }

    public static void main(String[] args) {
        args = new String[] {"20000000", "20000", "./data/3body.txt"};
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        //String filename = "./data/planets.txt";
        double R = readRadius(filename); // ./data/planets.txt
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-R, R);
        StdDraw.enableDoubleBuffering();
        double t = 0;
        int num = planets.length;
        while(t <= T){
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for(int i = 0; i < num; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < num; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(1);
            t += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", R);
        for (Planet planet : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }

    }

}

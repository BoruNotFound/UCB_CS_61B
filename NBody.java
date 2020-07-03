public class NBody {
    /** Read from file */
    public static double readRadius(String f) {
        In in = new In(f);
        int n = in.readInt();
        double radius = in.readDouble();

        return radius;
    }

    public static Body[] readBodies(String f) {
        In in = new In(f);
        int n = in.readInt();
        double radius = in.readDouble();
        Body[] allBodies = new Body[n];
        
        for (int i = 0; i < n; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();

            allBodies[i] = new Body(xP, yP, xV, yV, m, img);
        }

        return allBodies;
    }

    public static void main(String[] args) {
        /** Collect inputs */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Body[] allBodies = readBodies(filename);
        
        /** Set canvas */
        String backgroundImg = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();            // set animation buffer
        StdDraw.setScale(-radius, radius);          // set canvas scale as the universe radius

        /** Animation */
        int waitTimeMilliseconds = 10;
        int n = allBodies.length;
        int i;
        double time;
        double[] xForces = new double[n];           // net force for each body
        double[] yForces = new double[n];           // net force for each body

        for (time = 0; time < T; time += dt) {
            /** Calc net forces */
            for (i = 0; i < n; i++) {
                Body b = allBodies[i];
                xForces[i] = b.calcNetForceExertedByX(allBodies);
                yForces[i] = b.calcNetForceExertedByY(allBodies);
            }

            /** Update velocities and positions */
            for (i = 0; i < n; i++) {
                Body b = allBodies[i];
                b.update(dt, xForces[i], yForces[i]);
            }

            /** Draw the background */
            StdDraw.picture(0, 0, backgroundImg);

            /** Draw the bodies */
            for (Body b: allBodies) {
                b.draw();
            }

            /** Show the offscreen buffer */
            StdDraw.show();

            /** Pause */
            StdDraw.pause(waitTimeMilliseconds);

        }

        /** Print out final stages */
        StdOut.printf("%d\n", n);
        StdOut.printf("%.2e\n", radius);
        for (i = 0; i < n; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        allBodies[i].xxPos, allBodies[i].yyPos, allBodies[i].xxVel,
                        allBodies[i].yyVel, allBodies[i].mass, allBodies[i].imgFileName);   
        }
    }
}
import java.lang.*;

public class Body {
    /** Instance variables */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /** First Constructor */
    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** Second conctructor -- to make a copy of a Body instance */
    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    /** Calculate distance -- between this Body and the other one */
    public double calcDistance(Body b) {
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;

        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /** Calculate Force -- force exerted on this body by the given body */
    public double calcForceExertedBy(Body b) {
        double grav = 6.67 * Math.pow(10, -11);
        double r = this.calcDistance(b);
        double force = grav * this.mass * b.mass / Math.pow(r, 2);

        return force;
    }

    public double calcForceExertedByX(Body b) {
        double force = this.calcForceExertedBy(b);
        double dx = b.xxPos - this.xxPos;
        double r = this.calcDistance(b);

        return force * dx / r;
    }

    public double calcForceExertedByY(Body b) {
        double force = this.calcForceExertedBy(b);
        double dy = b.yyPos - this.yyPos;
        double r = this.calcDistance(b);

        return force * dy / r;
    }

    public double calcNetForceExertedByX(Body[] allBodies) {
        double forceX = 0;
        for (Body b: allBodies) {
            if (! this.equals(b)) {
                // only calculate forces exerted by other bodies
                forceX += this.calcForceExertedByX(b);
            }
        }

        return forceX;
    }

    public double calcNetForceExertedByY(Body[] allBodies) {
        double forceY = 0;
        for (Body b: allBodies) {
            if (! this.equals(b)) {
                // only calculate forces exerted by other bodies
                forceY += this.calcForceExertedByY(b);
            }
        }

        return forceY;
    }

    /** Update -- body's velocity and position change in dt */
    public void update(double dt, double forceX, double forceY) {
        double ax = forceX / this.mass;
        double ay = forceY / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    /** Draw this planet on canvas */
    public void draw() {
        String path = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, path);
    }

}
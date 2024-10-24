class Point {
    private double x;
    private double y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

class Rectangle {

    private Point a;
    private Point b;

    public Rectangle(){
        this.a = new Point(0, 0);
        this.b = new Point(1, 1);
    }

    public Rectangle(double p1_x, double p1_y, double p2_x, double p2_y){
        this();
        if (is_correct(p1_x, p1_y, p2_x, p2_y)){
            this.a = new Point(p1_x, p1_y);
            this.b = new Point(p2_x, p2_y);
        }
    }

    boolean is_correct(double p1_x, double p1_y, double p2_x, double p2_y){
        if (p1_x == p2_x || p1_y == p2_y) {
            System.out.println("Incorrect data");
            return false;
        } else {
            return true;
        }
    }

    public void setA(double x, double y) {
        this.a.setX(x);
        this.a.setY(y);
    }

    public void setB(double x, double y) {
        this.b.setX(x);
        this.b.setY(y);
    }

    public Point getA() {
        return this.a;
    }

    public Point getB() {
        return this.b;
    }

    double circumference(){
        return 2*Math.abs(this.getA().getX() - this.getB().getX()) + 2*Math.abs(this.getA().getY() - this.getB().getY());
    }

    double area(){
        return Math.abs(this.getA().getX() - this.getB().getX())*Math.abs(this.getA().getY() - this.getB().getY());
    }

    double diagonal(){
        return Math.sqrt((this.getA().getX() - this.getB().getX())*(this.getA().getX() - this.getB().getX()) + (this.getA().getY() - this.getB().getY())*(this.getA().getY() - this.getB().getY()));
    }
}

public class Main {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();
        System.out.println(r1.circumference());
        System.out.println(r1.area());
        System.out.println(r1.diagonal());

        Rectangle r2 = new Rectangle(-1, -1, 1, 3);
        System.out.println(r2.circumference());
        System.out.println(r2.area());
        System.out.println(r2.diagonal());

        Rectangle r3 = new Rectangle(0, 0, 0, 1);
    }
}

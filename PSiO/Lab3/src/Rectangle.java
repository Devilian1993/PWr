class Rectangle {

    double[] p1 = new double[2];
    double[] p2 = new double[2];

    public Rectangle(){
        this.p1[0] = 0;
        this.p1[1] = 0;
        this.p2[0] = 1;
        this.p2[1] = 1;
    }

    public Rectangle(double p1_x, double p1_y, double p2_x, double p2_y){
        this.p1[0] = p1_x;
        this.p1[1] = p1_y;
        this.p2[0] = p2_x;
        this.p2[1] = p2_y;
        if (p1[0] == p2[0] || p1[1] == p2[1]){
            throw new IllegalArgumentException("Niepoprawne punkty");
        }
    }

    double circumference(){
        return 2*Math.abs(this.p1[0] - this.p2[0]) + 2*Math.abs(this.p1[1] - this.p2[1]);
    }

    double area(){
        return Math.abs(this.p1[0] - this.p2[0])*Math.abs(this.p1[1] - this.p2[1]);
    }

    double diagonal(){
        return Math.sqrt((this.p1[0] - this.p2[0])*(this.p1[0] - this.p2[0]) + (this.p1[1] - this.p2[1])*(this.p1[1] - this.p2[1]));
    }

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(0,4, 0, -1);
        System.out.println(r1.circumference());
        System.out.println(r1.area());
        System.out.println(r1.diagonal());
    }
}


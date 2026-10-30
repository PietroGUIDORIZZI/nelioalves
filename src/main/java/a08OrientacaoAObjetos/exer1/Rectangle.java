package a08OrientacaoAObjetos.exer1;

public class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height){
        this.width = width;
        this.height = height;

    }

    public double area(){
        return this.height * this.width;
    }

    public double perimeter(){
        return this.width * 2 + this.height * 2;
    }

    public double diagonal(){
        double bSq = Math.pow(this.height, 2);
        double cSq = Math.pow(this.width, 2);
        double a = bSq + cSq;
        return Math.sqrt(a);


    }
}

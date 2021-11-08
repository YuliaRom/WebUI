package org.example.lesson4;

public class Triangle {
    public static double calculateTriangleSquare(double a, double b, double c) throws Exception {
        if (a <= 0 || b <= 0 || c <= 0) throw new Exception();
        if ((a + b) <= c || (a + c) <= b || (c + b) <= a) throw new Exception();
        if (c == 'a') throw new Exception();
        double halfPerimeter = (a + b + c) / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));


    }





}

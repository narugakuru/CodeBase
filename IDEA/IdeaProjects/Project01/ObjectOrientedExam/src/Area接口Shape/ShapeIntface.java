package Area接口Shape;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

abstract class face implements Shape {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Shape rec = new rectangle(3.3, 6.5);
        Shape cir = new circle(6.9);
        Shape cy = new cylinder(2, 1, 1);

        System.out.println("面积: " + rec.getArea() + "  周长：" + rec.getCircumference());
        System.out.println("面积: " + cir.getArea() + "  周长：" + cir.getCircumference());
        //double radius=sc.nextDouble();

        double radius = Double.parseDouble(JOptionPane.showInputDialog("嘤嘤嘤输入radius："));
        cir = new circle(radius);
        JOptionPane.showMessageDialog(null, "面积: " + cir.getArea() + "  周长：" + cir.getCircumference());

        JOptionPane.showMessageDialog(null, "Cylinder面积: " + cy.getArea());
    }
}

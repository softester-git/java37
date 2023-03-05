package ru.stqa.training.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Sash");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоуголгника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p = new Point(3, 5, 6, 8);
    System.out.println("Расстояние между точками с координатами " + p.x1 + ", " + p.y1 + " и " + p.x2 + ", " + p.y2 + " составляет: " + p.distance());
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

}
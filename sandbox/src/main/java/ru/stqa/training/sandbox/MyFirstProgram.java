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

    Point p1 = new Point();
    Point p2 = new Point();
    p1.x = 3;
    p1.y = 5;
    p2.x = 6;
    p2.y = 8;
    System.out.println("Расстояние между точками с координатами " + p1.x + ", " + p1.y + " и " + p2.x + ", " + p2.y + " составляет: " + distance(p1, p2));
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.x - p1.x) + (p2.y - p1.y));
  }

}
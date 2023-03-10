package ru.stqa.training.sandbox;

//import ru.stqa.training.sandbox.Point;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Sash");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоуголгника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p1 = new Point(3, 5);
    Point p2 = new Point(6, 8);
    System.out.println("Расстояние между точками с координатами " + p1.x + ", " + p1.y + " и " + p2.x + ", " + p2.y + " составляет: " + p1.distance(p2));
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

}
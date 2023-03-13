package ru.stqa.training.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

  @Test
  public void testArea() {
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25.0);
  }

  @Test
  public void testPointDistanceA() {
    Point p1 = new Point(3, 5);
    Point p2 = new Point(6, 8);
    Assert.assertEquals(p1.distance(p2), 4.242640687119285);
  }
  @Test
  public void testPointDistanceB() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  @Test
  public void testPointDistanceC() {
    Point p1 = new Point(-6, -8);
    Point p2 = new Point(3, 5);
    Assert.assertEquals(p1.distance(p2), 15.811388300841896);
  }

  @Test
  public void testPointDistanceE() {
    Point p1 = new Point(-6, -8);
    Point p2 = new Point(-3, -5);
    Assert.assertEquals(p1.distance(p2), 4.242640687119285);
  }

}

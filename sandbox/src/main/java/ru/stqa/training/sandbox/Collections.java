package ru.stqa.training.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"};

    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
    //List<String> languages = new ArrayList<String>();
    //languages.add("Java");
    //languages.add("C#");
    //languages.add("Python");
    //languages.add("PHP");

    for (String lang : languages) {
      System.out.println("Я хочу выучить " + lang);
    }
  }
}

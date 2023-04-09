package ru.stqa.training.mantis.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() {
    app.registration().start("user1", "user1@localhost.localdomain");
  }

}

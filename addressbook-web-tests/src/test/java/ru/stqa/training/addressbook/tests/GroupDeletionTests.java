package ru.stqa.training.addressbook.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.wd.get("http://localhost/addressbook/index.php");
    app.gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    app.returnToGroupPage();
  }

  private void deleteSelectedGroups() {
    app.wd.findElement(By.name("delete")).click();
  }

  private void selectGroup() {
    app.wd.findElement(By.name("selected[]")).click();
  }

}

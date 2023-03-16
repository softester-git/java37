package ru.stqa.training.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.training.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("Group1", "Group1 header", "Group1 footer"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}

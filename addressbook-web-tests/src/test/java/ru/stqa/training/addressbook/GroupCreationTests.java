package ru.stqa.training.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Group1", "Group1 header", "Group1 footer"));
    submitGroupCreation();
    returnToGroupPage();
  }

}

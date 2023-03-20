package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.training.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("Group1", "Group1 header", "Group1 footer"));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();

    Assert.assertEquals(after, before + 1);
  }

}

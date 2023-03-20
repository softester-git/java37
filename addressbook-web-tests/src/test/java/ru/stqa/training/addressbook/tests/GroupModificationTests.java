package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    GroupData groupNew = new GroupData("New Name", "New Header", "New Footer");

    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(groupNew);
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData groupEdit = new GroupData(before.get(before.size() - 1).getId(), "Edited Name", "Edited Header", "EditedFooter");
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(groupEdit);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(groupEdit);

    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}

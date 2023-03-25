package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.training.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    GroupData group = new GroupData()
            .withName("GroupName")
            .withHeader("GroupHeader")
            .withFooter("GroupFooter");

    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(group);
    }
  }

  @Test
  public void testGroupDeletion() {
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedGroup);

    Assert.assertEquals(before, after);
  }

}

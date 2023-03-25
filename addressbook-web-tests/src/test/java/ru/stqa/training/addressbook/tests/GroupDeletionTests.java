package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.training.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    GroupData group = new GroupData()
            .withName("GroupName")
            .withHeader("GroupHeader")
            .withFooter("GroupFooter");

    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(group);
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;

    app.group().delete(index);
    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}

package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    GroupData groupNew = new GroupData("Name", "Header", "Footer");
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(groupNew);
    }
  }

  @Test
  public void testGroupModification() {
    GroupData groupEdit = new GroupData("NameModified", "HeaderModified", "FooterModified");
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    app.group().modify(index, groupEdit);
    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(groupEdit);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}

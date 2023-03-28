package ru.stqa.training.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.training.addressbook.model.GroupData;
import ru.stqa.training.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new GroupData().withName("name1").withHeader("header1").withFooter("footer1")});
    list.add(new Object[] {new GroupData().withName("name2").withHeader("header2").withFooter("footer2")});
    list.add(new Object[] {new GroupData().withName("name3").withHeader("header3").withFooter("footer3")});
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() {
    GroupData group = new GroupData()
            .withName("GroupName'")
            .withHeader("GroupHeader")
            .withFooter("GroupFooter");

    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }

}

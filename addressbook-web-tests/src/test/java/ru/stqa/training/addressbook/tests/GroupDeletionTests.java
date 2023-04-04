package ru.stqa.training.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.training.addressbook.model.GroupData;
import ru.stqa.training.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      GroupData group = new GroupData()
              .withName("GroupName")
              .withHeader("GroupHeader")
              .withFooter("GroupFooter");
      app.goTo().groupPage();
      app.group().create(group);
    }
  }

  @Test
  public void testGroupDeletion() {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListInUi();
  }

}

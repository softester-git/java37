package ru.stqa.training.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.GroupData;
import ru.stqa.training.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {


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
  public void testGroupModification() {
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("GroupNameEdited")
            .withHeader("GroupHeaderEdited")
            .withFooter("GroupFooterEdited");

    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }

}

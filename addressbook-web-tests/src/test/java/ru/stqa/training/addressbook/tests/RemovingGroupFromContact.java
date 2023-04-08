package ru.stqa.training.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;
import ru.stqa.training.addressbook.model.Contacts;
import ru.stqa.training.addressbook.model.GroupData;
import ru.stqa.training.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemovingGroupFromContact extends TestBase {

  private int idContact;
  private int idGroup;

  @BeforeMethod
  public void ensurePreconditionsContact() {
    if (app.db().contacts().size() == 0) {
      ContactData contact = new ContactData()
              .withFirstName("FirstName")
              .withLastName("LastName")
              .withAddress("Address")
              .withHomePhone("1000")
              .withEmail("test@email.test");
      File photo = new File("src" + File.separator
              + "test" + File.separator
              + "resources" + File.separator
              + "stru.jpeg");
      contact.withPhoto(photo);
      app.goTo().contactPage();
      app.contact().create(contact);
    }
  }

  @BeforeMethod
  public void ensurePreconditionsGroup() {
    if (app.db().groups().size() == 0) {
      GroupData group = new GroupData()
              .withName("GroupName")
              .withHeader("GroupHeader")
              .withFooter("GroupFooter");
      app.goTo().groupPage();
      app.group().create(group);
    }
  }

  @BeforeMethod
  public void ensurePreconditionsRelation() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    boolean findSuccess = false;
    for (ContactData contact : contacts) {
      System.out.println("ID Contact: " + contact.getId());
      Groups groupsInContact = contact.getGroups();
      System.out.println("Groups in contact: " + groupsInContact);
      if (groupsInContact.size() > 0) {
        idContact = contact.getId();
        idGroup = groupsInContact.iterator().next().getId();
        findSuccess = true;
        break;
      }
    }
    if (!findSuccess) {
      System.out.println("!findSuccess");

      idContact = contacts.iterator().next().getId();
      idGroup = groups.iterator().next().getId();

      app.goTo().contactPage();
      app.contact().selectContactById(idContact);
      app.contact().selectGroupForAdd(idGroup);
      app.contact().submitGroupAdding();

      assertThat(app.db().isContactInGroup(idContact, idGroup), equalTo(true));
    }
  }

  @Test
  public void testRemovingGroupFromContact() {

    System.out.println("idContact: " + idContact);
    System.out.println("idGroup: " + idGroup);

    app.goTo().contactPage();
    new Select(app.wd.findElement(By.name("group"))).selectByValue(String.valueOf(idGroup));
    app.contact().selectContactById(idContact);
    app.contact().submitGroupRemoving();

    assertThat(app.db().isContactInGroup(idContact, idGroup), equalTo(false));
  }
}

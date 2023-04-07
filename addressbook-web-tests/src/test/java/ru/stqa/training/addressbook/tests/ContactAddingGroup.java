package ru.stqa.training.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;
import ru.stqa.training.addressbook.model.Contacts;
import ru.stqa.training.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddingGroup extends TestBase {

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

  @Test(enabled = false)
  public void testContactAddingGroup() {
    Contacts contacts = app.db().contacts();
    ContactData selectedContact = contacts.iterator().next();
    app.goTo().contactPage();

//    app.contact().delete(deletedContact);
//    assertThat(app.contact().getContactCount(), equalTo(before.size() - 1));
//    Contacts after = app.db().contacts();
//    assertThat(after, equalTo(before.without(deletedContact)));
//    verifyContactListInUi();
  }

}

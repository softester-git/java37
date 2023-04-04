package ru.stqa.training.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;
import ru.stqa.training.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
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

  @Test
  public void testContactModification() {
    app.goTo().contactPage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    File photo = new File("src" + File.separator
            + "test" + File.separator
            + "resources" + File.separator
            + "edit.gif");
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("FirstNameEdited")
            .withLastName("LastNameEdited")
            .withAddress("AddressEdited")
            .withHomePhone("2000")
            .withEmail("test@emailEdited.test")
            .withPhoto(photo);

    app.contact().modify(contact);
    assertThat(app.contact().getContactCount(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUi();
  }

}

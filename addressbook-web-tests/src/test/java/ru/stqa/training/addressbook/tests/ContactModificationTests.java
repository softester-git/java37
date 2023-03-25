package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    ContactData contact = new ContactData()
            .withFirstName("FirstName")
            .withLastName("LastName")
            .withAddress("Address")
            .withHomePhone("1000")
            .withEmail("test@email.test");

    app.goTo().contactPage();
    if (app.contact().list().size() == 0) {
      app.contact().create(contact);
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId())
            .withFirstName("FirstNameEdited")
            .withLastName("LastNameEdited")
            .withAddress("AddressEdited")
            .withHomePhone("2000")
            .withEmail("test@emailEdited.test");

    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}

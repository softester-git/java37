package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;
import ru.stqa.training.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    ContactData contact = new ContactData(
            "FirstName",
            "LastName",
            "Address",
            "1000",
            "test@email.test");
    app.getNavigationHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(contact);
    }
  }

  @Test
  public void testContactDeletion() {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;

    app.getContactHelper().selectContact(index);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}

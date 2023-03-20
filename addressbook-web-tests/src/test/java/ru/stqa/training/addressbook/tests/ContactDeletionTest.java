package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("FirstName", "LastName", "Address", "1000", "test@email.test"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size() - 1);
  }

}

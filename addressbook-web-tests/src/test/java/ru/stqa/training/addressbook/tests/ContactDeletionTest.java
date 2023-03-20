package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("FirstName", "LastName", "Address", "1000", "test@email.test"));
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().returnToContactPage();
    int after = app.getContactHelper().getContactCount();

    Assert.assertEquals(after, before - 1);
  }

}

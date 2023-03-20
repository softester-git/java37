package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.training.addressbook.model.ContactData;
import ru.stqa.training.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().gotoContactPage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("FirstName", "LastName", "Address", "1000", "test@email.test"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size() + 1);
  }

}

package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.training.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getNavigationHelper().gotoContactPage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("FirstName", "LastName", "Address", "1000", "test@email.test"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToContactPage();
    int after = app.getContactHelper().getContactCount();

    Assert.assertEquals(after, before + 1);
  }

}

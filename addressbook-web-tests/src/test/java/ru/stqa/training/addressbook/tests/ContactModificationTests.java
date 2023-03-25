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
  public void testContactModification() {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    ContactData contact = new ContactData(
            before.get(index).getId(),
            "FirstEditName",
            "LastEditName",
            "AddressEdit",
            "2000",
            "testedit@email.test");

    app.getContactHelper().modifyContact(index, contact);
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}

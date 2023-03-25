package ru.stqa.training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.training.addressbook.model.ContactData;

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
    app.goTo().contactPage();
    if (app.contact().list().size() == 0) {
      app.contact().create(contact);
    }
  }

  @Test
  public void testContactDeletion() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;

    app.contact().delete(index);
    List<ContactData> after = app.contact().list();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }

}

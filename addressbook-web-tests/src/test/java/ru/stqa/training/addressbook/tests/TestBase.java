package ru.stqa.training.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.training.addressbook.appmanager.ApplicationManager;
import ru.stqa.training.addressbook.model.ContactData;
import ru.stqa.training.addressbook.model.Contacts;
import ru.stqa.training.addressbook.model.GroupData;
import ru.stqa.training.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  public void verifyGroupListInUi() {
    if (Boolean.getBoolean("verifyUI")) {
      System.out.println("!!!verifyGroupListInUi!!!");

      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();

      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void verifyContactListInUi() {
    if (Boolean.getBoolean("verifyUI")) {
      System.out.println("!!!verifyContactListInUi!!!");

      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();

      assertThat(uiContacts, equalTo(dbContacts.stream()
              .map((c) -> new ContactData()
                      .withId(c.getId())
                      .withFirstName(c.getFirstName())
                      .withLastName(c.getLastName())
                      .withAddress(c.getAddress())
                      .withAllEmails(app.contact().mergeEmails(c))
                      .withAllPhones(app.contact().mergePhones(c)))
              .collect(Collectors.toSet())));
    }
  }

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));

  }
  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test " + m.getName());
  }
}

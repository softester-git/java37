package ru.stqa.training.mantis.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.training.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

  private final String user = "resetuser02";
  private final String curPassword = "password";
  private final String newPassword = "password001";
  private final String email = user + "@s210";

  @BeforeMethod
  public void ensurePreconditions() throws MessagingException {
    if (app.james().doesUserExist(user)) {
      app.james().deleteUser(user);
    }
    app.james().createUser(user, curPassword);
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.james().waitForMail(user, curPassword, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, curPassword);
  }

  @Test
  public void testResetPassword() throws IOException, MessagingException {
    app.reset().start("administrator", "root");
    app.reset().resetUserPassword(user);
    List<MailMessage> mailMessages = app.james().waitForMail(user, curPassword, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.reset().finish(confirmationLink, user, newPassword);
    assertTrue(app.newSession().login(user, newPassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}

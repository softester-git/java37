package ru.stqa.training.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.training.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.util.List;

public class ResetHelper extends HelperBase {

  public ResetHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String password) {
    type(By.name("username"), username);
    click(By.cssSelector("input[value='Вход']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Вход']"));
  }

  public void resetUserPassword(String username) {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
    click(By.linkText(username));
    click(By.cssSelector("input[value='Сбросить пароль']"));
  }

  public void finish(String confirmationLink, String username, String newPassword) {
    wd.get(confirmationLink);
    type(By.name("realname"), username);
    type(By.name("password"), newPassword);
    type(By.name("password_confirm"), newPassword);
    click(By.cssSelector("button[type=submit]"));
  }
}

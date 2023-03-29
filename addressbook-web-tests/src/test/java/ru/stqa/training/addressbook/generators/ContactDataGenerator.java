package ru.stqa.training.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.training.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;
  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String args[]) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      //saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      //saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unknown format: " + format);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int i = 0; i < count; i ++) {
      contacts.add(new ContactData()
              .withFirstName("TestFirstName" + i)
              .withLastName("TestLastName" + i)
              .withAddress("TestAddress" + i)
              .withHomePhone("10000")
              .withEmail("test" + i + "@mail.test"));
    }
    return contacts;
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s\n"
              , contact.getFirstName()
              , contact.getLastName()
              , contact.getAddress()
              , contact.getHomePhone()
              , contact.getEmail()));
    }
    writer.close();
  }
}

package ru.stqa.training.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.training.addressbook.model.ContactData;
import ru.stqa.training.addressbook.model.Contacts;
import ru.stqa.training.addressbook.model.Groups;
import ru.stqa.training.addressbook.model.GroupData;

import java.util.List;
import java.util.stream.Collectors;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData where deprecated='0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated='0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public boolean isContactInGroup(int idContact, int idGroup) {
    Contacts contacts = contacts();
    List<ContactData> contact = contacts
            .stream()
            .filter(c -> c.getId() == idContact)
            .collect(Collectors.toList());
    Groups groupsInContact = contact.get(0).getGroups();

    Groups groups = groups();
    List<GroupData> group = groups
            .stream()
            .filter(g -> g.getId() == idGroup)
            .collect(Collectors.toList());

    return groupsInContact.contains(group.get(0));
  }
}

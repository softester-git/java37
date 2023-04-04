package ru.stqa.training.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private String firstName = "";
  @Expose
  @Column(name = "lastname")
  private String lastName = "";
  @Transient
  private String group = "";
  @Expose
  @Column(name = "address")
  private String address = "";
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone = "";
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone = "";
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone = "";
  @Transient
  private String allPhones = "";
  @Expose
  @Column(name = "email")
  private String email = "";
  @Column(name = "email2")
  private String email2 = "";
  @Column(name = "email3")
  private String email3 = "";
  @Transient
  private String allEmails = "";
  //@Column(name = "photo")
  //@Type(type = "text")
  @Transient
  private String photo = "";


  public int getId() {
    return id;
  }
  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public String getAddress() {
    return address;
  }
  public String getHomePhone() {
    return homePhone;
  }
  public String getMobilePhone() {
    return mobilePhone;
  }
  public String getWorkPhone() {
    return workPhone;
  }
  public String getAllPhones() {
    return allPhones;
  }
  public String getEmail() {
    return email;
  }
  public String getEmail_2() {
    return email2;
  }
  public String getEmail_3() {
    return email3;
  }
  public String getAllEmails() { return allEmails; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id
            && Objects.equals(firstName, that.firstName)
            && Objects.equals(lastName, that.lastName)
            && Objects.equals(group, that.group)
            && Objects.equals(address, that.address)
            && Objects.equals(homePhone, that.homePhone)
            && Objects.equals(mobilePhone, that.mobilePhone)
            && Objects.equals(workPhone, that.workPhone)
            && Objects.equals(allPhones, that.allPhones)
            && Objects.equals(email, that.email)
            && Objects.equals(email2, that.email2)
            && Objects.equals(email3, that.email3)
            && Objects.equals(allEmails, that.allEmails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, group, address, homePhone, mobilePhone, workPhone, allPhones, email, email2, email3, allEmails);
  }

  public File getPhoto() {
    return new File(photo);
  }
  public String getGroup() { return group; }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", group='" + group + '\'' +
            ", address='" + address + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", allEmails='" + allEmails + '\'' +
            ", photo='" + photo + '\'' +
            '}';
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail_2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail_3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

}

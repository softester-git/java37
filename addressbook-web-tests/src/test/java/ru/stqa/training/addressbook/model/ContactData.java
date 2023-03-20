package ru.stqa.training.addressbook.model;

import java.util.Objects;

public class ContactData {
  private int id;
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String homePhone;
  private final String email;

  public ContactData(int id, String firstName, String lastName, String address, String homePhone, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.homePhone = homePhone;
    this.email = email;
  }


  public ContactData(String firstName, String lastName, String address, String homePhone, String email) {
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.homePhone = homePhone;
    this.email = email;
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

  public String getEmail() {
    return email;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, address);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  public int getId() {
    return id;
  }
}

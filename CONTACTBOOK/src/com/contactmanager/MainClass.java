package com.contactmanager;
import com.contactmanager.control.ContactControImple;
import com.contactmanager.model.ContactClass;
import java.util.List;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        ContactControImple contactControl = new ContactControImple();
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	System.out.println("....WELCOME TO CONTACTBOOK....");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Search Contact by ID");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Enter First Name:");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter Last Name:");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter Phone Number:");
                    String phoneNumber = scanner.nextLine();
                   

                    ContactClass contact = new ContactClass(0, firstName, lastName, phoneNumber);
                    contactControl.addContact(contact);
                    break;

                case 2:
                    List<ContactClass> contacts = contactControl.getAllContacts();
                    if (contacts.isEmpty()) {
                        System.out.println("No contacts found.");
                    } else {
                        for (ContactClass c : contacts) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Enter Contact ID:");
                    int searchId = scanner.nextInt();
                    ContactClass foundContact = contactControl.getContactById(searchId);
                    if (foundContact != null) {
                        System.out.println(foundContact);
                    } else {
                        System.out.println("Contact not found!");
                    }
                    break;

                case 4:
                    System.out.println("Enter Contact ID to Update:");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.println("Enter New First Name:");
                    String newFirstName = scanner.nextLine();
                    System.out.println("Enter New Last Name:");
                    String newLastName = scanner.nextLine();
                    System.out.println("Enter New Phone Number:");
                    String newPhoneNumber = scanner.nextLine();
                    

                    ContactClass updatedContact = new ContactClass(updateId, newFirstName, newLastName, newPhoneNumber);
                    contactControl.updateContact(updateId, updatedContact);
                    break;

                case 5:
                    System.out.println("Enter Contact ID to Delete:");
                    int deleteId = scanner.nextInt();
                    contactControl.deleteContact(deleteId);
                    break;

                case 6:
                    System.out.println("Exiting Contact Manager...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

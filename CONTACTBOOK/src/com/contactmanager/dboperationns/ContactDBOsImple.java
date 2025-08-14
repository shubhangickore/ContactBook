package com.contactmanager.dboperationns;

import com.contactmanager.model.ContactClass;
import com.contactmanager.util.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDBOsImple implements ContactDBOs {

    @Override
    public void addContact(ContactClass contact) {
        // Retrieve the phone number from the ContactClass object
        String phoneNumber = contact.getPhoneNumber();  // Assuming phoneNumber is stored as a String

        // Check if the phone number is null or empty
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            System.out.println("Insertion unsuccessful: Phone number cannot be null or empty.");
            return;
        }

        // Validate that the phone number contains exactly 10 digits
        if (!phoneNumber.matches("\\d{10}")) {
            System.out.println("Insertion unsuccessful: Invalid phone number. It must contain exactly 10 digits.");
            return;
        }

        // Proceed to add the contact to the database only if the phone number is valid
        try (Connection con = DBconnection.getDBConnection()) {
            String query = "INSERT INTO contacts (first_name, last_name, phone_number) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.setString(3, phoneNumber); 
            ps.executeUpdate();
            System.out.println("Contact added successfully!");
        } catch (SQLException e) {
            System.out.println("Insertion successful!!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteContact(int id) {
        try (Connection con = DBconnection.getDBConnection()) {
            String query = "DELETE FROM contacts WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Contact deleted successfully!");
            } else {
                System.out.println("No contact found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContact(int id, ContactClass updatedContact) {
        // Retrieve the phone number from the ContactClass object
        String phoneNumber = updatedContact.getPhoneNumber();  // Assuming phoneNumber is a String

        // Validate that the phone number is exactly 10 digits
        if (phoneNumber != null && phoneNumber.matches("\\d{10}")) {
            try (Connection con = DBconnection.getDBConnection()) {
                String query = "UPDATE contacts SET first_name = ?, last_name = ?, phone_number = ? WHERE id = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, updatedContact.getFirstName());
                ps.setString(2, updatedContact.getLastName());
                ps.setString(3, phoneNumber);  // Using String for phone number
                ps.setInt(4, id);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Contact updated successfully!");
                } else {
                    System.out.println("No contact found with ID: " + id);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // If phone number is invalid
            System.out.println("Invalid phone number. It must be exactly 10 digits.");
        }
    }

    @Override
    public ContactClass getContactById(int id) {
        ContactClass contact = null;
        try (Connection con = DBconnection.getDBConnection()) {
            String query = "SELECT * FROM contacts WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                contact = new ContactClass(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number")  
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }

    @Override
    public List<ContactClass> getAllContacts() {
        List<ContactClass> contacts = new ArrayList<>();
        try (Connection con = DBconnection.getDBConnection()) {
            String query = "SELECT * FROM contacts";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                contacts.add(new ContactClass(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number")  
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}

package com.contactmanager.dboperationns;

import com.contactmanager.model.ContactClass;
import java.util.List;

public interface ContactDBOs {
    void addContact(ContactClass contact);
    void deleteContact(int id);
    void updateContact(int id, ContactClass updatedContact);
    ContactClass getContactById(int id);
    List<ContactClass> getAllContacts();
}

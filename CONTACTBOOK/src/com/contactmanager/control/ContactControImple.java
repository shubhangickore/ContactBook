package com.contactmanager.control;

import com.contactmanager.dboperationns.ContactDBOs;
import com.contactmanager.dboperationns.ContactDBOsImple;
import com.contactmanager.model.ContactClass;

import java.util.List;

public class ContactControImple implements ContactControl {

    private final ContactDBOs contactDBOs;

    public ContactControImple() 
    {
        this.contactDBOs = new ContactDBOsImple();
    }

    @Override
    public void addContact(ContactClass contact) {
        contactDBOs.addContact(contact);
     
    }

    @Override
    public List<ContactClass> getAllContacts() {
        return contactDBOs.getAllContacts();
    }

    @Override
    public ContactClass getContactById(int id) {
        return contactDBOs.getContactById(id);
    }

    @Override
    public void updateContact(int id, ContactClass contact) {
        contactDBOs.updateContact(id, contact);
        
    }

    @Override
    public void deleteContact(int id) {
        contactDBOs.deleteContact(id);
       
    }
}

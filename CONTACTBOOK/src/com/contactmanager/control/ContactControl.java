package com.contactmanager.control;

import com.contactmanager.model.ContactClass;

import java.util.List;

public interface ContactControl {
	void addContact(ContactClass contact);
	List<ContactClass> getAllContacts();
	ContactClass getContactById(int id);
	void updateContact(int id, ContactClass contact);
	void deleteContact(int id);
}

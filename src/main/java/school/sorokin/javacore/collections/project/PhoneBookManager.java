package school.sorokin.javacore.collections.project;

import school.sorokin.javacore.collections.project.myexceptions.ContactAlreadyExistsException;
import school.sorokin.javacore.collections.project.myexceptions.ContactNotFoundException;

import java.util.*;

public class PhoneBookManager {
    private final List<Contact> contacts;

    public PhoneBookManager() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact newContact) {
        if (isExisted(newContact)) {
            throw new ContactAlreadyExistsException("Такой контакт уже есть!");
        }
        contacts.add(newContact);
    }

    public void deleteContact(String name, String phone) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getName().equals(name) && contact.getPhone().equals(phone)) {
                iterator.remove();
                return;
            }
        }
        throw new ContactNotFoundException("Контакт с таким именем и телефоном не найден.");
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    public Contact searchContact(String name, String phone) {
        Contact existedContact = null;
        for (Contact c : contacts) {
            if (c.getName().equals(name) && c.getPhone().equals(phone)) {
                return c;
            }
        }
        return existedContact;
    }

    public List<Contact> getContactsByGroup(Group group) {
        List<Contact> groupContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getGroup().equals(group)) {
                groupContacts.add(contact);
            }
        }
        return groupContacts;
    }

    public boolean isExisted(Contact contactNamePhone) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            if (contact.getName().equals(contactNamePhone.getName())
                    && contact.getPhone().equals(contactNamePhone.getPhone())) {
                return true;
            }
        }
        return false;
    }
}

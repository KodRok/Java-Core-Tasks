package school.sorokin.javacore.collections.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.sorokin.javacore.collections.project.myexceptions.ContactAlreadyExistsException;
import school.sorokin.javacore.collections.project.myexceptions.ContactNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneBookManagerTest {
    private PhoneBookManager phoneBookManager;

    @BeforeEach
    void setUp() {
        phoneBookManager = new PhoneBookManager();
    }

    @Test
    void addContactShouldContactAdded() {
        Contact newContact = new Contact
                ("Bob Dilan", "111111", "test@test.com", Group.WORK);
        phoneBookManager.addContact(newContact);
        Assertions.assertEquals(1, phoneBookManager.getAllContacts().size());
        Contact addedContact = phoneBookManager.getAllContacts().get(0);
        Assertions.assertEquals(newContact, addedContact);
    }

    @Test
    void addContactShouldThrowsExceptionIfContactIsAlreadyExist() {
        Contact newContact = new Contact
                ("Bob Dilan", "111111", "test@test.com", Group.WORK);
        phoneBookManager.addContact(newContact);
        Contact sameContact = new Contact
                ("Bob Dilan", "111111");
        assertThrows(ContactAlreadyExistsException.class, () -> {
            phoneBookManager.addContact(sameContact);
        });
        Assertions.assertEquals(1, phoneBookManager.getAllContacts().size());
    }

    @Test
    void deleteContactShouldThrowsExceptionIfContactNotExisted() {
        Contact newContact = new Contact
                ("Bob Dilan", "111111", "test@test.com", Group.WORK);
        phoneBookManager.addContact(newContact);

        assertThrows(ContactNotFoundException.class, () -> {
            phoneBookManager.deleteContact("Leonardo", "2356");
        });
        Assertions.assertEquals(1, phoneBookManager.getAllContacts().size());
    }

    @Test
    void searchContactShouldGetExistedContact() {
        phoneBookManager.addContact(
                new Contact("Bob Dilan", "111111", "test1@test.com", Group.WORK));
        phoneBookManager.addContact(
                new Contact("Anna Volkova", "222222", "test2@test.com", Group.WORK));
        phoneBookManager.addContact(
                new Contact("Ivan Ivanov", "333333", "test3@test.com", Group.WORK));
        Contact foundContact = phoneBookManager.searchContact("Bob Dilan", "111111");
        Assertions.assertNotNull(foundContact);
        Assertions.assertEquals("Bob Dilan", foundContact.getName());
        Assertions.assertEquals("111111", foundContact.getPhone());
        Assertions.assertEquals("test1@test.com", foundContact.getEmail());
        Assertions.assertEquals(Group.WORK, foundContact.getGroup());
    }
}
package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {

    ContactManager contactManager;
    @BeforeAll
    public void setupAll(){ //it must be a static method. It's executed for whole test class once
        System.out.println("Should print before all test cases");
    }

    @BeforeEach
    public void Setup(){ //each test method will have its own copy of contactManager
        contactManager = new ContactManager();
    }

    @Test
    public void shouldCreateContact() {
        contactManager.addContact("Vineet", "Raj", "0956051722");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty()); //we expect contact list shouldn't be empty
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should not create contact when first name is null")
    public void shouldThrowRunTimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "alam", "0987654321");
        });
    }

    @Test
    @DisplayName("Should not create contact when last name is null")
    public void shouldThrowRunTimeExceptionWhenLastNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("vikash", null, "0987654321");
        });
    }

    @Test
    @DisplayName("Should not create contact when Phone number is null")
    public void shouldThrowRunTimeExceptionWhenPhoneNumberIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Raju", "Singh", null);
        });
    }

    /*Assumptions*/
    @Test
    @DisplayName("Test contact creation on development machine")
    public void shouldCreateContactOnDev() {
        Assumptions.assumeTrue("TEST".equals(System.getProperty("ENV")));
        contactManager.addContact("Vineet", "Raj", "0956051722");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty()); //we expect contact list shouldn't be empty
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    /*Conditional Executions*/
    @Test
    @DisplayName("should not run on windows")
    @DisabledOnOs(value=OS.WINDOWS,disabledReason = "no need to run on windows")
    public void shouldNotCreateContactOnWindows() {
        contactManager.addContact("Vikash", "Raj", "0955621478");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty()); //we expect contact list shouldn't be empty
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("should not run on Mac os")
    @DisabledOnOs(value=OS.MAC,disabledReason = "no need to run on Mac")
    public void shouldNotCreateContactsOnMac() {
        contactManager.addContact("Rajoo", "Jaiswal", "0944621478");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty()); //we expect contact list shouldn't be empty
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @AfterEach
    public void tearDown(){
        System.out.println("should be executed after each test");
    }
    @AfterAll
    public void tearDownAll() { //must be static
        System.out.println("should be executed at the end");
    }
}
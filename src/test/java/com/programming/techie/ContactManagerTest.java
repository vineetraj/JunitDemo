package com.programming.techie;

import org.junit.jupiter.api.*;

class ContactManagerTest {

    ContactManager contactManager;
    @BeforeAll
    public static void setupAll(){ //it must be a static method. It's executed for whole test class once
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

    @AfterEach
    public void tearDown(){
        System.out.println("should be executed after each test");
    }
    @AfterAll
    public static void tearDownAll() { //must be static
        System.out.println("should be executed at the end");
    }
}
# zacci
###### \java\seedu\address\logic\commands\AddCommandTest.java
``` java
        @Override
        public void login(String username, int securityLevel){};

        @Override
        public void logout(){};

        @Override
        public Session getSession() {
            return new Session();
        };

        @Override
        public String getSessionDetails() {
            return "";
        }

        @Override
        public boolean attemptLogin(String username, String password) {
            return true;
        }

        @Override
        public int getSecurityLevel() {
            return 5;
        }

        @Override
        public void addUser(User user) {};

        @Override
        public void deleteUser(String user) {};

        @Override
        public boolean checkIsLoggedIn() {
            return true;
        };
```
###### \java\seedu\address\logic\LogicManagerTest.java
``` java
    public LogicManagerTest() {
        model.login("maxSecurityLevelUser", 999);
    }
```
###### \java\seedu\address\TestApp.java
``` java
    public Model getModel() {
        Model copy = new ModelManager((model.getAddressBook()), new UserPrefs());
        model.login("maxSecurityLevelUser", 999);
        ModelHelper.setFilteredList(copy, model.getFilteredPersonList());
        return copy;
    }

    //@author sarahgoh97
    public Model getNewModel() {
        Model copy = new ModelManager((model.getAddressBook()), new UserPrefs());
        model.login("maxSecurityLevelUser", 999);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        ModelHelper.setFilteredList(copy, model.getFilteredPersonList());
        return copy;
    }
```
###### \java\seedu\address\testutil\TypicalUsers.java
``` java
package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.user.User;
import seedu.address.model.user.exceptions.UserAlreadyExistsException;

/**
 * A utility class containing a list of Users, usernames, passwords and security levels to be used in tests.
 */
public class TypicalUsers {
    public static final String VALID_USERNAME = "prisonwarden99";
    public static final String VALID_PASSWORD = "password99";
    public static final int VALID_MINIMUM_SECURITY_LEVEL = 1;
    public static final int VALID_MAXIMUM_SECURITY_LEVEL = 3;
    public static final String INVALID_USERNAME = "prison warden"; //cannot have spaces
    public static final String INVALID_PASSWORD = "password1("; //cannot have "("
    public static final int INVALID_SECURITY_LEVEL = 4;

    public static final User PRISONWARDEN = new User("prisonwarden", "password3", 3);
    public static final User PRISONLEADER = new User("prisonleader", "password2", 2);
    public static final User PRISONGUARD = new User("prisonguard", "password1", 1);

    //Manually Added
    public static final User PRISONWARDEN2 = new User("prisonwarden2", "password3", 3);
    public static final User PRISONLEADER2 = new User("prisonleader2", "password2", 2);
    public static final User PRISONGUARD2 = new User("prisonguard2", "password1", 1);


    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalUserDatabase() {
        AddressBook ab = new AddressBook();
        for (User user : getTypicalUsers()) {
            try {
                ab.addUser(user);
            } catch (UserAlreadyExistsException e) {
                throw new AssertionError("not possible");
            }
        }
        return ab;
    }

    public static List<User> getTypicalUsers() {
        return new ArrayList<User>(Arrays.asList(PRISONWARDEN, PRISONLEADER, PRISONGUARD));
    }
}
```

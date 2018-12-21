import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class UserTasksTest {

    private UserTasks userTasks = new UserTasks();

    @Before
    public void init(){
        userTasks.addUser(new User("Rishabh", 29, "12, los angles",
                1, Arrays.asList("A", "B", "C", "D")));
        userTasks.addUser(new User("Richard", 21, "14, los angles",
                2, Arrays.asList("A", "B", "C", "D")));
        userTasks.addUser(new User("Denish", 23, "13, los angles",
                3, Arrays.asList("A", "B", "C", "D")));
    }

//    @AfterClass
//    public static void tearDown() throws Exception {
//        new File(UserTasks.USERS_FILE_NAME_TEST).delete();
//    }

    @Test
    public void addUniqueIdUserSuccessfully() {
        userTasks.addUser(new User("Gilfoyl", 25, "12, los angles",
                4, Arrays.asList("A", "B", "C", "D")));
        assertEquals("adding 1 more user to list", 4, userTasks.getUsers().size());
    }

    @Test
    public void addRedundantIdUserShouldFail() {
        userTasks.addUser(new User("DDDDenish", 23, "12, los angles",
                3, Arrays.asList("A", "B", "C", "D")));
        assertEquals("adding 1 redundant user to list", 3, userTasks.getUsers().size());
    }

    @Test(expected = NullPointerException.class)
    public void addEmptyUserShouldGiveException() {
        userTasks.addUser(new User());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInvalidUserDetailShouldGiveException() {
        userTasks.addUser(new User("", -23, "12, los angles",
                3, Arrays.asList("A", "B", "C", "D")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addUserWithNot4CoursesShouldGiveError() {
        userTasks.addUser(new User("Gilfoyl", 25, "12, los angles",
                4, Arrays.asList("A", "B")));
    }

    @Test
    public void showUserInAscendingOrderOfAge() {
        TreeSet<User> userTreeSet = userTasks.showUsers("age", "a");
        assertTrue("Age of last user is greater than that of first",
                userTreeSet.first().getAge() <= userTreeSet.last().getAge());
    }

    @Test
    public void showUserInAscendingOrderOfAddress() {
        TreeSet<User> userTreeSet = userTasks.showUsers("address", "a");
        assertTrue("Address of last user is greater than that of first",
                userTreeSet.first().getAddress().compareTo(userTreeSet.last().getAddress()) <= 0);
    }

    @Test
    public void showUserInAscendingOrderOfRollNo() {
        TreeSet<User> userTreeSet = userTasks.showUsers("rollNo", "a");
        assertTrue("RollNo of last user is greater than that of first",
                userTreeSet.first().getRollNo() < userTreeSet.last().getRollNo());
    }

    @Test
    public void showUserInAscendingOrderOfName() {
        TreeSet<User> userTreeSet = userTasks.showUsers("name", "a");
        assertTrue("Name of last user is greater than that of first",
                userTreeSet.first().getName().compareTo(userTreeSet.last().getName()) <= 0);
    }

    @Test
    public void showUserInDescendingOrderOfAge() {
        TreeSet<User> userTreeSet = userTasks.showUsers("age", "d");
        assertTrue("Age of last user is greater than that of first",
                userTreeSet.first().getAge() >= userTreeSet.last().getAge());
    }

    @Test
    public void showUserInDescendingOrderOfAddress() {
        TreeSet<User> userTreeSet = userTasks.showUsers("address", "d");
        assertTrue("Address of last user is greater than that of first",
                userTreeSet.first().getAddress().compareTo(userTreeSet.last().getAddress()) >= 0);
    }

    @Test
    public void showUserInDescendingOrderOfRollNo() {
        TreeSet<User> userTreeSet = userTasks.showUsers("rollNo", "d");
        assertTrue("RollNo of last user is greater than that of first",
                userTreeSet.first().getRollNo() > userTreeSet.last().getRollNo());
    }

    @Test
    public void showUserInDescendingOrderOfName() {
        TreeSet<User> userTreeSet = userTasks.showUsers("name", "d");
        assertTrue("Name of last user is greater than that of first",
                userTreeSet.first().getName().compareTo(userTreeSet.last().getName()) >= 0);
    }

    @Test
    public void removeValidUser() {
        userTasks.removeUser(1);
        assertEquals("size of user set after removing 1 user", 2, userTasks.getUsers().size());
    }

    @Test(expected = NoSuchElementException.class)
    public void removeUserWhichIsNotPresent() {
        userTasks.removeUser(100);
    }

    @Test
    public void checkIfUserDetailsSavedToFile() throws IOException {
        userTasks.saveUserDetailsToFile(UserTasks.USERS_FILE_NAME_TEST);
        assertNotEquals("length of file after saving shouldn't be zero",
                0, new File(UserTasks.USERS_FILE_NAME_TEST).length());
    }

}
import java.io.*;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class UserTasks {

    private TreeSet<User> users = new TreeSet<>();
    public static final String USERS_FILE_NAME = "userDetails.ser";
    public static final String USERS_FILE_NAME_TEST = "userDetailsTest.ser";

    public TreeSet<User> getUsers() {
        return users;
    }

    public void setUsers(TreeSet<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        if (user == null){
            throw new NullPointerException("User information is empty. Please provide details!!");
        }else if (user.getRollNo() < 0 || user.getAge() < 0 || user.getName().equals("") ||
                user.getAddress().equals("")){
            throw new IllegalArgumentException("User info are inappropriate. Please check and try again!!");
        }else if (user.getCourseList().size() != 4){
            throw new IllegalArgumentException("User should register 4 courses. Please try again!!");
        }
        users.add(user);
        System.out.println("User details successfully added");
    }

    public TreeSet<User> showUsers(String criteria, String order){
        TreeSet<User> userTreeSet;
        switch (criteria.toLowerCase()){
            case "age":
                userTreeSet = order.equals("a") ?
                        new TreeSet<>(User.ageAscendingComparator) : new TreeSet<>(User.ageDescendingComparator);
                userTreeSet.addAll(users);
                return userTreeSet;
            case "address":
                userTreeSet = order.equals("a") ?
                        new TreeSet<>(User.addressAscendingComparator) : new TreeSet<>(User.addressDescendingComparator);
                userTreeSet.addAll(users);
                return userTreeSet;
            case "rollno":
                userTreeSet = order.equals("a") ?
                        new TreeSet<>(User.rollNoAscendingComparator) : new TreeSet<>(User.rollNoDescendingComparator);
                userTreeSet.addAll(users);
                return userTreeSet;
            default:
                return order.equals("a") ? users : (TreeSet<User>) users.descendingSet();

        }
    }

    public void removeUser(int rollNo){
        boolean found = false;
        for (User user: users){
            if (user.getRollNo() == rollNo){
                users.remove(user);
                found = true;
                System.out.println("User details successfully deleted");
                break;
            }
        }
        if (!found){
            throw new NoSuchElementException("Can't find user with such roll number. Please check and try again");
        }
    }

    public void saveUserDetailsToFile(String fileName) throws IOException{
        if (users.size() == 0) return;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(users);
        objectOutputStream.close();
        fileOutputStream.close();
        System.out.println("User details successfully saved on disk");
    }

    public void getUserDetailsFromFile(String fileName){
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            users = (TreeSet<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {

        }
    }

}

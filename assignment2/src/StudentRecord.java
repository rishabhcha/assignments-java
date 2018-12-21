import java.io.IOException;
import java.util.*;

public class StudentRecord {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        UserTasks userTasks = new UserTasks();
        userTasks.getUserDetailsFromFile(UserTasks.USERS_FILE_NAME);
        while (true){
            try {
                System.out.println("\nEnter your choice\n1.Enter user details\n2.Display user details\n" +
                        "3.Delete user details\n4.Save user details\n5.Exit");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 1:
                        System.out.print("Enter user name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter user age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter user address: ");
                        String address = sc.nextLine();
                        System.out.print("Enter user roll number: ");
                        int rollNo = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter space separated user's course: ");
                        List<String> courses = Arrays.asList(sc.nextLine().trim().split(" "));
                        User user = new User(name, age, address, rollNo, courses);
                        userTasks.addUser(user);
                        break;
                    case 2:
                        System.out.print("How to sort result age/rollNo/address/name: ");
                        String sortBy = sc.nextLine();
                        System.out.print("Want result in ascending or descending order(a/d): ");
                        String order = sc.nextLine();
                        TreeSet<User> users = userTasks.showUsers(sortBy, order);
                        displayUsers(users);
                        break;
                    case 3:
                        System.out.print("Enter roll number of user whom you want to delete: ");
                        int rollNumber = sc.nextInt();
                        sc.nextLine();
                        userTasks.removeUser(rollNumber);
                        break;
                    case 4:
                        userTasks.saveUserDetailsToFile(UserTasks.USERS_FILE_NAME);
                        break;
                    case 5:
                        System.out.print("Do you want save progress before exit(y/n): ");
                        String save = sc.nextLine();
                        if (save.equals("y")) userTasks.saveUserDetailsToFile(UserTasks.USERS_FILE_NAME);
                        return;
                    default:
                        System.out.println("You didn't enter suitable choice. Please try again");
                        break;
                }
            }catch (InputMismatchException e){
                sc.nextLine();
                System.out.println("Please enter details in correct format");
            }catch (NullPointerException | IllegalArgumentException | NoSuchElementException | IOException e){
                System.out.println(e.getMessage());
            }
        }

    }

    private static void displayUsers(TreeSet<User> users) {
        System.out.println("----------------------------------------------------------------------------------------" +
                "-------------------------------------------------\n");
        System.out.format("%32s%16s%8s%32s%16s\n", "Name", "Roll Number", "Age", "Address", "Courses");
        System.out.println("----------------------------------------------------------------------------------------" +
                "-------------------------------------------------\n");
        for (User user : users){
            System.out.format("%32s%16d%8d%32s%16s\n", user.getName(), user.getRollNo(), user.getAge(),
                    user.getAddress(), user.getCourseList().toString());
        }
    }

}

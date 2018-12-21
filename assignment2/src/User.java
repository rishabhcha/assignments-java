import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class User implements Comparable<User>, Serializable {

    private String name;
    private int age;
    private String address;
    private int rollNo;
    private List<String> courseList;

    public User() {
    }

    public User(String name, int age, String address, int rollNo, List<String> courseList) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.rollNo = rollNo;
        this.courseList = courseList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public List<String> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<String> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", rollNo=" + rollNo +
                ", courseList=" + courseList +
                '}';
    }

    @Override
    public int compareTo(User user) {
        //sort user based on name in ascending order, if name are same
        //then sorting is performed based on roll number
        if (this.getRollNo() - user.getRollNo() == 0) return 0; //condition to make sure rollNo is unique
        int flag = this.getName().compareTo(user.getName());
        return flag == 0 ? (this.getRollNo() - user.getRollNo()) : flag;
    }

    /**
     * Comparator to sort list or array of user based on age in ascending order
     */
    public static Comparator<User> ageAscendingComparator = new Comparator<User>() {
        @Override
        public int compare(User user1, User user2) {
            return user1.getAge() - user2.getAge();
        }
    };

    /**
     * Comparator to sort list or array of user based on address in ascending order
     */
    public static Comparator<User> addressAscendingComparator = new Comparator<User>() {
        @Override
        public int compare(User user1, User user2) {
            return user1.getAddress().compareTo(user2.getAddress());
        }
    };

    /**
     * Comparator to sort list or array of user based on rollNo in ascending order
     */
    public static Comparator<User> rollNoAscendingComparator = new Comparator<User>() {
        @Override
        public int compare(User user1, User user2) {
            return user1.getRollNo() - user2.getRollNo();
        }
    };

    /**
     * Comparator to sort list or array of user based on age in descending order
     */
    public static Comparator<User> ageDescendingComparator = new Comparator<User>() {
        @Override
        public int compare(User user1, User user2) {
            return user2.getAge() - user1.getAge();
        }
    };

    /**
     * Comparator to sort list or array of user based on address in descending order
     */
    public static Comparator<User> addressDescendingComparator = new Comparator<User>() {
        @Override
        public int compare(User user1, User user2) {
            return user2.getAddress().compareTo(user1.getAddress());
        }
    };

    /**
     * Comparator to sort list or array of user based on rollNo in descending order
     */
    public static Comparator<User> rollNoDescendingComparator = new Comparator<User>() {
        @Override
        public int compare(User user1, User user2) {
            return user2.getRollNo() - user1.getRollNo();
        }
    };

}

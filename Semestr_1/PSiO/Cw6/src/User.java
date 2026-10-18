abstract public class User {

    private String first_name;
    private String last_name;
    private int user_id;

    public User(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_id = 0;
    }

    public void deleteUser() {
        System.out.println("Deleting account");
    }

    public void changeUserData(String first_name, String last_name, int user_id) {
        System.out.println("Changing user data");
        this.first_name = first_name;
        this.last_name = last_name;
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
package gamestore_project;

public class Admin {
    private String username;
    private String password;

    public Admin(String user, String pass) {
        username = user;
        password = pass;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
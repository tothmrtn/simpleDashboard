public class User {
    private String name;
    private String email;
    private String password;

    // Constructor
    public User(String name, String email, String password) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
    }

    public User() {

    }


    // Getters
    public String getName() {

        return this.name;
    }

    public String getEmail() {

        return this.email;
    }

    public String getPassword() {

        return this.password;
    }


    // Setters
    public void setName(String name) {

        this.name = name;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public void setPassword(String password) {

        this.password = password;
    }


}

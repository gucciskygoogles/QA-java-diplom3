package Data;


public class User {

    private String email;
    private String name;
    private String password;

    private static final String[] NAMES = {
            "Vadim", "Alexey", "Olga", "Maria", "Sergey", "Ivan", "Anna", "Elena"
    };

    public User(String email, String password, String name) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
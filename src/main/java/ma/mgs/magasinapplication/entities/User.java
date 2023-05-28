package ma.mgs.magasinapplication.entities;

import java.util.Objects;

public class User {
    private int id;
    private String Username;
    private String Password;

    public User(int id, String username, String password) {
        this.id = id;
        Username = username;
        Password = password;
    }
    public User( String username, String password) {
        Username = username;
        Password = password;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}

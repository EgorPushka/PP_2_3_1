package web.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class User {

    private int id;

    @NotEmpty(message = "Not valid name!")
    @Size(min = 1, max = 15, message = "Enter correct value bet.1 and 15 chars!")
    private String name;

    @Min(value = 0, message = "Not correct age!")
    private int age;

    @Email(message = "Not correct email!")
    @NotEmpty(message = "Not empty email!")
    private String email;


    public User() {
    }

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email=" + email +
                '}';
    }
}

package cent2box.cons.transfer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "UserID") // Maps to the correct column
    private Long userId;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Column(name = "Username", nullable = false, unique = true)
    private String username;

    @Column(name = "PasswordHash", nullable = false)
    private String password;

    @Column(name = "RoleID", nullable = false)
    private String role;

    // Getters & setters

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }


    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
}

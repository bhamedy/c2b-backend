import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGen {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("test123"));
    }
}

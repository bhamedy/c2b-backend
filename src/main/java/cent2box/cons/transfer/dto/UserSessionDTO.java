package cent2box.cons.transfer.dto;

public class UserSessionDTO {
    public Long userId;
    public String role;

    public UserSessionDTO(Long userId, String role) {
        this.userId = userId;
        this.role = role;
    }
}


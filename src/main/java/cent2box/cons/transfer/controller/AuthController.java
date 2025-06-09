package cent2box.cons.transfer.controller;

import cent2box.cons.transfer.dto.LoginRequest;
import cent2box.cons.transfer.dto.UserSessionDTO;
import cent2box.cons.transfer.model.User;
import cent2box.cons.transfer.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public UserSessionDTO login(@RequestBody LoginRequest request) {
        User user = authService.authenticate(request.username, request.password);
        return new UserSessionDTO(user.getUserId(), user.getRole());

    }
}

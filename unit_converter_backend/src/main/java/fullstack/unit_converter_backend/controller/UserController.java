package fullstack.unit_converter_backend.controller;

import fullstack.unit_converter_backend.dto.HistoryResponse;
import fullstack.unit_converter_backend.dto.UserResponse;
import fullstack.unit_converter_backend.model.User;
import fullstack.unit_converter_backend.service.HistoryService;
import fullstack.unit_converter_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final HistoryService historyService;

    public UserController(UserService userService, HistoryService historyService) {
        this.userService = userService;
        this.historyService = historyService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<HistoryResponse>> getUserHistory(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(historyService.getUserHistory(user));
    }
}

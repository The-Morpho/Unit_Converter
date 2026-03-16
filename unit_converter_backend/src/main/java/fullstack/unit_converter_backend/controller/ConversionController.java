package fullstack.unit_converter_backend.controller;

import fullstack.unit_converter_backend.dto.ConversionRequest;
import fullstack.unit_converter_backend.dto.ConversionResponse;
import fullstack.unit_converter_backend.dto.HistoryResponse;
import fullstack.unit_converter_backend.model.User;
import fullstack.unit_converter_backend.service.ConversionService;
import fullstack.unit_converter_backend.service.HistoryService;
import fullstack.unit_converter_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conv")
public class ConversionController {

    private final ConversionService conversionService;
    private final HistoryService historyService;
    private final UserService userService;

    public ConversionController(ConversionService conversionService,
                                HistoryService historyService,
                                UserService userService) {
        this.conversionService = conversionService;
        this.historyService = historyService;
        this.userService = userService;
    }

    @GetMapping("/info")
    public ResponseEntity<String> getConversionInfo() {
        return ResponseEntity.ok("API de conversion est opérationnelle");
    }

    @PostMapping("/preview")
    public ResponseEntity<ConversionResponse> previewConversion(
            @Valid @RequestBody ConversionRequest request,
            Authentication authentication) {
        ConversionResponse response = conversionService.previewConversion(request);
        User user = userService.findByEmail(authentication.getName());
        historyService.saveHistory(user, request, response.getResult());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history")
    public ResponseEntity<List<HistoryResponse>> getMyHistory(Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        return ResponseEntity.ok(historyService.getUserHistory(user));
    }
}
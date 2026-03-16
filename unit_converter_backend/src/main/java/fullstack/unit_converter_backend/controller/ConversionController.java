package fullstack.unit_converter_backend.controller;

import fullstack.unit_converter_backend.dto.ConversionRequest;
import fullstack.unit_converter_backend.dto.ConversionResponse;
import fullstack.unit_converter_backend.service.ConversionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conv")
@CrossOrigin(origins="*")
public class ConversionController {

    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
    @GetMapping("/info")
    public ResponseEntity<String> getConversionInfo() {
        return ResponseEntity.ok("API de conversion est opérationnelle");
    }
    @PostMapping("/preview")
    public ResponseEntity<ConversionResponse> previewConversion(@Valid @RequestBody ConversionRequest request) {
        ConversionResponse response = conversionService.previewConversion(request);
        return ResponseEntity.ok(response);
    }

}
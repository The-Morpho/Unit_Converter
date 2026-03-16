package fullstack.unit_converter_backend.service;

import fullstack.unit_converter_backend.dto.ConversionRequest;
import fullstack.unit_converter_backend.dto.HistoryResponse;
import fullstack.unit_converter_backend.model.ConversionHistory;
import fullstack.unit_converter_backend.model.User;
import fullstack.unit_converter_backend.repository.ConversionHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService {

    private final ConversionHistoryRepository repo;

    public HistoryService(ConversionHistoryRepository repo) {
        this.repo = repo;
    }

    public void saveHistory(User user, ConversionRequest request, double result) {
        ConversionHistory history = ConversionHistory.builder()
                .user(user)
                .category(request.getCategory())
                .fromUnit(request.getFromUnit())
                .toUnit(request.getToUnit())
                .inputValue(request.getValue())
                .outputValue(result)
                .build();
        repo.save(history);
    }

    public List<HistoryResponse> getUserHistory(User user) {
        return repo.findByUserOrderByCreatedAtDesc(user).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<HistoryResponse> getAllHistory() {
        return repo.findAllByOrderByCreatedAtDesc().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private HistoryResponse toDto(ConversionHistory h) {
        return HistoryResponse.builder()
                .id(h.getId())
                .category(h.getCategory())
                .fromUnit(h.getFromUnit())
                .toUnit(h.getToUnit())
                .inputValue(h.getInputValue())
                .outputValue(h.getOutputValue())
                .createdAt(h.getCreatedAt())
                .build();
    }
}

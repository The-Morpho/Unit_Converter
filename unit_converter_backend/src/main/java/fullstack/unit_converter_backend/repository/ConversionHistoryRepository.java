package fullstack.unit_converter_backend.repository;

import fullstack.unit_converter_backend.model.ConversionHistory;
import fullstack.unit_converter_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {

    List<ConversionHistory> findByUserOrderByCreatedAtDesc(User user);

    List<ConversionHistory> findAllByOrderByCreatedAtDesc();
}
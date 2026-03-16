package fullstack.unit_converter_backend.model;

import fullstack.unit_converter_backend.enums.UnitCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "conversion_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UnitCategory category;

    @Column(name = "from_unit", nullable = false, length = 30)
    private String fromUnit;

    @Column(name = "to_unit", nullable = false, length = 30)
    private String toUnit;

    @Column(name = "input_value", nullable = false)
    private Double inputValue;

    @Column(name = "output_value", nullable = false)
    private Double outputValue;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
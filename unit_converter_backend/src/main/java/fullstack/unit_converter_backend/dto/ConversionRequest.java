package fullstack.unit_converter_backend.dto;

import fullstack.unit_converter_backend.enums.UnitCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConversionRequest {

    @NotNull(message = "La catégorie est obligatoire")
    private UnitCategory category;

    @NotBlank(message = "L'unité source est obligatoire")
    private String fromUnit;

    @NotBlank(message = "L'unité cible est obligatoire")
    private String toUnit;

    @NotNull(message = "La valeur est obligatoire")
    private Double value;
}
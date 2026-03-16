package fullstack.unit_converter_backend.dto;

import fullstack.unit_converter_backend.enums.UnitCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversionResponse {

    private UnitCategory category;
    private String fromUnit;
    private String toUnit;
    private Double inputValue;
    private Double result;
}
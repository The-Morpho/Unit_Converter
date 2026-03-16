package fullstack.unit_converter_backend.service;

import fullstack.unit_converter_backend.dto.ConversionRequest;
import fullstack.unit_converter_backend.dto.ConversionResponse;
import fullstack.unit_converter_backend.enums.DistanceUnit;
import fullstack.unit_converter_backend.enums.TemperatureUnit;
import fullstack.unit_converter_backend.enums.WeightUnit;
import org.springframework.stereotype.Service;

@Service
public class ConversionService {

    public ConversionResponse previewConversion(ConversionRequest request) {
        double result;

        switch (request.getCategory()) {
            case WEIGHT:
                result = convertWeight(request.getFromUnit(), request.getToUnit(), request.getValue());
                break;

            case DISTANCE:
                result = convertDistance(request.getFromUnit(), request.getToUnit(), request.getValue());
                break;

            case TEMPERATURE:
                result = convertTemperature(request.getFromUnit(), request.getToUnit(), request.getValue());
                break;

            default:
                throw new IllegalArgumentException("Catégorie non supportée");
        }

        return ConversionResponse.builder()
                .category(request.getCategory())
                .fromUnit(request.getFromUnit())
                .toUnit(request.getToUnit())
                .inputValue(request.getValue())
                .result(result)
                .build();
    }

    private double convertWeight(String fromUnit, String toUnit, double value) {
        WeightUnit from = parseWeightUnit(fromUnit);
        WeightUnit to = parseWeightUnit(toUnit);

        if (from == to) {
            return value;
        }

        double valueInGrams;

        switch (from) {
            case KG:
                valueInGrams = value * 1000;
                break;
            case G:
                valueInGrams = value;
                break;
            case LB:
                valueInGrams = value * 453.59237;
                break;
            default:
                throw new IllegalArgumentException("Unité de poids source invalide");
        }

        switch (to) {
            case KG:
                return valueInGrams / 1000;
            case G:
                return valueInGrams;
            case LB:
                return valueInGrams / 453.59237;
            default:
                throw new IllegalArgumentException("Unité de poids cible invalide");
        }
    }

    private double convertDistance(String fromUnit, String toUnit, double value) {
        DistanceUnit from = parseDistanceUnit(fromUnit);
        DistanceUnit to = parseDistanceUnit(toUnit);

        if (from == to) {
            return value;
        }

        double valueInMeters;

        switch (from) {
            case KM:
                valueInMeters = value * 1000;
                break;
            case M:
                valueInMeters = value;
                break;
            case MI:
                valueInMeters = value * 1609.344;
                break;
            default:
                throw new IllegalArgumentException("Unité de distance source invalide");
        }

        switch (to) {
            case KM:
                return valueInMeters / 1000;
            case M:
                return valueInMeters;
            case MI:
                return valueInMeters / 1609.344;
            default:
                throw new IllegalArgumentException("Unité de distance cible invalide");
        }
    }

    private double convertTemperature(String fromUnit, String toUnit, double value) {
        TemperatureUnit from = parseTemperatureUnit(fromUnit);
        TemperatureUnit to = parseTemperatureUnit(toUnit);

        if (from == to) {
            return value;
        }

        double valueInCelsius;

        switch (from) {
            case C:
                valueInCelsius = value;
                break;
            case F:
                valueInCelsius = (value - 32) * 5 / 9;
                break;
            case K:
                valueInCelsius = value - 273.15;
                break;
            default:
                throw new IllegalArgumentException("Unité de température source invalide");
        }

        switch (to) {
            case C:
                return valueInCelsius;
            case F:
                return (valueInCelsius * 9 / 5) + 32;
            case K:
                return valueInCelsius + 273.15;
            default:
                throw new IllegalArgumentException("Unité de température cible invalide");
        }
    }

    private WeightUnit parseWeightUnit(String unit) {
        try {
            return WeightUnit.valueOf(unit.trim().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Unité de poids invalide : " + unit);
        }
    }

    private DistanceUnit parseDistanceUnit(String unit) {
        try {
            return DistanceUnit.valueOf(unit.trim().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Unité de distance invalide : " + unit);
        }
    }

    private TemperatureUnit parseTemperatureUnit(String unit) {
        try {
            return TemperatureUnit.valueOf(unit.trim().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("Unité de température invalide : " + unit);
        }
    }
}
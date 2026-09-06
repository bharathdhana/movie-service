package com.bharath.movieservice.dto;

import com.bharath.movieservice.entity.enums.MovieStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieRequest {
    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "duration is required")
    @Min(value = 1, message = "duration must be greater than 0")
    private Integer duration;

    @NotBlank(message = "language is required")
    private String language;

    @NotBlank(message = "genre is required")
    private String genre;

    @NotNull(message = "release date is required")
    private LocalDate releaseDate;

    @NotNull(message = "rating is required")
    @DecimalMin(value = "0.0", message = "Rating cannot be less than 0")
    @DecimalMax(value = "10.0", message = "Rating cannot be greater than 10")
    private Double rating;

    @NotNull(message = "status is required")
    @Enumerated(EnumType.STRING)
    private MovieStatus status;

}

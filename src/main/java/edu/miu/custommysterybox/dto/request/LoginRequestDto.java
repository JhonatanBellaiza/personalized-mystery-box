package edu.miu.custommysterybox.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
        @NotBlank(message = "blank - null- empty are not accepted")
        String email,
        @Size(min = 3, max = 10)
        String password
) {
}

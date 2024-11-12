package edu.miu.custommysterybox.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public record UserRequestDto(
        @NotBlank(message = "blank - null- empty are not accepted")
        String username,
        @Size(min = 3, max = 10)
        String password
) {
}

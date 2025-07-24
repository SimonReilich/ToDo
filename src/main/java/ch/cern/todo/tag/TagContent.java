package ch.cern.todo.tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TagContent(@NotNull @NotBlank String name) {
}

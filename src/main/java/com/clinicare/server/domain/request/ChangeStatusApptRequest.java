package com.clinicare.server.domain.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeStatusApptRequest {

    @NotNull(message = "Slot ID is required")
    @Min(1)
    @Max(3)
    private Long statusId;
}

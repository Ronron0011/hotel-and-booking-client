package fa.training.mockproject.mockprojectfjb05group01.dto.client;

import fa.training.mockproject.mockprojectfjb05group01.model.enums.RoleType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RoleDTO {
    private Long id;

    @NotNull(message = "Role type is required")
    private RoleType roleType;

    @NotBlank(message = "Description is required")
    @Size(max = 300, message = "Description should not exceed 300 characters")
    private String description;
}

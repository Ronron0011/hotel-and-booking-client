package fa.training.mockproject.mockprojectfjb05group01.service.client;

import fa.training.mockproject.mockprojectfjb05group01.dto.client.RoleDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.client.Role;
import fa.training.mockproject.mockprojectfjb05group01.model.enums.RoleType;



public interface RoleService {
    RoleDTO findRoleByRoleType(RoleType roleName);

    Role convertDtoToEntity(RoleDTO roleData);

    RoleDTO convertEntityToDto(Role role);

}

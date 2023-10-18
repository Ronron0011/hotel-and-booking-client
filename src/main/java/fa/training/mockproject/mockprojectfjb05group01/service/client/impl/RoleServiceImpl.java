package fa.training.mockproject.mockprojectfjb05group01.service.client.impl;

import fa.training.mockproject.mockprojectfjb05group01.dto.client.RoleDTO;
import fa.training.mockproject.mockprojectfjb05group01.entity.client.Role;
import fa.training.mockproject.mockprojectfjb05group01.model.enums.RoleType;
import fa.training.mockproject.mockprojectfjb05group01.repository.client.RoleRepository;
import fa.training.mockproject.mockprojectfjb05group01.service.client.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO findRoleByRoleType(RoleType roleName) {
        Optional<Role> roleFind = roleRepository.findRoleByRoleType(roleName);
        RoleDTO roleData = null;

        if (roleFind.isPresent()){
            roleData = convertEntityToDto(roleFind.get());
        }

        return roleData;
    }

    public List<RoleDTO> findAllRoles() {
        List<Role> roles = roleRepository.findAll();

        return roles.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Role convertDtoToEntity(RoleDTO roleData) {
        Role role = new Role();
        role.setId(roleData.getId());
        role.setRoleType(roleData.getRoleType());
        role.setDescription(roleData.getDescription());
        return role;
    }

    @Override
    public RoleDTO convertEntityToDto(Role role) {
        RoleDTO roleData = new RoleDTO();
        roleData.setId(role.getId());
        roleData.setRoleType(role.getRoleType());
        roleData.setDescription(role.getDescription());
        return roleData;
    }

}

package io.github.chengmboy.cloudrs.uc.remote;

import io.github.chengmboy.cloudrs.uc.api.UcRemoteService;
import io.github.chengmboy.cloudrs.uc.api.dto.PermissionDTO;
import io.github.chengmboy.cloudrs.uc.api.dto.UserDTO;
import io.github.chengmboy.cloudrs.uc.pojo.entity.Permission;
import io.github.chengmboy.cloudrs.uc.pojo.entity.Role;
import io.github.chengmboy.cloudrs.uc.pojo.repository.RoleRepository;
import io.github.chengmboy.cloudrs.uc.service.QuotaService;
import io.github.chengmboy.cloudrs.uc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



@RestController
@RequiredArgsConstructor
public class UcRemoteController implements UcRemoteService {


    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    QuotaService quotaService;

    @Override
    public Set<PermissionDTO> findPermissionByRoleCode(String roleCode) {

        Set<PermissionDTO> permissionDTOS = new HashSet<>();
        Role role = roleRepository.findByRoleCode(roleCode);
        if (role != null) {
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                PermissionDTO permissionDTO = new PermissionDTO();
                BeanUtils.copyProperties(permission,permissionDTO);
                permissionDTOS.add(permissionDTO);
            }
        }
        return permissionDTOS;
    }



    @Override
    public UserDTO getByLoginName(@PathVariable("loginName") String loginName) {
        return userService.getByLoginName(loginName);
    }

    @Override
    public void deduct(String code, int count) {
        quotaService.deduct(code,count);
    }
}

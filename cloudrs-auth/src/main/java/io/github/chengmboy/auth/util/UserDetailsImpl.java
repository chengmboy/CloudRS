package io.github.chengmboy.auth.util;


import io.github.chengmboy.cloudrs.uc.api.dto.UserDTO;
import io.github.chengmboy.cloudrs.common.constants.CommonConstant;
import io.github.chengmboy.cloudrs.common.constants.SecurityConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String username;
    private String password;
    private String status;
    private List<String> roleVoList;

    public UserDetailsImpl() {

    }

    public UserDetailsImpl(UserDTO memberVo) {
        this.userId = memberVo.getId();
        this.username = memberVo.getName();
        this.password = memberVo.getPassword();
        this.status = "0";
        roleVoList = Arrays.asList("USER");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (String roleVo : roleVoList) {
            authorityList.add(new SimpleGrantedAuthority(roleVo));
        }
        authorityList.add(new SimpleGrantedAuthority(SecurityConstants.BASE_ROLE));
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !StringUtils.equals(CommonConstant.STATUS_LOCK, status);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return StringUtils.equals(CommonConstant.STATUS_NORMAL, status);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoleVoList() {
        return roleVoList;
    }

    public void setRoleVoList(List<String> roleVoList) {
        this.roleVoList = roleVoList;
    }

    public String getStatus() {
        return status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

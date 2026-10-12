package com.angelicamartins.expensecontrol.model;

import com.angelicamartins.expensecontrol.model.dto.UserDto;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public record UserDetailsVo(
  String username,
  String password,
  List<GrantedAuthority> authorities
) implements UserDetails {
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public static UserDetailsVo fromUserDto(UserDto user) {
    return new UserDetailsVo(user.getEmail(), user.getPassword(), List.of());
  }
}

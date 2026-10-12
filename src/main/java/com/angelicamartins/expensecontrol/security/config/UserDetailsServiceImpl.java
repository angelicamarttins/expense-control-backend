package com.angelicamartins.expensecontrol.security.config;

import static com.angelicamartins.expensecontrol.model.UserDetailsVo.fromUserDto;

import com.angelicamartins.expensecontrol.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("username ====== " + username);

    return fromUserDto(userService.findUserByEmail(username));
  }
}

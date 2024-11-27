package com.example.gameDemo.service.impl;


import com.example.gameDemo.model.UserMaster;
import com.example.gameDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String mobileNo) throws UsernameNotFoundException {
    UserMaster user = (UserMaster) userRepository.findByMobileNo(mobileNo)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with phone: " + mobileNo));

    return UserDetailsImpl.build(user);
  }

}

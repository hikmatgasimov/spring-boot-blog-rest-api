package com.springboot.blog.security;


import com.springboot.blog.entity.User;
import com.springboot.blog.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    private  UserRepository userRepository;

    public CustomUserDetailsServic(UserRepository userRepository){
      this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
      User user=  userRepository.findByUsername(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()->new  UsernameNotFoundException("User not found with username or email:" + usernameOrEmail));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));

    }
    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}

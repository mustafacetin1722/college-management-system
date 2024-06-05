package com.mustafa.college_management.service.impl;

import com.mustafa.college_management.model.User;
import com.mustafa.college_management.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCredentialsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    public UserCredentialsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        return user.orElseThrow(EntityExistsException::new);
    }
}

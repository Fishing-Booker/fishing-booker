package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.IRepository.UserRepository;
import com.example.fishingbooker.Model.Role;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IRoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findById(Integer id) {
        Optional<User> user = this.userRepository.findById(id);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = this.userRepository.findAll();
        return users;
    }

    @Override
    public User save(UserDTO userDTO) {
        User u = new User();
        u.setUsername(userDTO.getUsername());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setEmail(userDTO.getEmail());
        u.setName(userDTO.getName());
        u.setSurname(userDTO.getSurname());
        u.setAddress(userDTO.getAddress());
        u.setApproved(true);
        u.setCity(userDTO.getCity());
        u.setCountry(userDTO.getCountry());
        u.setDeleted(false);
        u.setPhone(userDTO.getPhoneNumer());
        List<Role> roles = roleService.findByName(userDTO.getRolename());
        u.setRoles(roles);

        return this.userRepository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }
}

package com.prueba.usco.service;

import com.prueba.usco.domain.Authority;
import com.prueba.usco.domain.User;
import com.prueba.usco.repository.AuthorityRepository;
import com.prueba.usco.repository.UserRepository;
import com.prueba.usco.security.AuthoritiesConstants;
import com.prueba.usco.security.SecurityUtils;
import com.prueba.usco.service.dto.UserDTO;
import com.prueba.usco.web.rest.errors.BadRequestException;
import com.prueba.usco.web.rest.input.CreateUserInput;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;
    private static final SecureRandom SECURE_RANDOM;

    static {
        SECURE_RANDOM = new SecureRandom();
        SECURE_RANDOM.nextBytes(new byte[64]);
    }

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    private boolean removeNonActivatedUser(User existingUser) {
        if (existingUser.isActivated()) {
            return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        return true;
    }

    public User createUser(CreateUserInput userDTO) {
        User user = new User();
        user.setLogin(userDTO.getFirstName().toLowerCase() + userDTO.getLastName().toLowerCase() + userDTO.getDocumentId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());
        user.setBirthdate(userDTO.getBirthdate());
        user.setDocumentId(userDTO.getDocumentId());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setGender(userDTO.getGender());
        String encryptedPassword = passwordEncoder.encode(RandomStringUtils.random(20, 0, 0, true, true, null, SECURE_RANDOM));
        user.setPassword(encryptedPassword);
        user.setActivated(true);
        if (userDTO.getAuthorities() != null) {
            Set<Authority> authorities = userDTO
                .getAuthorities()
                .stream()
                .map(authorityRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            user.setAuthorities(authorities);
        }
        userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param phoneNumber first name of user.
     * @param address  last name of user.
     */
    public void updateUser(String phoneNumber, String address) {
        SecurityUtils
            .getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(
                user -> {
                    user.setPhoneNumber(phoneNumber);
                    user.setAddress(address);
                    log.debug("Changed Information for User: {}", user);
                }
            );
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
    }

    /**
     * Gets a list of all the authorities.
     * @return a list of all the authorities.
     */
    @Transactional(readOnly = true)
    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }
}

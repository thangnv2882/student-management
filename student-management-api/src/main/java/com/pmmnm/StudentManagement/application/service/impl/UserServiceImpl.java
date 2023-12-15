package com.pmmnm.StudentManagement.application.service.impl;

import com.pmmnm.StudentManagement.application.constants.CommonConstant;
import com.pmmnm.StudentManagement.application.constants.MessageConstant;
import com.pmmnm.StudentManagement.application.input.commons.Input;
import com.pmmnm.StudentManagement.application.input.user.CreateUserInput;
import com.pmmnm.StudentManagement.application.input.user.LoginInput;
import com.pmmnm.StudentManagement.application.input.user.UpdateUserInput;
import com.pmmnm.StudentManagement.application.output.common.Output;
import com.pmmnm.StudentManagement.application.repository.UserRepository;
import com.pmmnm.StudentManagement.application.service.IUserService;
import com.pmmnm.StudentManagement.config.exception.NotFoundException;
import com.pmmnm.StudentManagement.domain.entity.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public User login(LoginInput loginInput) {
        User user = userRepository.login(loginInput.getId(), loginInput.getPassword());
        checkUserExists(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Input input) {
        User user = userRepository.findById(input.getId());
        checkUserExists(user);
        return user;
    }

    @Override
    public Output createUser(CreateUserInput input) {
        User user = userRepository.findById(input.getId());
        if (user != null) {
            return new Output(CommonConstant.TRUE, MessageConstant.USER_ALREADY_EXISTS);
        }
        user = modelMapper.map(input, User.class);
        // TODO gen password
        user.setPassword(RandomStringUtils.randomAlphanumeric(6));
        userRepository.save(user);
        return new Output(CommonConstant.TRUE, CommonConstant.CREATED);
    }

    @Override
    public Output updateUser(UpdateUserInput input) {
        User user = userRepository.findById(input.getId());
        checkUserExists(user);
        modelMapper.map(input, user);
        userRepository.save(user);
        return new Output(CommonConstant.TRUE, CommonConstant.UPDATED);
    }

    @Override
    public Output deleteUser(Input input) {
        User user = userRepository.findById(input.getId());
        checkUserExists(user);
        userRepository.delete(user);
        return new Output(CommonConstant.TRUE, CommonConstant.DELETED);
    }


    public static void checkUserExists(User user) {
        if (user == null) {
            throw new NotFoundException(MessageConstant.USER_NOT_EXISTS);
        }
    }

}

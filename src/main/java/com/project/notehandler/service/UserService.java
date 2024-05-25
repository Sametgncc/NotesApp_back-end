package com.project.notehandler.service;

import com.project.notehandler.Exceptions.NotFoundException;
import com.project.notehandler.Exceptions.NotMatchedException;
import com.project.notehandler.Exceptions.UserAlreadyExistsWithEmailException;
import com.project.notehandler.Exceptions.UserAlreadyExistsWithUsernameException;
import com.project.notehandler.model.Note;
import com.project.notehandler.model.User;
import com.project.notehandler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope(value = "singleton")
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsWithEmailException("E-posta alınmış: " + user.getEmail());
        }
        else{
            if(userRepository.existsByUsername(user.getUsername())){
                throw new UserAlreadyExistsWithUsernameException("Kullanıcı adı alınmış: " + user.getUsername());
            }
            else{
                return userRepository.save(user);
            }
        }


    }

    public User getByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);

        return foundUser.orElse(null);

    }

    public User deleteByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            userRepository.delete(user);
            return user;
        }
        else {
            throw new NotFoundException("Kullanıcı bulunamadı.");
        }
    }

    public User updateByEmail(String email, User newUser) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isPresent()) {

            User user = foundUser.get();
            userRepository.delete(user);
            if(!newUser.getEmail().isEmpty()){
                user.setEmail(newUser.getEmail());
            }
            if(!newUser.getPassword().isEmpty()){
                user.setPassword(newUser.getPassword());
            }
            if (!newUser.getUsername().isEmpty()){
                user.setUsername(newUser.getUsername());
            }
            user.setNotes(newUser.getNotes());
            return userRepository.save(user);

        }
        else {
            throw new NotFoundException("Kullanıcı bulunamadı.");
        }
    }

    public User checkUser(String emailUsername, String password) {
        Optional<User> foundUser = userRepository.findByEmail(emailUsername);
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
            else {
                throw new NotMatchedException("e-posta şifre yanlış.");
            }
        }
        else {
            foundUser = userRepository.findByUsername(emailUsername);
            if (foundUser.isPresent()) {
                User user = foundUser.get();
                if (user.getPassword().equals(password)) {
                    return user;
                }
                else {
                    throw new NotMatchedException("kullanıcı adı - şifre yanlış.");
                }
            }
            else {
                throw new NotFoundException("e-posta veya kullanıcı bulunamadı.");
            }
        }
    }

    public User addNote(String email, Note note) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            List<Note> notes= user.getNotes();
            notes.add(note);
            user.setNotes(notes);
            return userRepository.save(user);
        }
        else {
            throw new NotFoundException("Kullanıcı bulunamadı.");
        }
    }
}

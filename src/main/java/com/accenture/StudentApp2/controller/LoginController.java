package com.accenture.StudentApp2.controller;

import com.accenture.StudentApp2.emailConfirmation.EmailSenderService;
import com.accenture.StudentApp2.emailConfirmation.VerificationToken;
import com.accenture.StudentApp2.model.Student;
import com.accenture.StudentApp2.repository.ConfirmationTokenRepository;
import com.accenture.StudentApp2.repository.UserRepository;
import com.accenture.StudentApp2.service.StudentServiceImpl;
import com.accenture.StudentApp2.service.UserService;
import com.accenture.StudentApp2.userModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Random;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserRepository userRepository;



 /*   @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{

        try{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()
                ));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);


        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }*/

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/registration")
    public String getRegistration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }


    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public ModelAndView registration(@Valid User user, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        Optional<User> userExists = userService.findUserByUsername(user.getUsername());

        if (userExists.isPresent()) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the user name provided");
            System.out.println("User exists: " + userExists.get().getUsername() + ", " + userExists.get().getFirstName());
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            user.setActive(false);
            userService.save(user);
            modelAndView.addObject("user", new User());
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.setViewName("index");

            Student student = new Student(); //TODO: Make user a student? Should I do that?
            student.setName(user.getFirstName());
            student.setSurname(user.getLastName());
            student.setEmail(user.getEmail());
            studentService.save(student);


            emailSenderService.sendEmail(user);

        }
        return modelAndView;
    }


    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        VerificationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setActive(true);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }


}






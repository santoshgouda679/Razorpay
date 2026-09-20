package com.employee_management_system.service;


import java.time.LocalDateTime;
import java.util.Optional;



import org.springframework.stereotype.Service;

import com.employee_management_system.dto.RegisterRequest;
import com.employee_management_system.entity.User;
import com.employee_management_system.repository.UserRepository;
import com.employee_management_system.util.OtpGenerator;

@Service
public class UserService {
	UserRepository userRepository;
EmailService emailService;

	
	
	 public UserService(UserRepository userRepository, EmailService emailService) {
	
	this.userRepository = userRepository;
	this.emailService = emailService;
}



	 public String registerRequest(RegisterRequest registerRequest) {
		// TODO Auto-generated method stub
		 Optional<User>op=userRepository.findByEmail(registerRequest.getEmail());
			if(op.isPresent()) {
				return "email is already exist";
			}else {
				User user=new User();
				user.setName(registerRequest.getName());
				user.setEmail(registerRequest.getEmail());
				user.setPassword(registerRequest.getPassword());
				user.setRole("ROLE_USER");
				user.setVerified(false);
				
				String otp=OtpGenerator.generateOtp();
				user.setOtp(otp);
				user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
				
				emailService.sendOtp(user.getEmail(), otp);
				userRepository.save(user);
				return "otp sent";
				}
	 }

}

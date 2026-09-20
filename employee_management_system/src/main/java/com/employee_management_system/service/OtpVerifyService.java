package com.employee_management_system.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.employee_management_system.dto.VerifyOtpRequest;
import com.employee_management_system.entity.User;
import com.employee_management_system.exception.InvalidOtpException;

import com.employee_management_system.repository.UserRepository;

@Service
public class OtpVerifyService {
    private final UserRepository userRepository;

    // ✅ Constructor injection ensures userRepository is not null
    public OtpVerifyService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String otpVerification(VerifyOtpRequest otpRequest) {
        Optional<User> op = userRepository.findByEmail(otpRequest.getEmail());
        if (op.isPresent()) {
            User user = op.get();

            if (user.getOtp() == null || !user.getOtp().equals(otpRequest.getOtp())) {
            	throw new InvalidOtpException("invalid otp");
            }

            if (LocalDateTime.now().isAfter(user.getOtpExpiryTime())) {
            	Optional<User> us= userRepository.findByEmail(otpRequest.getEmail());
            	User ou= us.get();
            	userRepository.delete(ou);
            	return "otp got expired";
            	 
            } else {
                user.setOtp(null);
                user.setVerified(true);
                user.setOtpExpiryTime(null);
                userRepository.save(user);
                return "otp verified successfully";
            }
        } else {
        	return "user not found with this email";
        }
    }
}

package com.example.fitbull.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.fitbull.entities.GymOwner;
import com.example.fitbull.entities.User;
import com.example.fitbull.repo.GymOwnerRepository;
import com.example.fitbull.repo.UserRepository;
import com.example.fitbull.security.JwtUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	final UserRepository userRepository;
	final GymOwnerRepository gymOwnerRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository,GymOwnerRepository gymOwnerRepository) {
		this.userRepository = userRepository;
		this.gymOwnerRepository=gymOwnerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    User userOpt = userRepository.findByEmail(email);
	    if (userOpt !=null) {
	        return JwtUserDetails.create(userOpt, null);
	    }

	    GymOwner gymOwnerOpt = gymOwnerRepository.findByEmail(email);
	    if (gymOwnerOpt !=null) {
	        return JwtUserDetails.create(null, gymOwnerOpt);
	    }

	    throw new UsernameNotFoundException("Kullanıcı e-posta ile bulunamadı: " + email);
	}

	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).get();
        GymOwner gymOwner = gymOwnerRepository.findById(id).get();

        if (user != null) {
            return  JwtUserDetails.create(user, null);
        } else if (gymOwner != null) {
            return  JwtUserDetails.create(null, gymOwner);
        } else {
            throw new UsernameNotFoundException("Kullanıcı bulunamadı");
        }
	}

}

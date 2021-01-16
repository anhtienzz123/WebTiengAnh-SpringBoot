package comjava.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import comjava.entity.User;
import comjava.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUserName(username);

		if (user == null)
			throw new UsernameNotFoundException("Tài khoản không tồn tại");
		return new CustomUserDetails(user);
	}

	@Transactional
	public UserDetails loadUserById(String userName) {
		User user = userRepository.findByUserName(userName);

		if (user == null)
			return null;

		return new CustomUserDetails(user);
	}

}

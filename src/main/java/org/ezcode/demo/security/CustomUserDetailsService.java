package org.ezcode.demo.security;

import org.ezcode.demo.domain.CustomUser;
import org.ezcode.demo.domain.MemberVO;
import org.ezcode.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * CustomUserDetailsService
 */
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Setter(onMethod_ = { @Autowired })
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		MemberVO vo = memberMapper.read(userid);
		log.warn("Load User By UserName : " + userid);

		return vo == null ? null : new CustomUser(vo);
	}
}
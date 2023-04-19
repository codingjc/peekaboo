package cn.codingjc.peekaboo.application.service;

import cn.codingjc.peekaboo.domain.repository.UserRepository;
import cn.codingjc.peekaboo.infrastructure.persistence.po.SysUserPO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @auther: codingjc
 * @date:   2023/4/19
 */

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

   private final UserRepository userRepository;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      SysUserPO sysUserPO = userRepository.getUserByUsername(username);
      if (null == sysUserPO) {
         throw new UsernameNotFoundException("用户不存在！");
      }
      return sysUserPO;
   }
}

package com.paraparp.gestorfondos.service.imp;

import java.util.List;

import com.paraparp.gestorfondos.model.entity.User;
import com.paraparp.gestorfondos.repository.IUserRepository;

public class AuthService 
//implements UserDetailsService 
{



//	    @Autowired
//
//	    private IUserRepository  userRepository;
//
//	    @Autowired
//
//	    private SecurityGroupService securityGroupService;
//
//	    @Override
//
//	    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//
//	        List<User> users = userRepository.findByUsername(name);
//
//	        if(users.isEmpty()) {
//
//	            throw new UsernameNotFoundException("Could not find the user "+name);
//
//	        }
//
//	        User user = users.get(0);
//
//	        List<SecurityGroup> securityGroups = securityGroupService.listUserGroups(user.getCompanyId(), user.getId());
//
//	        return new CustomUserDetail(user, securityGroups.stream()
//
//	                .map(e->e.getId())
//
//	                .collect(Collectors.toList()) );
//
//	    }
//
//	}
}

package com.paraparp.gestorfondos.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.paraparp.gestorfondos.model.entity.User;
import com.paraparp.gestorfondos.service.IUserService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private IUserService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		User user = usuarioService.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		
		info.put("id", user.getId());
		info.put("firstname", user.getFirstName());
		info.put("lastname", user.getLastName());
		info.put("email", user.getEmail());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}

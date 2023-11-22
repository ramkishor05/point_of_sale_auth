package com.brijframwork.authorization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brijframwork.authorization.beans.UIUserProfile;
import com.brijframwork.authorization.beans.UserDetailRequest;
import com.brijframwork.authorization.beans.UserDetailResponse;
import com.brijframwork.authorization.mapper.UserDetailMapper;
import com.brijframwork.authorization.model.EOUserAccount;
import com.brijframwork.authorization.model.EOUserProfile;
import com.brijframwork.authorization.model.EOUserRole;
import com.brijframwork.authorization.repository.UserAccountRepository;
import com.brijframwork.authorization.repository.UserProfileRepository;
import com.brijframwork.authorization.repository.UserRoleRepository;

@Service
public class CustUserDetailServiceImpl implements CustUserDetailService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	UserDetailMapper userDetailMapper;
	
	@Override
	public UserDetailResponse registerAccount(Long ownerId,UserDetailRequest userDetailRequest) {
		if(isAlreadyExists(userDetailRequest.getUsername())) {
			throw new RuntimeException("AlreadyExists");
		}
		EOUserRole eoUserRole = userRoleRepository.findById(userDetailRequest.getUserRoleId()).orElseThrow(()-> new RuntimeException("Invalid role id"));
		
		EOUserAccount eoUserAccount=new EOUserAccount();
		eoUserAccount.setUsername(userDetailRequest.getUsername());
		eoUserAccount.setPassword(userDetailRequest.getPassword());
		eoUserAccount.setType(eoUserRole.getRoleId());
		eoUserAccount.setRegisteredMobile(userDetailRequest.getRegisteredMobile());
		eoUserAccount.setRegisteredEmail(userDetailRequest.getRegisteredEmail());
		eoUserAccount.setAccountName(userDetailRequest.getAccountName());
		eoUserAccount.setOwnerId(userDetailRequest.getOwnerId());
		eoUserAccount.setUserRole(eoUserRole);
		eoUserAccount.setUserProfile(getUserProfile(userDetailRequest.getUserProfile()));
		eoUserAccount=userAccountRepository.saveAndFlush(eoUserAccount);		
		return userDetailMapper.mapToDTO(eoUserAccount);
	}

	public boolean isAlreadyExists(String username) {
		return userAccountRepository.findUserName(username).isPresent();
	}

	@Override
	public UserDetailResponse updateAccount(Long ownerId,UserDetailRequest userDetailRequest) {
		if(!isAlreadyExists(userDetailRequest.getUsername())) {
			throw new RuntimeException("User not exists in system.");
		}
		EOUserRole eoUserRole = userRoleRepository.findById(userDetailRequest.getUserRoleId()).orElseThrow(()-> new RuntimeException("Invalid role id"));
		
		EOUserAccount eoUserAccount=new EOUserAccount();
		eoUserAccount.setId(userDetailRequest.getId());
		eoUserAccount.setUsername(userDetailRequest.getUsername());
		eoUserAccount.setPassword(userDetailRequest.getPassword());
		eoUserAccount.setType(eoUserRole.getRoleId());
		eoUserAccount.setRegisteredMobile(userDetailRequest.getRegisteredMobile());
		eoUserAccount.setRegisteredEmail(userDetailRequest.getRegisteredEmail());
		eoUserAccount.setAccountName(userDetailRequest.getAccountName());
		eoUserAccount.setOwnerId(userDetailRequest.getOwnerId());
		eoUserAccount.setUserRole(eoUserRole);
		eoUserAccount.setUserProfile(getUserProfile(userDetailRequest.getUserProfile()));
		eoUserAccount=userAccountRepository.saveAndFlush(eoUserAccount);		
		return userDetailMapper.mapToDTO(eoUserAccount);
	}

	private EOUserProfile getUserProfile(UIUserProfile userProfile) {
		EOUserProfile eoUserProfile=new EOUserProfile();
		eoUserProfile.setId(userProfile.getId());
		eoUserProfile.setTitle(userProfile.getTitle());
		eoUserProfile.setFullName(userProfile.getFullName());
		eoUserProfile.setPreferredName(userProfile.getPreferredName());
		eoUserProfile.setPictureURL(userProfile.getPictureURL());
		return userProfileRepository.saveAndFlush(eoUserProfile);
	}

	@Override
	public List<UserDetailResponse> getCustUsers(Long ownerId) {
		return userDetailMapper.mapToDTO(userAccountRepository.findAllByOwnerId(ownerId));
	}

}
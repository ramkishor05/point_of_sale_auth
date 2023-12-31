package com.brijframwork.authorization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brijframwork.authorization.beans.UIUserRole;
import com.brijframwork.authorization.constant.UserType;
import com.brijframwork.authorization.mapper.UserRoleMapper;
import com.brijframwork.authorization.model.EOUserRole;
import com.brijframwork.authorization.repository.UserRoleRepository;

@Service
public class CustRoleServiceImpl implements CustRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public UIUserRole addUserRole(UIUserRole uiUserRole) {
		EOUserRole eoUserRole = userRoleMapper.mapToDAO(uiUserRole);
		eoUserRole.setRoleType(UserType.VENDOR.getType());
		eoUserRole=userRoleRepository.saveAndFlush(eoUserRole);
		return userRoleMapper.mapToDTO(eoUserRole);
	}

	@Override
	public UIUserRole updateUserRole(UIUserRole uiUserRole) {
		EOUserRole eoUserRole = userRoleMapper.mapToDAO(uiUserRole);
		eoUserRole.setRoleType(UserType.VENDOR.getType());
		eoUserRole=userRoleRepository.saveAndFlush(eoUserRole);
		return userRoleMapper.mapToDTO(eoUserRole);
	}

	@Override
	public Boolean deleteUserRole(Long id) {
		userRoleRepository.deleteById(id);
		return true;
	}

	@Override
	public UIUserRole getUserRole(Long id) {
		return userRoleMapper.mapToDTO(userRoleRepository.findById(id).orElse(null));
	}

	@Override
	public List<UIUserRole> getUserRoleList() {
		return userRoleMapper.mapToDTO(userRoleRepository.findAllByRoleType(UserType.VENDOR.getType()));
	}

	@Override
	public List<UIUserRole> getUserRoleList(String type) {
		return userRoleMapper.mapToDTO(userRoleRepository.findAllByRoleType(type));
	}

}

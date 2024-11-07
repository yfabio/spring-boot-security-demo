package com.tech.pro.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tech.pro.model.Role;
import com.tech.pro.repository.RoleRepository;
import com.tech.pro.service.RoleService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
	
	private RoleRepository roleRepository;
			
	public List<Role> getAllRoles(){
		return roleRepository.findAll();
	}
	
}

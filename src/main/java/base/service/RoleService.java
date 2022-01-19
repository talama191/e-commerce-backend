package base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.exception.ResourceNotFoundException;
import base.model.entity.Role;
import base.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional
	public Role findByName(String name) {
		return roleRepository.findByName(name)
							.orElseThrow(() -> new ResourceNotFoundException("Role", "name", name));
	}
	
}

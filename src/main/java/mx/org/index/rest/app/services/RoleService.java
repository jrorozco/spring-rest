package mx.org.index.rest.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mx.org.index.rest.app.entities.Role;
import mx.org.index.rest.app.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	
	public List<Role> getRoles(){
		return repository.findAll();
	}
	
	public Role createRole(Role role)
	{
		return repository.save(role);
	}

	public Role updateRole(Integer roleId, Role role) {
		Optional<Role> optRole = repository.findById(roleId);
		if(optRole.isPresent()) {
			return repository.save(role);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role  Id %d dosen't exists", roleId));
		}
	}

	public void deleteRole(Integer roleId) {
		Optional<Role> optRole = repository.findById(roleId);
		if(optRole.isPresent()) {
			 repository.delete(optRole.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role  Id %d dosen't exists", roleId));
		}
	}
}

package mx.org.index.rest.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.org.index.rest.app.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository< Role, Integer> {

}

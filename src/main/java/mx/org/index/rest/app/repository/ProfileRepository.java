package mx.org.index.rest.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.org.index.rest.app.entities.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{

}

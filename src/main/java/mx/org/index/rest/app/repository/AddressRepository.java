package mx.org.index.rest.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.org.index.rest.app.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}

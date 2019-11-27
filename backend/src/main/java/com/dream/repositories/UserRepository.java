package com.dream.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.User;

//@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	Boolean existsByUsername(String username);
}

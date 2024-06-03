package com.cts.agrofarmingstore.repository;

import com.cts.agrofarmingstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository class for CRUD operations in User Class
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

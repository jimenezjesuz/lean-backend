package com.example.UNIVERCIDAD.repository;

import com.example.UNIVERCIDAD.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByIdentification(String identification);

    User findByEmail(String email);

}

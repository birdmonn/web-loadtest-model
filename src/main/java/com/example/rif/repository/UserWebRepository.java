package com.example.rif.repository;

import com.example.rif.entities.UserWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserWebRepository extends JpaRepository<UserWeb, Long> {

    @Query("SELECT uw FROM UserWeb uw WHERE uw.username = ?1 " )
    UserWeb findByUsername(String username);

}

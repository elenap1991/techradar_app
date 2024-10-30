package com.techradar.polls.techradar_polls.jpa;


import com.techradar.polls.techradar_polls.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.userId = ?1")
    User findByUserId(Long userId);
}

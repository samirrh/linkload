package io.sh.linkload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.sh.linkload.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

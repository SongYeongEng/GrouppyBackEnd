package com.grouppy.spring;
import org.springframework.data.jpa.repository.JpaRepository;

//what does this do ?
public interface UserRepository extends JpaRepository<User, Long> {}
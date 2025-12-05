package se.yrgo.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import se.yrgo.domain.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findUserByEmail(String email);
    
    
}
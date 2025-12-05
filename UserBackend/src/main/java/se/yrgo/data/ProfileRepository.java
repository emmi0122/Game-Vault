package se.yrgo.data;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import se.yrgo.domain.*;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

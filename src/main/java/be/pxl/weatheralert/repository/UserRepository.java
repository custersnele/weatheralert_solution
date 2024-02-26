package be.pxl.weatheralert.repository;

import be.pxl.weatheralert.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

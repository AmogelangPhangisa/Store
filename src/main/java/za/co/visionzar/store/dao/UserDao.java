package za.co.visionzar.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import za.co.visionzar.store.POJO.User;

public interface UserDao extends JpaRepository<User,Integer> {

    User findByEmailId(@Param("email") String email);
}

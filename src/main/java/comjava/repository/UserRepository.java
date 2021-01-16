package comjava.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import comjava.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

   User findByUserName(String userName);
}

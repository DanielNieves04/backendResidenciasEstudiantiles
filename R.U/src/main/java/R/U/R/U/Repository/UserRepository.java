package R.U.R.U.Repository;

import R.U.R.U.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByMail(String mail);

    @Query("SELECT " +
            "u.idUsers as idUsers, " +
            "u.first_name as firstName, " +
            "u.lastName as lastName, " +
            "u.mail as mail, " +
            "u.imageUrl as imageUrl " +
            "FROM User u WHERE u.idUsers = :id")
    Optional<UserBasicInfo> findBasicInfoById(@Param("id") Long id);

}

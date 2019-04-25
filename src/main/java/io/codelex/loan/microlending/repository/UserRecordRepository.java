package io.codelex.loan.microlending.repository;

import io.codelex.loan.microlending.repository.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {

    @Query("select count(user) > 0 from UserRecord user"
            + " where lower (user.email) like lower(:email)")
    boolean isUserPresent(@Param("email") String email);

    @Query("select user from UserRecord user"
            + " where user.email = :email")
    UserRecord findByEmail(@Param("email") String email);

}

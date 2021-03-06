package Group2A.SDMS.Repositories;

import Group2A.SDMS.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    // Users is the name of the entity, its a singular object (returns 1 user)
    Users findByUsername(String username);

    // Example of a custom select query
    @Query("FROM Users WHERE deleted = false")
    List<Users> getAllUsers();

    // Queries can also be defined as follows:
    // @Query("<You query as you would write it normally with sql>", nativeQuery = true)

    // Use @modifying whenever modifying data (aka delete or update queries)
    // Keep in mind to never delete data but only update the deleted field
    @Modifying
    @Query("update Users set deleted = true where userID = :userID")
    void deleteUserByID(@Param("userID") int userID);

    @Modifying
    @Query(value = "update Users set email = :email, firstname = :firstname, lastname = :lastname," +
            "address = :address, wage = :wage where username = :username")
    void editUser( @Param("username") String username, @Param("email") String email, @Param("firstname") String firstname,
                   @Param("lastname") String lastname, @Param("address") String address, @Param("wage") double wage);
}

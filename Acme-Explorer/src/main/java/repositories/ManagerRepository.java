
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	@Query("select m from Manager m where m.isSuspicious = true")
	Collection<Manager> listSuspicious();

	@Query("select m from Manager m where m.userAccount.id = ?1")
	Manager findByUserAccountId(int id);

}

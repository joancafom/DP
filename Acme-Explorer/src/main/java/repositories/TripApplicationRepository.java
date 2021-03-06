
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.TripApplication;

@Repository
public interface TripApplicationRepository extends JpaRepository<TripApplication, Integer> {

	@Query("select trip.tripApplications from Trip trip where trip.manager.id=?1")
	Collection<TripApplication> findTripApplicationsManagedByManager(int managerId);

	@Query("select t from TripApplication t where t.explorer.id = ?1 order by t.status")
	Collection<TripApplication> findTripApplicationsByExplorer(int exploredId);

	@Query("select t from TripApplication t where t.status='ACCEPTED' and t.explorer.id=?1")
	Collection<TripApplication> findAcceptedTripApplicationsByExplorer(int id);

	@Query("select ta from TripApplication ta where ta.explorer.id = ?1 and ta.trip.id = ?2")
	TripApplication findByExplorerIdAndTripId(int explorerId, int tripId);

	@Query("select t from TripApplication t where t.trip.id=?1")
	Collection<TripApplication> findAllByTrip(int id);

}


package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TripRepository;
import security.LoginService;
import domain.Audition;
import domain.Category;
import domain.Explorer;
import domain.Finder;
import domain.LegalText;
import domain.Manager;
import domain.Note;
import domain.Ranger;
import domain.Sponsorship;
import domain.Stage;
import domain.Story;
import domain.SurvivalClass;
import domain.TagValue;
import domain.Trip;
import domain.TripApplication;

@Service
@Transactional
public class TripService {

	// Managed repository ------------------

	@Autowired
	private TripRepository	tripRepository;

	// Supporting services -----------------

	@Autowired
	private ExplorerService	explorerService;


	// Constructors ------------------------

	public TripService() {
		super();
	}

	// Simple CRUD methods -----------------

	public Trip create(final Collection<Stage> stages, final LegalText legalText, final Category category, final Ranger ranger, final Manager manager) {

		// REVISAR !!!
		// Un manager puede crear trips que no son suyos?

		String ticker = "";
		double price = 0.0;
		final List<Sponsorship> sponsorships = new ArrayList<Sponsorship>();
		final List<Story> stories = new ArrayList<Story>();
		final List<Note> notes = new ArrayList<Note>();
		final List<Audition> auditions = new ArrayList<Audition>();
		final List<TripApplication> tripApplications = new ArrayList<TripApplication>();
		final List<TagValue> tagValues = new ArrayList<TagValue>();
		final List<SurvivalClass> survivalClasses = new ArrayList<SurvivalClass>();
		Trip trip;

		trip = new Trip();

		final LocalDate date = new LocalDate();
		final Integer year = new Integer(date.getYear());
		final String yy = new String(year.toString());

		final Integer month = new Integer(date.getMonthOfYear());
		final String mm = new String(month.toString());

		final Integer day = new Integer(date.getDayOfMonth());
		final String dd = new String(day.toString());

		final String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String wwww = "";

		for (int i = 0; i < 4; i++) {
			final Random r = new Random();
			wwww += abc.charAt(r.nextInt(abc.length()));
		}

		ticker = yy.substring(2).toUpperCase() + mm.toUpperCase() + dd.toUpperCase() + "-" + wwww.toUpperCase();

		trip.setTicker(ticker);

		Assert.notNull(stages);

		for (final Stage s : stages) {
			Assert.notNull(s);
			price += s.getPrice();
			s.setTrip(trip);

		}

		trip.setPrice(price);
		trip.setSponsorships(sponsorships);
		trip.setStories(stories);
		trip.setNotes(notes);
		trip.setAuditions(auditions);
		trip.setTripApplications(tripApplications);
		trip.setTagValues(tagValues);

		Assert.notNull(legalText);
		trip.setLegalText(legalText);
		legalText.getTrips().add(trip);

		trip.setStages(stages);

		Assert.notNull(category);
		trip.setCategory(category);
		category.getTrips().add(trip);

		Assert.notNull(ranger);
		trip.setRanger(ranger);
		ranger.getTrips().add(trip);
		trip.setSurvivalClasses(survivalClasses);
		trip.setManager(manager);
		manager.getTrips().add(trip);

		return trip;
	}

	public Collection<Trip> findAll() {
		Collection<Trip> trips;

		Assert.notNull(this.tripRepository);
		trips = this.tripRepository.findAll();
		Assert.notNull(trips);

		return trips;
	}

	public Trip findOne(final int tripId) {
		// REVISAR !!!
		// Debe tener alg�n assert?
		Trip trip;

		trip = this.tripRepository.findOne(tripId);

		return trip;
	}

	public Trip save(final Trip trip) {
		Assert.notNull(trip);

		final Date currentDate = new Date();
		Assert.isTrue(trip.getPublicationDate().after(currentDate));

		return this.tripRepository.save(trip);
	}

	public void delete(final Trip trip) {
		Assert.notNull(trip);

		final Date currentDate = new Date();
		Assert.isTrue(trip.getPublicationDate().after(currentDate));
		Assert.isTrue(this.tripRepository.exists(trip.getId()));

		this.tripRepository.delete(trip);
	}

	// Other business methods --------------

	public Collection<Trip> findByKeyWord(final String keyWord) {
		Assert.notNull(keyWord);

		Collection<Trip> trips;

		Assert.notNull(this.tripRepository);
		trips = this.tripRepository.findByKeyWord(keyWord);
		Assert.notNull(trips);

		return trips;
	}

	// REVISAR !!!
	// Qu� significa navegar el tree de categories?
	public Collection<Trip> findByCategory(final Category category) {
		Assert.notNull(category);

		final Collection<Trip> trips;

		Assert.notNull(this.tripRepository);
		trips = this.tripRepository.findByCategoryId(category.getId());
		Assert.notNull(trips);

		return trips;
	}

	public void cancel(final Trip trip) {
		Assert.notNull(trip);

		Date currentDate;
		currentDate = new Date();

		Assert.notNull(trip.getCancelationReason());
		Assert.isTrue(trip.getPublicationDate().before(currentDate));
		Assert.isTrue(trip.getStartingDate().after(currentDate));
		Assert.isTrue(trip.getEndingDate().after(currentDate));

		this.tripRepository.save(trip);
	}

	public Collection<Trip> findAllManagedBy(final Manager manager) {
		Assert.notNull(manager);

		Collection<Trip> trips;

		Assert.notNull(this.tripRepository);
		trips = this.tripRepository.findAllManagedBy(manager.getId());
		Assert.notNull(trips);

		return trips;
	}

	public Collection<Trip> findByFinder(final Finder finder) {
		Assert.notNull(finder);

		Explorer explorer;
		Collection<Trip> trips;

		explorer = this.explorerService.findByUserAccount(LoginService.getPrincipal());

		Assert.isTrue(finder.getId() == explorer.getFinder().getId());

		if (finder.getMinRange() == null && finder.getMaxRange() == null && finder.getMinDate() == null && finder.getMaxDate() == null)
			trips = this.tripRepository.findPublishedButNotStarted();
		else
			trips = this.tripRepository.findByFinderAttributes(finder.getMinRange(), finder.getMaxRange(), finder.getMinDate(), finder.getMaxDate(), finder.getKeyword());

		Assert.notNull(trips);

		return trips;
	}

}

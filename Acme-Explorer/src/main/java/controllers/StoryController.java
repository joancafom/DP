
package controllers;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.StoryService;
import services.TripService;
import domain.Story;

@Controller
@RequestMapping("/story")
public class StoryController extends AbstractController {

	// Services -------------------------------

	@Autowired
	private StoryService	storyService;

	@Autowired
	private TripService		tripService;


	// Constructors ---------------------------

	public StoryController() {
		super();
	}

	// Display --------------------------------

	// Listing --------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int tripId) {
		ModelAndView res;
		Collection<Story> stories;

		stories = this.storyService.findAllByTripId(tripId);
		Assert.isTrue(this.tripService.findOne(tripId).getPublicationDate().before(new Date()));

		res = new ModelAndView("story/list");
		res.addObject("stories", stories);
		res.addObject("requestURI", "story/list.do");
		res.addObject("tripURI", "trip/display.do?tripId=");

		return res;
	}
	// Creation -------------------------------

	// Edition --------------------------------

	// Ancillary methods ----------------------

}


package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.ExplorerService;
import services.FinderService;
import services.UserAccountService;
import domain.Explorer;
import domain.Finder;

@Controller
@RequestMapping("/explorer")
public class ExplorerController extends AbstractController {

	//Services
	@Autowired
	private UserAccountService	userAccountService;

	@Autowired
	private ExplorerService		explorerService;

	@Autowired
	private FinderService		finderService;


	//Creation
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		final ModelAndView res;
		final Explorer explorer;
		final UserAccount userAccount;
		final Finder finder;
		Finder finderS;

		userAccount = this.userAccountService.create();
		finder = this.finderService.create();
		finderS = this.finderService.save(finder);
		explorer = this.explorerService.create(userAccount, finderS);

		res = this.createEditModelAndView(explorer);

		return res;

	}

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Explorer explorer, final BindingResult bindingResult) {

		ModelAndView res;

		if (bindingResult.hasErrors())
			res = this.createEditModelAndView(explorer);
		else {

			final String hashedPassword;
			final String oldPassword = explorer.getUserAccount().getPassword();

			try {
				final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				hashedPassword = encoder.encodePassword(explorer.getUserAccount().getPassword(), null);

				explorer.getUserAccount().setPassword(hashedPassword);

				this.explorerService.save(explorer);
				res = new ModelAndView("redirect:/");

			} catch (final DataIntegrityViolationException oops) {

				explorer.getUserAccount().setPassword(oldPassword);
				res = this.createEditModelAndView(explorer, "actor.username.duplicated");

			} catch (final Throwable oops) {
				explorer.getUserAccount().setPassword(oldPassword);
				res = this.createEditModelAndView(explorer, "actor.commit.error");
			}
		}

		return res;

	}
	//Ancillary methods
	protected ModelAndView createEditModelAndView(final Explorer explorer) {
		return this.createEditModelAndView(explorer, null);
	}

	protected ModelAndView createEditModelAndView(final Explorer explorer, final String messageCode) {
		final ModelAndView res;

		res = new ModelAndView("explorer/edit");
		res.addObject("actorClassName", "explorer");
		res.addObject("explorer", explorer);
		res.addObject("requestURI", "explorer/create.do");
		res.addObject("actionURI", "explorer/edit.do");

		res.addObject("messageCode", messageCode);

		return res;
	}
}

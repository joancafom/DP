
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;
import domain.Trip;

@Service
@Transactional
public class CategoryService {

	// Managed repository ------------------

	@Autowired
	private CategoryRepository	categoryRepository;


	// Supporting services -----------------

	// Constructors ------------------------

	public CategoryService() {
		super();
	}

	// Simple CRUD methods -----------------

	public Category create(final Category parentCategory) {
		final Category category;

		category = new Category();

		category.setParentCategory(this.categoryRepository.findOne(parentCategory.getId()));
		category.setTrips(new HashSet<Trip>());
		category.setChildCategories(new HashSet<Category>());

		return category;
	}

	public Collection<Category> findAll() {
		final Collection<Category> categories;

		categories = this.categoryRepository.findAll();
		Assert.notNull(categories);

		return categories;
	}

	public Category findOne(final int categoryId) {
		final Category category;

		category = this.categoryRepository.findOne(categoryId);
		Assert.notNull(category);

		return category;
	}

	public Category save(final Category category) {

		Assert.notNull(category);

		Boolean res = false;

		Assert.notNull(category.getParentCategory());

		for (final Category c : category.getParentCategory().getChildCategories())
			if (c.getName().toLowerCase().equals(category.getName().toLowerCase()) && !c.equals(category)) {
				res = true;
				break;
			}

		Assert.isTrue(!res);
		Assert.isTrue(!category.getName().equals("CATEGORY"));

		for (final Trip t : category.getTrips())
			t.setCategory(category);

		return this.categoryRepository.save(category);
	}

	public void delete(final Category category) {

		Assert.notNull(category);
		Assert.isTrue(this.categoryRepository.exists(category.getId()));

		final Collection<Category> categories = new HashSet<Category>();
		final Category parentCategory = this.categoryRepository.findOne(category.getParentCategory().getId());

		categories.add(category);

		for (final Category c : this.categoryRepository.findAll())
			if (categories.contains(c.getParentCategory()))
				categories.add(c);

		for (final Category c : categories) {
			if (!c.equals(category))
				Assert.isTrue(categories.contains(c.getParentCategory()));
			for (final Trip t : c.getTrips())
				t.setCategory(parentCategory);
			this.categoryRepository.delete(c);
		}

	}
	// Other business methods --------------

	public Category getParentRootCategory() {

		return this.categoryRepository.findByName("CATEGORY");
	}
}

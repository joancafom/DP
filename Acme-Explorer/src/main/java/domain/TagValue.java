
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class TagValue extends DomainEntity {

	private String	value;


	@NotBlank
	public String getValue() {
		return this.value;
	}

	public void setValue(final String value) {
		this.value = value;
	}


	//Relationships

	private Tag		tag;
	private Trip	trip;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Tag getTag() {
		return this.tag;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Trip getTrip() {
		return this.trip;
	}

	public void setTag(final Tag tag) {
		this.tag = tag;
	}

	public void setTrip(final Trip trip) {
		this.trip = trip;
	}

}

package acme.entities.delor;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.entities.item.Item;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Delor extends AbstractEntity{
	
	
	protected static final long serialVersionUID = 1L;
	
	
	@NotBlank
	@Pattern(regexp="^[0-9]{6}:(([0-2]{2}0[1-9]|1[0-2])([0-2][0-9]|3[0-1]))$", message="Incorrect format, follow the example 000000:220101")
	@Column(unique=true)
	protected String keylet;
	
	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date instantiationMoment;
	
	@NotBlank
	@Size(max=100)
	protected String subject;
	
	
	@NotBlank
	@Size(max=255)
	protected String explanation;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date initialPeriod;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date finalPeriod;
	
	
	@NotNull
	@Valid
	protected Money income;
	

	@URL
	protected String moreInfo;
	

	@NotNull
	@Valid
	@OneToOne(optional=false)
	protected Item item;

	
}

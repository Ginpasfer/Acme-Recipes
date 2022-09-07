package acme.entities.pimpam;



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
public class Pimpam extends AbstractEntity{
	
	
	protected static final long serialVersionUID = 1L;
	
	
	@NotBlank
	@Pattern(regexp="^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$", message="Incorrect format, follow the example JE:DBV-201")
	@Column(unique=true)
	protected String codigo;
	
	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	@NotBlank
	@Size(max=100)
	protected String titulo;
	
	
	@NotBlank
	@Size(max=255)
	protected String descripcion;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date periodoInicial;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date periodoFinal;
	
	
	@NotNull
	@Valid
	protected Money presupuesto;
	

	@URL
	protected String enlace;
	

	@NotNull
	@Valid
	@OneToOne(optional=false)
	protected Item item;

	
}

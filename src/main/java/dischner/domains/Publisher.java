package dischner.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Pattern(regexp = "^[A-z]+\\h?[A-z]+(\\h?[A-z]+)?(\\h?[A-z]+)?$", message = "Name must be in \"First Last\" format (can contain up to two middle names)")
	private String name;

	@NotBlank
	private String street;

	@NotBlank
	private String city;

	@Pattern(regexp = "^[A-z]{2}$", message = "State must be 2 characters long")
	private String state;

	@Pattern(regexp = "^([0-9]{5})(-([0-9]{4}))?$", message = "Zip must be in XXXXX or XXXXX-XXXX format")
	private String zip;

	@OneToMany
	@JoinColumn(name = "publisher_id")
	private Set<Book> books = new HashSet<>();

	public Publisher() {
	}

	public Publisher(String name, String street, String city, String state,
	                 String zip,
	                 Set<Book> books) {
		this.name = name;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.books = books;
	}

	@Override
	public String toString() {
		return "Publisher{" +
		       "id=" + id +
		       ", name='" + name + '\'' +
		       ", street='" + street + '\'' +
		       ", city='" + city + '\'' +
		       ", state='" + state + '\'' +
		       ", zip=" + zip +
		       ", books=" + books +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Publisher publisher)) return false;
		return Objects.equals(id, publisher.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

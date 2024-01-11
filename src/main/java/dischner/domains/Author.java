package dischner.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "First name is required")
	private String firstName;

	@NotBlank(message = "Last name is required")
	private String lastName;

	@ManyToMany(mappedBy = "authors")
	private Set<Book> books = new HashSet<>();

	public Author() {
	}

	public Author(String firstName, String lastName, Set<Book> books) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author{" +
		       "id=" + id +
		       ", firstName='" + firstName + '\'' +
		       ", lastName='" + lastName + '\'' +
		       ", books=" + books +
		       '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Author author)) return false;
		return Objects.equals(id, author.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

package dischner.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Title is required")
	private String title;

	@NotBlank(message = "ISBN is required")
	private String isbn;

	@ManyToOne
	private Publisher publisher;

	@ManyToMany
	@JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> authors = new HashSet<>();

	public Book() {
	}

	public Book(String title, String isbn, Set<Author> authors) {
		this.title = title;
		this.isbn = isbn;
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "Book{" +
		       "id=" + id +
		       ", title='" + title + '\'' +
		       ", isbn='" + isbn + '\'' +
		       ", authors=" + authors +
		       "}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Book book)) return false;
		return Objects.equals(id, book.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}

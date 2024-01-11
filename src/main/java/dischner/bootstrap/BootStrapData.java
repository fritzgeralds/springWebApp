package dischner.bootstrap;

import dischner.domains.Author;
import dischner.domains.Book;
import dischner.domains.Publisher;
import dischner.repositories.AuthorRepository;
import dischner.repositories.BookRepository;
import dischner.repositories.PublisherRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class BootStrapData {
	@Bean
	public ApplicationRunner dataLoader(AuthorRepository authorRepository,
	                                    BookRepository bookRepository,
	                                    PublisherRepository publisherRepository) {

		return args -> {
			Author auth = new Author("Eric", "Evans", new HashSet<>());
			Book book = new Book("Domain Driven Design", "1234567890",
			                     new HashSet<>());
			Publisher pub = new Publisher("Some Publisher", "123 Sesame St",
			                              "City", "ST",
			                              "12345", new HashSet<>());
			auth.getBooks().add(book);
			book.getAuthors().add(auth);
			book.setPublisher(pub);
			pub.getBooks().add(book);

			publisherRepository.save(pub);
			authorRepository.save(auth);
			bookRepository.save(book);

			System.out.print("Authors: ");
			System.out.println(authorRepository.count());
			System.out.print("Books: ");
			System.out.println(bookRepository.count());
			System.out.print("Publishers: ");
			System.out.println(publisherRepository.count());
		};
	}
}

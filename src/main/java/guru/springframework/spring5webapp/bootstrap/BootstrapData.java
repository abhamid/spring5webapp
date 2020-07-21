package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;;

@Component
public class BootstrapData implements CommandLineRunner {
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	

	public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Publisher publisher = new Publisher("Wrox Publisher", "6 Down Street", "Kolkata", "WestBengal", "700014");
		this.publisherRepository.save(publisher);		
		
		Author ericEvan = new Author("Eric", "Evan");
		Book domainDrivenDesign = new Book("Domain Driven Design", "123456");		
		
		ericEvan.addBook(domainDrivenDesign);
		domainDrivenDesign.addAuthor(ericEvan);
		domainDrivenDesign.setPublisher(publisher);
		publisher.getBooks().add(domainDrivenDesign);
				
		
		this.authorRepository.save(ericEvan);
		this.bookRepository.save(domainDrivenDesign);		
		this.publisherRepository.save(publisher);
		
		Author marioPuzo = new Author("Mario", "Puzo");
		Book godFather = new Book("God Father", "123456");
		
		marioPuzo.addBook(godFather);
		godFather.addAuthor(marioPuzo);
		godFather.setPublisher(publisher);
		publisher.getBooks().add(godFather);
		
		
		this.authorRepository.save(marioPuzo);
		this.bookRepository.save(godFather);	
		this.publisherRepository.save(publisher);		
		
		System.out.println("Number of Books: " + this.bookRepository.count());
		System.out.println("Number of Authors: " + this.authorRepository.count());
		System.out.println("Number of Publishers: " + this.publisherRepository.count());
		System.out.println("Number of Books printed by publisher:" + publisher.getBooks().size());
		
		
	}

}

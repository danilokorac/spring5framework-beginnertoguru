package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher pub = new Publisher();
        pub.setName("National library");
        pub.setCity("Beogradska 11");
        pub.setState("PUBLISHED");

        publisherRepository.save(pub);

        System.out.println("Publisher count: " + publisherRepository.count());

        Author dan = new Author("Dan", "Mans");
        Book ddd =  new Book("Doman Driven Design", "123231");
        dan.getBooks().add(ddd);
        ddd.getAuthors().add(dan);

        ddd.setPublisher(pub);
        pub.getBooks().add(ddd);

        authorRepository.save(dan);
        bookRepository.save(ddd);
        publisherRepository.save(pub);

        Author god = new Author("Goodie", "Godson");
        Book rsps = new Book("Rich soon, poor son", "2131523");

        god.getBooks().add(rsps);
        rsps.getAuthors().add(god);
        rsps.setPublisher(pub);
        pub.getBooks().add(rsps);


        authorRepository.save(god);
        bookRepository.save(rsps);
        publisherRepository.save(pub);


        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher number of books: " + pub.getBooks().size());

    }
}

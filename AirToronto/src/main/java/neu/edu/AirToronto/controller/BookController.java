package neu.edu.AirToronto.controller;

import neu.edu.AirToronto.entities.Book;
import neu.edu.AirToronto.repo.BookRepository;
import neu.edu.common.utils.CommonUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author YUlia
 * @version 1.0
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // CREATE operation
    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        try {
            return ResponseEntity.ok(CommonUtils.success(bookRepository.save(book)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(CommonUtils.failed("Cannot create book!"));
        }
    }

    // READ operation - get all books
    @GetMapping("/getAll")
    public ResponseEntity<String>  getAllBooks() {
        return ResponseEntity.ok(CommonUtils.success(bookRepository.findAll()));
    }

    // READ operation - get a book by id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return ResponseEntity.ok(bookOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // UPDATE operation
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable("id") String id, @RequestBody Book book) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book updatedBook = bookOptional.get();
            updatedBook.setUserid(book.getUserid());
            updatedBook.setFlightid(book.getFlightid());
            updatedBook.setDate(book.getDate());
            updatedBook.setServer(book.getServer());
            return ResponseEntity.ok(bookRepository.save(updatedBook));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE operation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable("id") String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            bookRepository.delete(bookOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findByUser")
    public ResponseEntity<String> getAllBooksForUser(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(CommonUtils.success(bookRepository.findAllByUserid(userId)));
    }
}


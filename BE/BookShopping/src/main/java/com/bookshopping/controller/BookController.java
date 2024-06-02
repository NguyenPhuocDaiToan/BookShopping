package com.bookshopping.controller;

import com.bookshopping.model.Book;
import com.bookshopping.payload.request.BookIdsRequest;
import com.bookshopping.payload.response.RelativeResponse;
import com.bookshopping.payload.response.ResponseMessage;
import com.bookshopping.payload.response.SemanticResponse;
import com.bookshopping.service.AIService;
import com.bookshopping.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private AIService aiService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/findRandomBooksByCategoryIds")
    public ResponseEntity<List<Integer>> findRandomBooksByCategoryIds(@RequestBody BookIdsRequest data) {
        return new ResponseEntity<>(bookService.findRandomBooksByCategoryIds(data.getIds(), data.getNumberRecord()), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<Book>> paginate(@PageableDefault(size = 48) Pageable pageable) {
        return new ResponseEntity<>(bookService.paginate(pageable), HttpStatus.OK);
    }

    @GetMapping("/findByNumberRecord")
    public ResponseEntity<List<Book>> getBooks(@RequestParam int numberRecord) {
        return new ResponseEntity<>(bookService.findBookByNumberRecord(numberRecord), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createBook(@RequestBody Book book) {
        System.out.println("Create new book");
        Book bookCreate = bookService.save(book);
        if(bookCreate == null)
            return new ResponseEntity<>(new ResponseMessage("Thêm sách thất bại."), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ResponseMessage("Thêm mới sách thành công !!!"), HttpStatus.OK);
    }

    @PostMapping("/create-multi")
    public ResponseEntity<ResponseMessage> createMultiBook(@RequestBody List<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            System.out.println("Create new book");
            Book bookCreate = bookService.save(books.get(i));
            //            if(bookCreate == null)
            //                return new ResponseEntity<>(new ResponseMessage("Thêm sách thất bại."), HttpStatus.BAD_REQUEST);
            //            return new ResponseEntity<>(new ResponseMessage("Thêm mới sách thành công !!!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("Thêm mới sách thành công !!!"), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<Book> findById(@RequestParam Integer id) {
        System.out.println("Find by id");
        Book book = bookService.findById(id);
        System.out.println(book.getId());
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

//    @GetMapping("/findBooksSameCategory")
//    public ResponseEntity<List<Book>> findBooksSameCategory(@RequestParam String category) {
//        System.out.println("Find by category");
//        return new ResponseEntity<>(bookService.findBooksByCategory(category), HttpStatus.OK);
//    }

    @GetMapping("/findBooksSameAuthor")
    public ResponseEntity<List<Book>> findBooksSameAuthor(@RequestParam String author) {
        System.out.println("Find by author");
        return new ResponseEntity<>(bookService.findBooksByAuthor(author), HttpStatus.OK);
    }

    @GetMapping("/findBooksSameCategoryLimit")
    public ResponseEntity<List<Book>> findBooksSameCategory(@RequestParam String category,
                                                            @RequestParam(defaultValue = "20") int numberRecord) {
        System.out.println("Find by category");
        return new ResponseEntity<>(bookService.findBookByNumberRecordSameCategory(category, numberRecord), HttpStatus.OK);
    }

    @GetMapping("/findBooksSameAuthorLimit")
    public ResponseEntity<List<Book>> findBooksSameAuthor(@RequestParam String author,
                                                          @RequestParam(defaultValue = "20") int numberRecord) {
        System.out.println("Find by author");
        return new ResponseEntity<>(bookService.findBookByNumberRecordSameAuthor(author, numberRecord), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Book>> search(@RequestParam String q, @PageableDefault(size = 48) Pageable pageable) {
        System.out.println("Search value = " + q);
        return new ResponseEntity<>(bookService.search(q, pageable), HttpStatus.OK);
    }

    @GetMapping("/recommend")
    public ResponseEntity<List<Book>> recommendation(@RequestParam String idBooks) {
        List<Integer> list = Arrays.asList(idBooks.split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());


        System.out.println(list);

        List<Integer> bookIdsRecommend = aiService.getRecommendation(list);

        List<Book> books = bookService.getBooksRecommend(bookIdsRecommend);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/relative")
    public ResponseEntity<List<Book>> getRelative(@RequestParam Integer bookId) {
        List<RelativeResponse> relatives = aiService.getBooksRelative(bookId);
        List<Integer> listIds = relatives.stream().map(RelativeResponse::get_id).collect(Collectors.toList());
        List<Book> books = bookService.getBooksRecommend(listIds);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/semantic")
    public ResponseEntity<List<Book>> getRelative(@RequestParam String text) {
        List<SemanticResponse> semantics = aiService.getBooksSemantic(text);
        List<Integer> listIds = semantics.stream().map(SemanticResponse::get_id).collect(Collectors.toList());

        List<Book> books = bookService.getBooksRecommend(listIds);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}

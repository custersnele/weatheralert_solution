package be.pxl.weatheralert.api;

import be.pxl.weatheralert.domain.Book;
import be.pxl.weatheralert.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createBookTest() throws Exception {
        Book newBook = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        Mockito.when(bookService.saveBook(ArgumentMatchers.any(Book.class))).thenReturn(newBook);

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("The Great Gatsby"));
    }

    @Test
    public void getBookByIdTest() throws Exception {
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        Mockito.when(bookService.getBookById(1L)).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("The Great Gatsby"));
    }

    @Test
    public void updateBookTest() throws Exception {
        Book updatedBook = new Book("The Great Gatsby", "Fitzgerald");
        Mockito.when(bookService.updateBook(ArgumentMatchers.eq(1L), ArgumentMatchers.any(Book.class))).thenReturn(updatedBook);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Fitzgerald"));
    }

    @Test
    public void deleteBookTest() throws Exception {
        Mockito.doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/books/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

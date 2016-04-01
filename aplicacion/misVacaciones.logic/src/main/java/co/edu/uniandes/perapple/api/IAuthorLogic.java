package co.edu.uniandes.perapple.api;

import co.edu.uniandes.perapple.entities.AuthorEntity;
import co.edu.uniandes.perapple.entities.BookEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import java.util.List;

public interface IAuthorLogic {

    public List<AuthorEntity> getAuthors();

    public AuthorEntity getAuthor(Long id) throws BusinessLogicException;

    public AuthorEntity createAuthor(AuthorEntity entity);

    public AuthorEntity updateAuthor(AuthorEntity entity);

    public void deleteAuthor(Long id);

    public BookEntity addBook(Long bookId, Long authorId) throws BusinessLogicException;

    public void removeBook(Long bookId, Long authorId);

    public List<BookEntity> replaceBooks(List<BookEntity> books, Long authorId) throws BusinessLogicException;

    public List<BookEntity> getBooks(Long authorId);

    public BookEntity getBook(Long authorId, Long bookId);
}

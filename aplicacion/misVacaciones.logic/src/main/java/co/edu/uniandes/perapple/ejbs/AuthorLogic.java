package co.edu.uniandes.perapple.ejbs;

import co.edu.uniandes.perapple.api.IAuthorLogic;
import co.edu.uniandes.perapple.api.IBookLogic;
import co.edu.uniandes.perapple.entities.AuthorEntity;
import co.edu.uniandes.perapple.entities.BookEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import co.edu.uniandes.perapple.persistence.AuthorPersistence;
import co.edu.uniandes.perapple.persistence.BookPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthorLogic implements IAuthorLogic {

    private static final Logger logger = Logger.getLogger(AuthorLogic.class.getName());

    @Inject
    private AuthorPersistence persistence;

    @Inject
    IBookLogic bookLogic;

    @Inject
    private BookPersistence bookPersistence;

    @Override
    public List<AuthorEntity> getAuthors() {
        logger.info("Inicia proceso de consultar todos los autores");
        List<AuthorEntity> authors = persistence.findAll();
        logger.info("Termina proceso de consultar todos los autores");
        return authors;
    }

    @Override
    public AuthorEntity getAuthor(Long id) throws BusinessLogicException {
        logger.log(Level.INFO, "Inicia proceso de consultar autor con id={0}", id);
        AuthorEntity author = persistence.find(id);
        if (author == null) {
            logger.log(Level.SEVERE, "El autor con el id {0} no existe", id);
            throw new BusinessLogicException("El autor solicitado no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar autor con id={0}", id);
        return author;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity entity) {
        logger.info("Inicia proceso de creación de autor");
        persistence.create(entity);
        logger.info("Termina proceso de creación de autor");
        return entity;
    }

    @Override
    public AuthorEntity updateAuthor(AuthorEntity entity) {
        logger.log(Level.INFO, "Inicia proceso de actualizar autor con id={0}", entity.getId());
        AuthorEntity newEntity = persistence.update(entity);
        logger.log(Level.INFO, "Termina proceso de actualizar autor con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteAuthor(Long id) {
        logger.log(Level.INFO, "Inicia proceso de borrar autor con id={0}", id);
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar autor con id={0}", id);
    }

    @Override
    public BookEntity addBook(Long bookId, Long authorId) throws BusinessLogicException {
        bookLogic.addAuthor(authorId, bookId);
        return bookPersistence.find(bookId);
    }

    @Override
    public void removeBook(Long bookId, Long authorId) {
        bookLogic.removeAuthor(authorId, bookId);
    }

    @Override
    public List<BookEntity> replaceBooks(List<BookEntity> books, Long authorId) throws BusinessLogicException {
        List<BookEntity> bookList = bookPersistence.findAll();
        AuthorEntity author = persistence.find(authorId);
        for (BookEntity book : bookList) {
            if (books.contains(book)) {
                if (!book.getAuthors().contains(author)) {
                    bookLogic.addAuthor(authorId, book.getId());
                }
            } else {
                bookLogic.removeAuthor(authorId, book.getId());
            }
        }
        author.setBooks(books);
        return author.getBooks();
    }

    @Override
    public List<BookEntity> getBooks(Long authorId) {
        return persistence.find(authorId).getBooks();
    }

    @Override
    public BookEntity getBook(Long authorId, Long bookId) {
        List<BookEntity> books = persistence.find(authorId).getBooks();
        BookEntity book = new BookEntity();
        book.setId(bookId);
        int index = books.indexOf(book);
        if (index >= 0) {
            return books.get(index);
        }
        return null;
    }
}

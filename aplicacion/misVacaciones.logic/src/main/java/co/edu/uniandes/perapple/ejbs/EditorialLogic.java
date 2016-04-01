package co.edu.uniandes.perapple.ejbs;

import co.edu.uniandes.perapple.api.IEditorialLogic;
import co.edu.uniandes.perapple.entities.BookEntity;
import co.edu.uniandes.perapple.entities.EditorialEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import co.edu.uniandes.perapple.persistence.BookPersistence;
import co.edu.uniandes.perapple.persistence.EditorialPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EditorialLogic implements IEditorialLogic {

    private static final Logger logger = Logger.getLogger(EditorialLogic.class.getName());

    @Inject
    private EditorialPersistence persistence;

    @Inject
    private BookPersistence bookPersistence;

    @Override
    public List<EditorialEntity> getEditorials() {
        logger.info("Inicia proceso de consultar todas las editoriales");
        List<EditorialEntity> editorials = persistence.findAll();
        logger.info("Termina proceso de consultar todas las editoriales");
        return editorials;
    }

    @Override
    public EditorialEntity getEditorial(Long id) throws BusinessLogicException {
        logger.log(Level.INFO, "Inicia proceso de consultar editorial con id={0}", id);
        EditorialEntity editorial = persistence.find(id);
        if (editorial == null) {
            logger.log(Level.SEVERE, "La editorial con el id {0} no existe", id);
            throw new BusinessLogicException("La editorial solicitada no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar editorial con id={0}", id);
        return editorial;
    }

    @Override
    public EditorialEntity createEditorial(EditorialEntity entity) {
        logger.info("Inicia proceso de creación de editorial");
        persistence.create(entity);
        logger.info("Termina proceso de creación de editorial");
        return entity;
    }

    @Override
    public EditorialEntity updateEditorial(EditorialEntity entity) {
        logger.log(Level.INFO, "Inicia proceso de actualizar editorial con id={0}", entity.getId());
        EditorialEntity newEntity = persistence.update(entity);
        logger.log(Level.INFO, "Termina proceso de actualizar editorial con id={0}", entity.getId());
        return newEntity;
    }

    @Override
    public void deleteEditorial(Long id) {
        logger.log(Level.INFO, "Inicia proceso de borrar editorial con id={0}", id);
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar editorial con id={0}", id);
    }

    @Override
    public BookEntity addBook(Long bookId, Long editorialId) {
        EditorialEntity editorialEntity = persistence.find(editorialId);
        BookEntity bookEntity = bookPersistence.find(bookId);
        bookEntity.setEditorial(editorialEntity);
        return bookEntity;
    }

    @Override
    public void removeBook(Long bookId, Long editorialId) {
        EditorialEntity editorialEntity = persistence.find(editorialId);
        BookEntity book = bookPersistence.find(bookId);
        book.setEditorial(null);
        editorialEntity.getBooks().remove(book);
    }

    @Override
    public List<BookEntity> replaceBooks(List<BookEntity> books, Long editorialId) {
        EditorialEntity editorial = persistence.find(editorialId);
        List<BookEntity> bookList = bookPersistence.findAll();
        for (BookEntity book : bookList) {
            if (books.contains(book)) {
                book.setEditorial(editorial);
            } else if (book.getEditorial() != null && book.getEditorial().equals(editorial)) {
                book.setEditorial(null);
            }
        }
        return books;
    }

    @Override
    public List<BookEntity> getBooks(Long editorialId) {
        return persistence.find(editorialId).getBooks();
    }

    @Override
    public BookEntity getBook(Long editorialId, Long bookId) {
        List<BookEntity> books = persistence.find(editorialId).getBooks();
        BookEntity book = new BookEntity();
        book.setId(bookId);
        int index = books.indexOf(book);
        if (index >= 0) {
            return books.get(index);
        }
        return null;
    }
}

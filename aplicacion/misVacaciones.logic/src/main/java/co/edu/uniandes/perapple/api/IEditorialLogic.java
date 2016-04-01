/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.perapple.api;

import co.edu.uniandes.perapple.entities.BookEntity;
import co.edu.uniandes.perapple.entities.EditorialEntity;
import co.edu.uniandes.perapple.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author af.esguerra10
 */
public interface IEditorialLogic {

    public List<EditorialEntity> getEditorials();

    public EditorialEntity getEditorial(Long id) throws BusinessLogicException;

    public EditorialEntity createEditorial(EditorialEntity entity);

    public EditorialEntity updateEditorial(EditorialEntity entity);

    public void deleteEditorial(Long id);

    public List<BookEntity> getBooks(Long editorialId);

    public BookEntity getBook(Long editorialId, Long bookId);

    public BookEntity addBook(Long bookId, Long editorialId);

    public void removeBook(Long bookId, Long editorialId);

    public List<BookEntity> replaceBooks(List<BookEntity> books, Long editorialId);
}

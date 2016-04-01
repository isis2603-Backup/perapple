package co.edu.uniandes.perapple.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class EditorialEntity extends BaseEntity implements Serializable {

    @OneToMany(mappedBy = "editorial")
    private List<BookEntity> books = new ArrayList<>();

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

}

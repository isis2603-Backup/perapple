package co.edu.uniandes.perapple.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;

@Entity
public class ViajeroEntity extends BaseEntity implements Serializable {


  //TODO implementar metodos get y set por todos los atributos de la entidad -- agregar relaciones con otras entidades utilizando anotaciones
    @Id
    private int id;

    public int getIdentificador()
    {
        return id;
    }
}

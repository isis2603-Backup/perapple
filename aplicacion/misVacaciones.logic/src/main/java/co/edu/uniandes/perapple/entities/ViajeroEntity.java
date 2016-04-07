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
import sun.security.util.Password;

@Entity
public class ViajeroEntity extends BaseEntity implements Serializable {


  //TODO  -- agregar relaciones con otras entidades utilizando anotaciones
    @Id
    private int id;

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }

    @Name
    private String name;

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }

    @Email
    private String email

     public String getEmail()
     {
         return email;
     }
     public void setEmail(String email)
     {
         this.email=email;
     }

    @Password
    private String password;

    public String getPassword()
    {
        return password;

    }
    public void setPassword(String password)
    {
        this.password=password;
    }

    @Image
    private String image;

    public String getImage()
    {
        return image;
    }
    public void setImage(String image)
    {
        this.image=image;
    }
}

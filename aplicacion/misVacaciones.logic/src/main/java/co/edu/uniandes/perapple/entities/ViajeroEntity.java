package co.edu.uniandes.perapple.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;


@Entity
public class ViajeroEntity  implements Serializable {


  //TODO  -- agregar relaciones con otras entidades utilizando anotaciones
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }

    //Name
    private String name;

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }

    //Email
    private String email;

     public String getEmail()
     {
         return email;
     }
     public void setEmail(String email)
     {
         this.email=email;
     }

    //Password
    private String password;

    public String getPassword()
    {
        return password;

    }
    public void setPassword(String password)
    {
        this.password=password;
    }

    //Imagen
    private String image;

    public String getImage()
    {
        return image;
    }
    public void setImage(String image)
    {
        this.image=image;
    }

    @PodamExclude
    @OneToMany (mappedBy = "viajero", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItinerarioEntity> itinerarios;

    public List<ItinerarioEntity> getItinerarios() {
        return itinerarios;
    }

    public void setItinerarios(List<ItinerarioEntity> itinerarios) {
        this.itinerarios = itinerarios;
    }

}

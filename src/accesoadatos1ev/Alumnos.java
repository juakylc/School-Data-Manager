/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoadatos1ev;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jborregb
 */
@XmlRootElement(name = "alumnos")
public class Alumnos {
    //Atributo que representa la lista de alumnos
    private List<Alumno> alumnos;
    
    //Constructor
    public Alumnos() {
        this.alumnos = new ArrayList<Alumno>();
    }
    
    //Constructor dado un alumno
    public Alumnos(Alumno alumno) {
        this();
        this.alumnos.add(alumno);
    }
    
    //Constructor dada una lista de alumnos
    public Alumnos(List alumnos) {
        this.alumnos.addAll(alumnos);
    }

    //Getter de la lista de alumnos
    @XmlElement(name = "alumno")
    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    //Setter de la lista de alumnos
    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    
    //Método toString
    @Override
    public String toString() {
        return "<alumnos>"+ formatearLista() +"\n</alumnos>";
    }
    
    //Método para imprimirlo por pantalla en modo xml
    private String formatearLista() {
        String str = "";
        for (Alumno alumno:alumnos) {
            str += "\n\t" + alumno.toString().replace("\n","\n\t");
        }
        return str;
    }
}

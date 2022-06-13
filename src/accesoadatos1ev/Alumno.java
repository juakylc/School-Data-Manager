/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoadatos1ev;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author jborregb
 */
@XmlRootElement(name = "alumno")
@XmlType(propOrder = {"nombre", "dni", "fecha_matricula", "ciclo", "asignaturas_matriculadas"})
public class Alumno {
    //Atributo que representa el nombre del alumno
    private String nombre;
    
    //Atributo que representa el dni del alumno
    private String dni;
    
    //Atributo que representa la fecha de matriculacion del alumno
    private String fecha_matricula;
    
    //Atributo que representa el ciclo del alumno
    private String ciclo;
    
    //Atributo que representa el numero de asignaturas matriculadas del alumno
    private int asignaturas_matriculadas;
    
    //Constructor
    public Alumno() {
        super();
    }

    //Constructor dados sus atributos
    public Alumno(String nombre, String dni, String fecha_matricula, String ciclo, int asignaturas_matriculadas) {
        this.nombre = nombre;
        this.dni = dni;
        this.fecha_matricula = fecha_matricula;
        this.ciclo = ciclo;
        this.asignaturas_matriculadas = asignaturas_matriculadas;
    }

    //Getter de nombre
    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    //Setter de nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Getter de dni
    @XmlElement(name = "dni")
    public String getDni() {
        return dni;
    }

    //Setter de dni
    public void setDni(String dni) {
        this.dni = dni;
    }

    //Getter de fecha de matricula
    @XmlElement(name = "fecha_matricula")
    public String getFecha_matricula() {
        return fecha_matricula;
    }

    //Setter de fecha de matricula
    public void setFecha_matricula(String fecha_matricula) {
        this.fecha_matricula = fecha_matricula;
    }

    //Getter de ciclo
    @XmlElement(name = "ciclo")
    public String getCiclo() {
        return ciclo;
    }

    //Setter de ciclo
    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    //Getter de asignaturas matriculadas
    @XmlElement(name = "asignaturas_matriculadas")
    public int getAsignaturas_matriculadas() {
        return asignaturas_matriculadas;
    }

    //Setter de asignaturas matriculadas
    public void setAsignaturas_matriculadas(int asignaturas_matriculadas) {
        this.asignaturas_matriculadas = asignaturas_matriculadas;
    }
    
    //Método toString que lo mostrará como si fuese un xml
    @Override
    public String toString() {
        return "<alumno>\n\t<nombre>"+nombre+"</nombre>"
                     + "\n\t<dni>"+dni+"</dni>"
                     + "\n\t<fecha_matricula>"+fecha_matricula+"</fecha_matricula>"
                     + "\n\t<ciclo>"+ciclo+"</ciclo>"
                     + "\n\t<asignaturas_matriculadas>"+asignaturas_matriculadas+"</asignaturas_matriculadas>"
                + "\n</alumno>";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoadatos1ev;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author jborregb
 */
public class ConnectionMethods {
    
    //Metodo para crear tabla
    public static void createTable() {
        try {
            //Preguntamos si necesitamos crear la tabla
            System.out.println("Necesitas crear la tabla? (Si/No)");
            String ans = AccesoADatos1Ev.sc.nextLine();
        
            //Si la respuesta es si
            if ("si".equals(ans.toLowerCase())) {
                //Creamos la conexion
                Connection conexion = DriverManager.getConnection(AccesoADatos1Ev.url, AccesoADatos1Ev.usuario, AccesoADatos1Ev.password);
                System.out.println("");
                System.out.println("Creando tabla...");
                
                //Preparamos la sentencia
                Statement sentencia = conexion.createStatement();
                
                //Ejecutamos la sentencia de si existe la tabla la borramos
                sentencia.executeUpdate("DROP TABLE IF EXISTS `northwind`.`alumnos`;");
                
                //Ejecutamos la sentencia de crear la tabla
                sentencia.executeUpdate("CREATE TABLE `northwind`.`alumnos` (\n" +
                                        "  `nombre` VARCHAR(45) NOT NULL,\n" +
                                        "  `dni` VARCHAR(9) NOT NULL,\n" +
                                        "  `fecha_matricula` DATE NOT NULL,\n" +
                                        "  `ciclo` VARCHAR(45) NOT NULL,\n" +
                                        "  `asignaturas_matriculadas` INT NOT NULL,\n" +
                                         "  PRIMARY KEY (`dni`));");
                
                //Preguntaremos si además quiere importar el xml
                System.out.println("");
                System.out.println("Quieres importar el xml? (Si/No)");
                String ans2 = AccesoADatos1Ev.sc.nextLine();
            
                //Si la respuesta es si
                if ("si".equals(ans2.toLowerCase())) {
                    //importamos
                    importarXML("Alumnos.xml");
                }
                System.out.println("Tabla creada con exito!");
                System.out.println("");
                //Cerramos todo
                sentencia.close();
                conexion.close();
            } 
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Metodo para listar la tabla
    public static void show() {
        try{
            //Creamos la conexion
            Connection conexion = DriverManager.getConnection(AccesoADatos1Ev.url, AccesoADatos1Ev.usuario, AccesoADatos1Ev.password);
            
            //Preparacion de la consulta
            Statement sentencia = conexion.createStatement();
            
            //Ejecutamos la consulta
            ResultSet resul = sentencia.executeQuery("SELECT * FROM `northwind`.`alumnos`;");

            System.out.println("");
            System.out.println("|------------------------------------------------------------------------|");
            System.out.println("| Nombre \tDni \t\tFecha \t\tCiclo \tNº de Asignaturas|");
            System.out.println("|------------------------------------------------------------------------|");
            // Mostramos los resultados 
            while(resul.next()) {
                System.out.println("| " + resul.getString(1) + " \t" + resul.getString(2) + " \t" + resul.getDate(3) + " \t" + resul.getString(4) + " \t" + resul.getInt(5)+" \t\t |");
            }
            System.out.println("|------------------------------------------------------------------------|");
            System.out.println("");
            
            //Cerramos todo
            resul.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Metodo que dado un alumno lo añadimos a la tabla
    public static void add(Alumno alumno) {
        try {
            //Creamos la conexion
            Connection conexion = DriverManager.getConnection(AccesoADatos1Ev.url, AccesoADatos1Ev.usuario, AccesoADatos1Ev.password);
            System.out.println("");
            
            //Escribimos la sentencia sql
            String sql = "INSERT INTO `northwind`.`alumnos` "
                + "(`nombre`, `dni`, `fecha_matricula`, `ciclo`, `asignaturas_matriculadas`) VALUES"
                + "(?,?,?,?,?)";

            //Creamos una consulta preparada
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            
            //Le añadimos sus respectivos valores al ? indicado
            preparedStatement.setString(1, alumno.getNombre());
            preparedStatement.setString(2, alumno.getDni());            
            preparedStatement.setString(3, alumno.getFecha_matricula());            
            preparedStatement.setString(4, alumno.getCiclo());            
            preparedStatement.setInt(5, alumno.getAsignaturas_matriculadas());

            //Ejecutamos la consulta preparada
            preparedStatement.executeUpdate();

            System.out.println("");
            System.out.println("Alumno insertado con exito!");
            System.out.println("");
            
            //Cerramos todo
            preparedStatement.close();
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: puede haber error de conexion o el DNI introducido ya existe en la base de datos");
            System.out.println("");
        }
    }
    
    public static void update() {
        try {
            //Creamos la conexion
            Connection conexion = DriverManager.getConnection(AccesoADatos1Ev.url, AccesoADatos1Ev.usuario, AccesoADatos1Ev.password);
            System.out.println("");
            
            //Escribimos la sentencia sql
            String sql = "UPDATE `northwind`.`alumnos` "
                + " SET `nombre`=(?), `fecha_matricula`=(?), `ciclo`=(?), `asignaturas_matriculadas`=(?) "
                + " WHERE `dni`=(?)";

            //Creamos una consulta preparada
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            //Le añadimos sus respectivos valores pedidos por pantalla al ? indicado
            System.out.println("Introduce el dni del alumno a modificar:");
            preparedStatement.setString(5, AccesoADatos1Ev.sc.nextLine());
            System.out.println("Introduce un nombre:");
            preparedStatement.setString(1, AccesoADatos1Ev.sc.nextLine());
            System.out.println("Introduce una fecha (YYYY-MM-DD):");
            preparedStatement.setString(2, AccesoADatos1Ev.sc.nextLine());
            System.out.println("Introduce un ciclo:");
            preparedStatement.setString(3, AccesoADatos1Ev.sc.nextLine());
            System.out.println("Introduce el numero de asignaturas matriculadas:");
            preparedStatement.setInt(4, Integer.parseInt(AccesoADatos1Ev.sc.nextLine()));

            //Ejecutamos la consulta preparada
            preparedStatement.executeUpdate();

            System.out.println("");
            System.out.println("Alumno modificado con exito!");
            System.out.println("");
            
            //Cerramos todo
            preparedStatement.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void delete() {
        try {
            //Creamos la conexion
            Connection conexion = DriverManager.getConnection(AccesoADatos1Ev.url, AccesoADatos1Ev.usuario, AccesoADatos1Ev.password);
            System.out.println("");
            
            //Escribimos la sentencia sql
            String sql = "DELETE FROM `northwind`.`alumnos` " +
                " WHERE `dni`=(?)";

            //Creamos una consulta preparada
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);

            //Le añadimos su valor pedido por pantalla al ? indicado
            System.out.println("Introduce el dni del alumno a eliminar:");
            preparedStatement.setString(1, AccesoADatos1Ev.sc.nextLine());

            //Ejecutamos la consulta preparada
            preparedStatement.executeUpdate();

            System.out.println("");
            System.out.println("Alumno eliminado con exito!");
            System.out.println("");
            
            //Cerramos todo
            preparedStatement.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void exportarXML() {
        try {
            //Creamos la conexion
            Connection conexion = DriverManager.getConnection(AccesoADatos1Ev.url, AccesoADatos1Ev.usuario, AccesoADatos1Ev.password);
            
            //Preparacion de la consulta
            Statement sentencia = conexion.createStatement();
            
            //Ejecutamos la consulta
            ResultSet resul = sentencia.executeQuery("SELECT * FROM `northwind`.`alumnos`;");
            
            //Creamos un objeto alumnos en cuya lista guardaremos los alumnos consultados
            Alumnos alumnos = new Alumnos();
            while(resul.next()) {
                //Le añadimos a la lista del objeto alumnos los alumnos consultados
               alumnos.getAlumnos().add(new Alumno(resul.getString(1),resul.getString(2),resul.getString(3),resul.getString(4), resul.getInt(5)));
            }
            //Cerramos todo
            resul.close();
            sentencia.close();
            conexion.close();
            
            System.out.println(alumnos);
            System.out.println("------------------------------------------");
            
            //Guardamos los alumnos en un xml
            JAXBContext contexto = JAXBContext.newInstance(alumnos.getClass());
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(alumnos, new File("Alumnos.xml"));
            
            System.out.println("\nXML Creado Correctamente en ./" + "Alumnos.xml");
            System.out.println("Previsualizacion: \n");
            
            //Los mostramos por pantalla
            marshaller.marshal(alumnos, System.out);
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void importarXML(String ruta) {
        try {
            //Importamos el xml
            System.out.println("Importando...");
            JAXBContext context = JAXBContext.newInstance( Alumnos.class );
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Alumnos alumnos = (Alumnos)unmarshaller.unmarshal(new File(ruta));
            
            //Añadimos a la base de datos los alumnos importados
            for (Alumno alumno : alumnos.getAlumnos()) {
                add(alumno);
            }
            System.out.println("XML Importado con exito! \n");

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
}

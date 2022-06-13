/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoadatos1ev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jborregb
 */
public class AccesoADatos1Ev {

    //Definimos la clase scanner
    public static Scanner sc = new Scanner(System.in);
    
    //Definimos la url usuario y password que necesitaremos para establecer la conexion
    public static String url = "jdbc:mysql://localhost/school"
        + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static String usuario = "root";
    public static String password = "root";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Ejecutamos crear tabla
        ConnectionMethods.createTable();    
        int i = 0;
        while(i!=7) {
            //Mostramos por pantalla el menú:
            System.out.println("Introduzca la operación que desea realizar: ");
            System.out.println("1 - Listar todos los alumnos.");
            System.out.println("2 - Introducir nuevo alumno.");
            System.out.println("3 - Modificar datos de un alumno.");
            System.out.println("4 - Eliminar alumno.");
            System.out.println("5 - Exportar alumnos a XML.");
            System.out.println("6 - Importar alumnos desde XML.");
            System.out.println("7 - Salir.");
            System.out.println("");
            
            //Guardamos el numero introducido
            i = Integer.parseInt(sc.nextLine());
            
            //Segun el numero elegido, ejecutaremos el metodo correspondiente al menu
            switch (i) {
                //Caso 1, metodo de mostrar la tabla
                case 1: ConnectionMethods.show();
                        break;
                //Caso 2, te va pidiendo el alumno a introducir y ejecuta el metodo de introducir ese alumno
                case 2: Alumno alumno = new Alumno();
                        System.out.println("Introduce un nombre:");
                        alumno.setNombre(sc.nextLine());
                        System.out.println("Introduce un dni:");
                        alumno.setDni(sc.nextLine());
                        System.out.println("Introduce una fecha (YYYY-MM-DD):");
                        alumno.setFecha_matricula(sc.nextLine());
                        System.out.println("Introduce un ciclo:");
                        alumno.setCiclo(sc.nextLine());
                        System.out.println("Introduce el numero de asignaturas matriculadas:");
                        alumno.setAsignaturas_matriculadas(Integer.parseInt(sc.nextLine()));
                            
                        ConnectionMethods.add(alumno);
                        break;
                //Caso 3, metodo de modificar            
                case 3: ConnectionMethods.update();
                        break;
                //Caso 4, metodo de eliminar            
                case 4: ConnectionMethods.delete();
                        break;
                //Caso 5, metodo de exportar el xml            
                case 5: ConnectionMethods.exportarXML();
                        break;
                //Caso 6, metodo de importal el xml    
                case 6: ConnectionMethods.importarXML("Alumnos.xml");
                        break;
                //Caso 7, finalizar el programa    
                case 7: System.out.println("");
                        System.out.println("Programa Finalizado.");
                        System.out.println("");
                        break;
                //Si no es un numero de los anteriores, pedir que introduzca otro numero             
                default: System.out.println("");
                         System.out.println("Error. El numero introducido no es valido. Introduce un número del menú.");
                         System.out.println("");
                         break;
            }
        }   
    }  
}

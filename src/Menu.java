package src;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Menu {
    public static void guardarEstudiantes(File file, List<Estudiante> lista) {
        try {
            FileOutputStream ficheroSalida = new FileOutputStream(file);
            ObjectOutputStream objetoSalida = new ObjectOutputStream(ficheroSalida);
            objetoSalida.writeObject(lista);
            objetoSalida.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Estudiante> obtenerEstudiantes(File file) {
        List<Estudiante> lista = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (List<Estudiante>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public static void main (String[] args){
        File file = new File("C:\\javaProjects\\crud-java\\BDEstudiantes.txt");
        int opc = 0;
        List<Estudiante> objetivo = obtenerEstudiantes(file);
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("Registro de estudiantes");
            System.out.println("========================");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Mostrar estudiantes");
            System.out.println("3. Buscar estudiante");
            System.out.println("4. Eliminar estudiante");
            System.out.println("5. Modificar estudiante");
            System.out.println("6. Salir");
            System.out.println("========================");
            System.out.println("\nIngrese una opcion:\n ");
            opc = sc.nextInt();

            switch (opc){
                case 1:
                    System.out.println("Agregar estudiante");
                    System.out.println("========================================");
                    sc.nextLine();
                    System.out.println("\nIngrese el nombre del estudiante:\n ");
                    String nombre = sc.nextLine();
                    System.out.println("\nIngrese el codigo del estudiante:\n ");
                    int codigo = sc.nextInt();
                    System.out.println("\nIngrese la edad del estudiante:\n ");
                    int edad = sc.nextInt();
                    System.out.println("\nIngrese la estatura del estudiante: ");
                    String estatura = sc.nextLine();
                    objetivo.add(new Estudiante(nombre, codigo, edad, estatura));
                    guardarEstudiantes(file, objetivo);
                    break;
                case 2:
                System.out.println("Mostrar estudiantes");
                System.out.println("========================================");
                System.out.println(FlipTableConverters.fromIterable(objetivo, Estudiante.class));
                break;
                case 3:
                System.out.println("Buscar estudiante");
                System.out.println("========================================");
                System.out.println("Ingrese el codigo del estudiante: ");
                int codigoBuscar = sc.nextInt();
                String mensaje = "No se encontró a la persona con el codigo " + codigoBuscar;
                Estudiante x = null;
                for (Estudiante o : objetivo) {
                    if (o.getCodigo() == (codigoBuscar)) {
                        mensaje = "Se encontró a la persona con el codigo " + codigoBuscar;
                        x = o;
                        break;
                    }
                }
                System.out.println("\n" + mensaje);
                String [] headers= {"Nombre", "Codigo", "Edad", "Estatura"};
                if (x != null) {
                    String [][] data = {
                        {x.getNombre(), Integer.toString(x.getCodigo()), Integer.toString(x.getEdad()), x.getEstatura()}
                    };
                    System.out.println(FlipTable.of(headers, data));
                }
                break;
                case 4:
                System.out.println("Eliminar estudiante");
                System.out.println("========================================");
                System.out.println("Ingrese el codigo del estudiante: ");
                int codigoEliminar = sc.nextInt();
                String mensaje2 = "No se encontró a la persona con el codigo " + codigoEliminar;
                for (int i = 0; i < objetivo.size(); i++) {
                    if (objetivo.get(i).getCodigo() == (codigoEliminar)) {
                        objetivo.remove(i);
                        mensaje2 = "Estudiante eliminado\n";
                    }
                }
                guardarEstudiantes(file, objetivo);
                System.out.println(mensaje2);
                break;
                case 5:
                System.out.println("Modificar estudiante");
                System.out.println("========================================");
                System.out.println("Ingrese el codigo del estudiante: ");
                int codigoModificar = sc.nextInt();
                String mensaje3 = "No se encontró a la persona con el codigo " + codigoModificar;
                Estudiante estudiante = null;
                for (Estudiante o : objetivo) {
                    if (o.getCodigo() == (codigoModificar)) {
                        estudiante = o;
                        mensaje3 = "Se encontró al estudiante";
                    }
                }
                System.out.println(mensaje3);
                int opc2=0;
                if (estudiante!= null){
                    do {
                        System.out.println("1-Modificar nombre");
                        System.out.println("2-Modificar codigo");
                        System.out.println("3-Modificar edad");
                        System.out.println("4-Modificar estatura");
                        System.out.println("5-Cancelar");
                        System.out.println("\nIngrese una opción:\n ");
                        opc2 = sc.nextInt();
                        switch (opc2){
                            case 1:
                            sc.nextLine();
                            System.out.println("Nombre actual: "+estudiante.getNombre());
                            estudiante.setNombre(sc.nextLine());
                            System.out.println("Nombre modificado: "+estudiante.getNombre());
                            break;
                            case 2:
                            System.out.println("Codigo actual: "+estudiante.getCodigo());
                            estudiante.setCodigo(sc.nextInt());
                            System.out.println("Codigo modificado: "+estudiante.getCodigo());
                            break;
                            case 3:
                            System.out.println("Edad actual: "+estudiante.getEdad());
                            estudiante.setEdad(sc.nextInt());
                            System.out.println("Edad modificada: "+estudiante.getEdad());
                            break;
                            case 4:
                            System.out.println("Estatura actual: "+estudiante.getEstatura());
                            estudiante.setEstatura(sc.nextLine());
                            System.out.println("Estatura modificada: "+estudiante.getEstatura());
                            break;
                            case 5:
                            System.out.println("Operación cancelada");
                            guardarEstudiantes(file, objetivo);
                            break;
                            default: 
                            System.out.println("Opción inválida");
                        }
                    } while (opc2 != 5);
                }
                break;
                case 6:
                System.out.println("Saliendo...");
                break;
                default:
                System.out.println("Opción inválida");
            }
        } while (opc != 6);
    }
}
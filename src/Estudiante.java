package src;
public class Estudiante implements java.io.Serializable {
    private String nombre;
    private int codigo;
    private int edad;
    private String estatura;

    public Estudiante (String nombre, int codigo, int edad, String estatura) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.edad = edad;
        this.estatura = estatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }
}



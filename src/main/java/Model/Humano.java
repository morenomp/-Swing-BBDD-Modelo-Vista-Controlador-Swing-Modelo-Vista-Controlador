package Model;

public class Humano extends Ser {

//-------------------------------------
//Variable de instancia (v.i)
//-------------------------------------
    private int edad; //entre 0 y 130
    private String genero; //puede ser masculino o femenino
    private static int civilizationLevel = 1; //el nivel de civilización de cada especie
    //(será única para cada una de ellas)
    //le pondremos "static", para que TODOS 
    //tengan estos atributos

//-------------------------------------
//Sobrecarga de constructores:
//-------------------------------------
    //Dar de Baja usará este
    public Humano(int edad, String genero, String name, Planeta p) {
        super(name, p);
        this.edad = edad;
        this.genero = genero;
    }
    
    //Crear Humano usará este
    public Humano(int edad, String genero, String name) {
        super(name);
        this.edad = edad;
        this.genero = genero;
    }

    public Humano(String name) {
        super(name);
    }

//-------------------------------------
//Variable de clase (v.c)
//-------------------------------------
//GETTER:
    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public static int getCivilizationLevel() {
        return civilizationLevel;
    }

//SETTER
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

//-------------------------------------
//OVERRIDE
//-------------------------------------
    /**
     * Devuelve datos sobrescribiendo el metodo del padre
     *
     * @return
     */
    @Override
    public String toString() {
        //con super.toSrting() llamamos al padre (Ser)
        return super.toString() + "Especie: Humano" + "\n"
                + "Edad: " + edad + "\n"
                + "Genero: " + genero;
    }
}

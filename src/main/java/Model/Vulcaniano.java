package Model;

public class Vulcaniano extends Ser {

//-------------------------------------
//Variable de instancia (v.i)
//-------------------------------------
    private int meditation; //rango entre 0 y 10
    private final static int civilizationLevel = 3; //el nivel de civilización de cada especie
    //(será única para cada una de ellas)
    //le pondremos "static", para que TODOS 
    //tengan estos atributos

//-------------------------------------
//Sobrecarga de constructores:
//-------------------------------------
    //Dar de Baja usará este
    public Vulcaniano(int meditation, String name, Planeta p) {
        super(name, p);
        this.meditation = meditation;
    }
    
    //Crear Vulcaniano usará este
    public Vulcaniano(int meditation, String name) {
        super(name);
        this.meditation = meditation;
    }

    public Vulcaniano(String name) {
        super(name);
    }

//-------------------------------------
//Variable de clase (v.c)
//-------------------------------------
//GETTER:
    public int getMeditation() {
        return meditation;
    }

//SETTER:   
    public void setMeditation(int meditation) {
        this.meditation = meditation;
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
        return super.toString() + "Especie: Vulcaniano " + "\n"
                + "Meditacion: " + meditation;
    }
}

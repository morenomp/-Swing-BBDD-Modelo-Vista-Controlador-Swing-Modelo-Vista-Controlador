package Model;

public class Ferengi extends Ser {

//-------------------------------------
//Variable de instancia (v.i)
//-------------------------------------
    private int gold; //cantidad de oro (nunca será negativo)
    private static int civilizationLevel = 1; //el nivel de civilización de cada especie
    //(será única para cada una de ellas)
    //le pondremos "static", para que TODOS 
    //tengan estos atributos
//-------------------------------------
//Sobrecarga de constructores:
//-------------------------------------
    //Dar de Baja usará este
    public Ferengi(int gold, String name, Planeta p) {
        super(name, p);
        this.gold = gold;
    }
    
    //Crear Ferengi usará este
    public Ferengi(int gold, String name) {
        super(name);
        this.gold = gold;
    }

    public Ferengi(String name) {
        super(name);
    }

//-------------------------------------
//Variable de clase (v.c)
//-------------------------------------
//GETTER:
    public int getGold() {
        return gold;
    }

    public static int getCivilizationLevel() {
        return civilizationLevel;
    }

//SETTER:
    public void setGold(int gold) {
        this.gold = gold;
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
        return super.toString() + "Especie: Ferengi" + "\n"
                + "Oro: " + gold;
    }
}

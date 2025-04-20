package Model;

import java.util.Objects;

public class Ser extends Object {

//-------------------------------------
//Variable de instancia (v.i)
//-------------------------------------
    final protected String name; //será lo que lo haga único
    protected Planeta planet;

//-------------------------------------
//Sobrecarga de constructores:
//-------------------------------------
    public Ser(String name) {
        this.name = name;
    }
    
    public Ser(String name, Planeta planet) {
        this.name = name;
        this.planet = planet;
    }

//-------------------------------------
//Variable de clase (v.c)
//-------------------------------------
//GETTER:
    public String getName() {
        return name;
    }

    public Planeta getPlanet() {
        return planet;
    }

//-------------------------------------
//OVERRIDE
//-------------------------------------
    /**
     * Devuelve datos del ser sobrescribiendo el metodo del padre
     *
     * @return
     */
    @Override
    public String toString() {
        return "Nombre: " + name + "\n";
    }

//-------------------------------------
//EQUALS
//-------------------------------------
    /**
     * Compara Planeta con otro objeto para determinar si son iguales
     *
     * @param obj obj será el objeto que comparará
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (obj instanceof Ser) {
            final Ser other = (Ser) obj;
            return this.getName().equals(other.getName());
        }

        return false;
    }
}

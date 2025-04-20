package Controller;

import DAO_Controller.DAOSQL;
import Model.*;
import Excepcion.*;

public class Controlador {

//HashSet Planeta
    //Tras eliminarlo comienza el verdadero reto
    //public static HashSet<Planeta> allplanet = new HashSet<>();
    
//--------------------------------
// Verificación del nombre Planeta
//--------------------------------
    /**
     * Funcion para conseguir objeto Planeta
     *
     * @param p Tiene un objeto entrada tipo Planeta o new Planet(nombre)
     * @return Devuelve el planeta ps del hashet que equivale a p
     * @throws Excepcion.DAO_Excep
     */
    public static Planeta getPlanet(Planeta p) throws DAO_Excep {

        //Crearemos una instancia del DAO que accede a la BD
        DAOSQL daoPlanet = new DAOSQL();

        // Llamamos a "readPlanet" (método que obtiene todos los planetas) y
        // validaremos si este contiene un planeta igual al que el usuario pasó
        // SI todo está correcto seguimos, y recorreremos readPlanet...
        for (Planeta ps : daoPlanet.readPlanet()) {

            // ...para encontrar y devolver el objeto planeta exacto
            if (ps.equals(p)) {

                //SI encuentra un planeta, lo devuelve
                return ps;
            }
        }

        //SI NO a encontrado nada, devolvemos null
        return null;
    }

    //-------------------------------
    // CREATE PLANETA
    //-------------------------------
    /**
     * Funcion para crear planetas
     *
     * @param p De entrada tiene un objeto tipo Planeta p
     * @throws PlanetaExcepcion Si hay un error lo lanza
     */
    public static void createplanet(Planeta p) throws PlanetaExcepcion {

        DAOSQL daoEst = new DAOSQL();

        int registeredPlanet;

        try {
            registeredPlanet = daoEst.insert(p);

        } catch (DAO_Excep ex) {

            throw new PlanetaExcepcion(p.getName() + " Error al registrar el planeta" + ex.getMessage());
        }
    }

    //-------------------------------
    // CREATE SER
    //-------------------------------
    /**
     * Funciona para añadir un Ser a un Planeta
     *
     * @param ser Objeto de entrada Ser s
     * @param planet Objeto de entrada Planeta p
     * @throws SerExcepcion
     * @throws Excepcion.DAO_Excep
     */
    public static void createser(Ser ser, Planeta planet) throws SerExcepcion, DAO_Excep {
      
        DAOSQL daoSer = new DAOSQL();

        //Verificaremos si son correctos los datos o no
        System.out.println("[createser] Verificando validez del planeta...");
        daoSer.getValidPlanet(ser, planet);
        
        //y los devolveremos para que los vaya llamando para almacenar
        if (ser instanceof Humano) {

            Humano h = (Humano) ser;
            daoSer.insertHumano(h, planet);
            System.out.println("[createSer] Humano insertado en el planeta " + planet.getName());
            
        } else if (ser instanceof Vulcaniano) {

            Vulcaniano v = (Vulcaniano) ser;
            daoSer.insertVulcaniano(v, planet);
            System.out.println("[createSer] Vulcaniano insertado en el planeta " + planet.getName());
            
        } else if (ser instanceof Nibiriano) {

            Nibiriano n = (Nibiriano) ser;
            daoSer.insertNibiriano(n, planet);
            System.out.println("[createSer] Nibiriano insertado en el planeta " + planet.getName());
            
        } else if (ser instanceof Klingon) {

            Klingon k = (Klingon) ser;
            daoSer.insertKlingon(k, planet);
            System.out.println("[createSer] Klingon insertado en el planeta " + planet.getName());
        
        } else if (ser instanceof Ferengi) {

            Ferengi f = (Ferengi) ser;
            daoSer.insertFerengi(f, planet);
            System.out.println("[createSer] Ferengi insertado en el planeta " + planet.getName());
        
        } else if (ser instanceof Andoriano) {

            Andoriano a = (Andoriano) ser;
            daoSer.insertAndoriano(a, planet);
            System.out.println("[createSer] Andoriano insertado en el planeta " + planet.getName());
        }
    }

    //-------------------------------
    // DELETE SER
    //-------------------------------
    public static void deleteSer(Ser s) throws SerExcepcion, DAO_Excep {

        DAOSQL daoDelete = new DAOSQL();

        if (s instanceof Andoriano a) {

            daoDelete.deleteAndoriano(a);

        } else if (s instanceof Ferengi f) {

            daoDelete.deleteFerengi(f);

        } else if (s instanceof Humano h) {

            daoDelete.deleteHumano(h);

        } else if (s instanceof Klingon k) {

            daoDelete.deleteKlingon(k);

        } else if (s instanceof Nibiriano n) {

            daoDelete.deleteNibiriano(n);

        } else if (s instanceof Vulcaniano v) {

            daoDelete.deleteVulcaniano(v);
        }
    }
}

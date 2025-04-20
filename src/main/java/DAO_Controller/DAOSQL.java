package DAO_Controller;

import Excepcion.*;
import Model.*;
import java.sql.*;
import java.util.*;

public class DAOSQL {

    //Variables para la conexión segura contra el servidor (sin especificar DDBB)
    //private final String JDBC_URL = "jdbc:mysql://localhost:3306";
    //¡IMPORTANTE! voy a especificar contra que bbdd debe hacer las consultas, 
    //ya que estoy teniendo problemas con el buscar.
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/startstucom";
    private final String JDBC_COMMU_OPT = "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private final String JDBC_USER = "root";
    private final String JDBC_PASSWORD = "";

    //Especificamos la base de Datos
    private final String JDBC_DDBB = "startstucom";

//////////////////////
//TABLAS
//////////////////////
    //Planetas
    private final String JDBC_TABLE = "planet";
    private final String JDBC_DDBB_TABLE = JDBC_DDBB + "." + JDBC_TABLE;

//Tenemos dos alternativas:
//      |
//      |- Crear tablas independientes para cada uno de los "seres"
//      |
//      |_ Crear una tabla de "ser" y hacer diferentes restricciones. Ya que
//         por ejemplo un humano tiene el campo edad, pero un vulcaniano no.
//
//Nosotros cogeremos la primera opción
    //Humano
    private final String JDBC_TABLE_HUM = "humano";
    private final String JDBC_DDBB_TABLE_HUM = JDBC_DDBB + "." + JDBC_TABLE_HUM;

    //Andoriano
    private final String JDBC_TABLE_AND = "andoriano";
    private final String JDBC_DDBB_TABLE_AND = JDBC_DDBB + "." + JDBC_TABLE_AND;

    //Ferengi
    private final String JDBC_TABLE_FER = "ferengi";
    private final String JDBC_DDBB_TABLE_FER = JDBC_DDBB + "." + JDBC_TABLE_FER;

    //Klingon
    private final String JDBC_TABLE_KLI = "klingon";
    private final String JDBC_DDBB_TABLE_KLI = JDBC_DDBB + "." + JDBC_TABLE_KLI;

    //Vulcaniano
    private final String JDBC_TABLE_VUL = "vulcaniano";
    private final String JDBC_DDBB_TABLE_VUL = JDBC_DDBB + "." + JDBC_TABLE_VUL;

    //Nibiriano
    private final String JDBC_TABLE_NIB = "nibiriano";
    private final String JDBC_DDBB_TABLE_NIB = JDBC_DDBB + "." + JDBC_TABLE_NIB;

//////////////////////
//SELECTS
//////////////////////
    //Variables para las consultas SQL
    //Planetas
    private final String SQL_SELECT_PLANETA = "SELECT * FROM " + JDBC_DDBB_TABLE + ";";

    //Humano
    private final String SQL_SELECT_HUM = "SELECT * FROM " + JDBC_DDBB_TABLE_HUM + ";";

    //Andoriano
    private final String SQL_SELECT_AND = "SELECT * FROM " + JDBC_DDBB_TABLE_AND + ";";

    //Ferengi
    private final String SQL_SELECT_FER = "SELECT * FROM " + JDBC_DDBB_TABLE_FER + ";";

    //Klingon
    private final String SQL_SELECT_KLI = "SELECT * FROM " + JDBC_DDBB_TABLE_KLI + ";";

    //Vulcaniano
    private final String SQL_SELECT_VUL = "SELECT * FROM " + JDBC_DDBB_TABLE_VUL + ";";

    //Nibiriano
    private final String SQL_SELECT_NIB = "SELECT * FROM " + JDBC_DDBB_TABLE_NIB + ";";

//////////////////////
//INSERTS
//////////////////////
    //Planetas
    private final String SQL_INSERT = "INSERT INTO " + JDBC_DDBB_TABLE + " (name, galaxy, populationMax, clime, flora, aquatic) VALUES (?, ?, ?, ?, ?, ?);";

    //Humano
    private final String SQL_INSERT_HUM = "INSERT INTO " + JDBC_DDBB_TABLE_HUM + " (name, edad, genero, planeta) VALUES (?, ?, ?, ?);";

    //Andoriano
    private final String SQL_INSERT_AND = "INSERT INTO " + JDBC_DDBB_TABLE_AND + " (name, rango, iceAtThePoles, planeta) VALUES (?, ?, ?, ?);";

    //Ferengi
    private final String SQL_INSERT_FER = "INSERT INTO " + JDBC_DDBB_TABLE_FER + " (name, gold, planeta) VALUES (?, ?, ?);";

    //Klingon
    private final String SQL_INSERT_KLI = "INSERT INTO " + JDBC_DDBB_TABLE_KLI + " (name, fuerza, planeta) VALUES (?, ?, ?);";

    //Vulcaniano
    private final String SQL_INSERT_VUL = "INSERT INTO " + JDBC_DDBB_TABLE_VUL + " (name, meditation, planeta) VALUES (?, ?, ?);";

    //Nibiriano
    private final String SQL_INSERT_NIB = "INSERT INTO " + JDBC_DDBB_TABLE_NIB + " (name, floraOrFish, planeta) VALUES (?, ?, ?);";

//////////////////////
//DELETE
//////////////////////
    private final String SQL_DELETE_PLA = "DELETE FROM " + JDBC_DDBB + "." + JDBC_TABLE + " WHERE (name = ?);";
    private final String SQL_DELETE_HUM = "DELETE FROM " + JDBC_DDBB + "." + JDBC_TABLE_HUM + " WHERE (name = ?);";
    private final String SQL_DELETE_AND = "DELETE FROM " + JDBC_DDBB + "." + JDBC_TABLE_AND + " WHERE (name = ?);";
    private final String SQL_DELETE_FER = "DELETE FROM " + JDBC_DDBB + "." + JDBC_TABLE_FER + " WHERE (name = ?);";
    private final String SQL_DELETE_KLI = "DELETE FROM " + JDBC_DDBB + "." + JDBC_TABLE_KLI + " WHERE (name = ?);";
    private final String SQL_DELETE_VUL = "DELETE FROM " + JDBC_DDBB + "." + JDBC_TABLE_VUL + " WHERE (name = ?);";
    private final String SQL_DELETE_NIB = "DELETE FROM " + JDBC_DDBB + "." + JDBC_TABLE_NIB + " WHERE (name = ?);";

//////////////////////
//SEARCH
//////////////////////
    //Tuve muchos problemas con esta parte, por eso el exceso de comentarios :)
    //Nos devolverá el nombre del planeta al que pertenece
    private final String SQL_SEARCH = "SELECT * FROM " + JDBC_DDBB_TABLE + " WHERE name = ?;";
    private final String SQL_SEARCH_AND = "SELECT * FROM " + JDBC_DDBB_TABLE_AND + " WHERE planeta = ?;";
    private final String SQL_SEARCH_VUL = "SELECT * FROM " + JDBC_DDBB_TABLE_VUL + " WHERE planeta = ?;";
    //Que hace? Buscar un ser concreto por nombre ->
    //Con esta consulta lo que haremos será unificar todas las especies, en una 
    //tabla temporal llamada "seres". Además, agregaremos "atributos específicos"
    //(atributo1, atributo2, extra1), ya que los humanos y andorianos tienen una
    //característica de más que el resto, y esto nos dará el siguiente problema:
    //
    //      Excepcion.DAO_Excep: Can not write to database (DAO_COntroller.DAOSQL
    //      .insert)java.sql.SQLException: The used SELECT statements have a 
    //      different number of columns
    //
    //de esta forma lo evitaremos.
    //
    //Para que usaremos CAST? Lo usaremos para convertir un tipo de dato de un 
    //valor a otro. Por ejemplo:
    //
    //      CAST(edad AS CHAR)
    //
    //Esto convertirá el valor de edad (numérico) a una cadena de texto (CHAR).
    //Principalmente lo haremos para que mezclar sin errores todos los datos.
    private final String SQL_SEARCH_ALL_SER = "SELECT name, tipo, atributo1, atributo2, extra1, planeta FROM ("
            + "SELECT name, 'humano' AS tipo, CAST(edad AS CHAR) AS atributo1, genero AS atributo2, '' AS extra1, planeta FROM " + JDBC_TABLE_HUM + " UNION ALL "
            + "SELECT name, 'andoriano', CAST(rango AS CHAR), '' AS atributo2, CAST(iceAtThePoles AS CHAR) AS extra1, planeta FROM " + JDBC_TABLE_AND + " UNION ALL "
            + "SELECT name, 'ferengi', CAST(gold AS CHAR), '' AS atributo2, '' AS extra1, planeta FROM " + JDBC_TABLE_FER + " UNION ALL "
            + "SELECT name, 'klingon', CAST(fuerza AS CHAR), '' AS atributo2, '' AS extra1, planeta FROM " + JDBC_TABLE_KLI + " UNION ALL "
            + "SELECT name, 'vulcaniano', CAST(meditation AS CHAR), '' AS atributo2, '' AS extra1, planeta FROM " + JDBC_TABLE_VUL + " UNION ALL "
            + "SELECT name, 'nibiriano', floraOrFish AS atributo1, '' AS atributo2, '' AS extra1, planeta FROM " + JDBC_TABLE_NIB
            + ") AS seres WHERE name = ?;";
    //Que hace? Ver todos los seres, con todos sus atributos ->
    //Uniremos todos los seres de distintas especies en una única tabla,
    //manteniendo así los nombres originales de las columnas (edad, genero, gold, etc.)
    //Si los atributos no pertenecen a una especie (por ejemplo los Humanos tienen
    //edad pero los andorianos no) se rellenarán con NULL
    private final String SQL_SEARCH_ALL_SERES
            = "SELECT name, edad, genero, NULL AS rango, NULL AS iceAtThePoles, NULL AS gold, NULL AS fuerza, NULL AS meditation, NULL AS floraOrFish, planeta FROM " + JDBC_DDBB_TABLE_HUM + " UNION ALL "
            + "SELECT name, NULL AS edad, NULL AS genero, rango, iceAtThePoles, NULL, NULL, NULL, NULL, planeta FROM " + JDBC_DDBB_TABLE_AND + " UNION ALL "
            + "SELECT name, NULL AS edad, NULL AS genero, NULL AS rango, NULL AS iceAtThePoles, gold, NULL, NULL, NULL, planeta FROM " + JDBC_DDBB_TABLE_FER + " UNION ALL "
            + "SELECT name, NULL AS edad, NULL AS genero, NULL AS rango, NULL AS iceAtThePoles, NULL AS gold, fuerza, NULL, NULL, planeta FROM " + JDBC_DDBB_TABLE_KLI + " UNION ALL "
            + "SELECT name, NULL AS edad, NULL AS genero, NULL AS rango, NULL AS iceAtThePoles, NULL AS gold, NULL AS fuerza, meditation, NULL, planeta FROM " + JDBC_DDBB_TABLE_VUL + " UNION ALL "
            + "SELECT name, NULL AS edad, NULL AS genero, NULL AS rango, NULL AS iceAtThePoles, NULL AS gold, NULL AS fuerza, NULL AS meditation, floraOrFish, planeta FROM " + JDBC_DDBB_TABLE_NIB;
    private final String SQL_COUNT_SERES
            = "SELECT COUNT(*) AS total FROM ("
            + "SELECT planeta FROM " + JDBC_DDBB_TABLE_HUM + " WHERE planeta = ? UNION ALL "
            + "SELECT planeta FROM " + JDBC_DDBB_TABLE_AND + " WHERE planeta = ? UNION ALL "
            + "SELECT planeta FROM " + JDBC_DDBB_TABLE_FER + " WHERE planeta = ? UNION ALL "
            + "SELECT planeta FROM " + JDBC_DDBB_TABLE_KLI + " WHERE planeta = ? UNION ALL "
            + "SELECT planeta FROM " + JDBC_DDBB_TABLE_VUL + " WHERE planeta = ? UNION ALL "
            + "SELECT planeta FROM " + JDBC_DDBB_TABLE_NIB + " WHERE planeta = ?"
            + ") AS results;";

//////////////////////
//UPDATE
//////////////////////
    //Seguiremos el ejemplo proporcionado por el profesorado:
    private final String SQL_UPDATE_HUM = "UPDATE " + JDBC_DDBB + "." + JDBC_TABLE_HUM + " SET gender = ?, SET age = ? WHERE (name = ?);";
    private final String SQL_UPDATE_AND = "UPDATE " + JDBC_DDBB + "." + JDBC_TABLE_AND + " SET rango = ?, SET ice = ? WHERE (name = ?);";
    private final String SQL_UPDATE_FER = "UPDATE " + JDBC_DDBB + "." + JDBC_TABLE_FER + " SET golf = ? WHERE (name = ?);";
    private final String SQL_UPDATE_KLI = "UPDATE " + JDBC_DDBB + "." + JDBC_TABLE_KLI + " SET fuerza = ? WHERE (name = ?);";
    private final String SQL_UPDATE_VUL = "UPDATE " + JDBC_DDBB + "." + JDBC_TABLE_VUL + " SET meditation = ? WHERE (name = ?);";
    private final String SQL_UPDATE_NIB = "UPDATE " + JDBC_DDBB + "." + JDBC_TABLE_NIB + " SET floraOrFish = ? WHERE (name = ?);";

    public DAOSQL() {
    }

//-------------------------
//CONEXIONES
//-------------------------
    //Con "Connection conn" nos referimos a la conexión que ya hemos establecido, y
    //necesitamos el metodo connect(), que se encargará de conectar la base de datos
    public Connection connect() throws DAO_Excep {
        Connection conn = null;
        try {

            //getConnection necesita la BBDD, el usuario y la contraseña
            conn = DriverManager.getConnection(JDBC_URL + JDBC_COMMU_OPT, JDBC_USER, JDBC_PASSWORD);

            //crearemos las tablas, desde el inicio
            createDB(conn);
            createTable(conn);
            createTableHumano(conn);
            createTableAndoriano(conn);
            createTableVulcaniano(conn);
            createTableFerengi(conn);
            createTableNibiriano(conn);
            createTableKlingon(conn);

        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(ex.getMessage());
            throw new DAO_Excep("Can not connect or create database with tables: " + JDBC_DDBB);
        }
        return conn;
    }

//-------------------------
//CREACIÓN DE LA BASE DE DATOS
//-------------------------
    private void createDB(Connection conn) throws SQLException {

        //Sentencia SQL que crea la BBDD si no existe en el servidor
        String instruction = "create database if not exists " + JDBC_DDBB + ";";
        Statement stmt = null;

        //La clase Statemen nos permite ejecutar sentencias SQL
        stmt = conn.createStatement();

        //Liberamos los recursos de la comunicación 
        stmt.executeUpdate(instruction);

        stmt.close();
    }

/////////////////////////// 
//-------------------------
//CREACIÓN DE LA TABLA PLANETA
//-------------------------
    private void createTable(Connection conn) throws SQLException {

        //Haremos que el nombre del planeta sea el identificador único (la primary key)
        String query = "create table if not exists " + JDBC_DDBB + "." + JDBC_TABLE + "("
                + "name varchar(50) primary key,"
                + "galaxy varchar(50),"
                + "populationMax int,"
                + "clime varchar(50),"
                + "flora boolean,"
                + "aquatic boolean);";

        System.out.println(query);

        Statement stmt = null;

        //La clase Statemen nos permite ejecutar sentencias SQL
        stmt = conn.createStatement();

        //Liberamos los recursos de la comunicación 
        stmt.executeUpdate(query);

        stmt.close();
    }

//-------------------------
//INSERTAR PLANETA
//-------------------------
    public int insert(Planeta planet) throws DAO_Excep {

        Connection conn = null;

        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_INSERT);
            instruction.setString(1, planet.getName());
            instruction.setString(2, planet.getGalaxy());
            instruction.setInt(3, planet.getPopulationMax());
            instruction.setString(4, planet.getClime());
            instruction.setBoolean(5, planet.isFlora());
            instruction.setBoolean(6, planet.isAquatic());
            //TODO meter resto campos
            registers = instruction.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)");

        } finally {

            try {

                //validaremos antes si se llegó a crear
                if (instruction != null) {

                    instruction.close();
                }
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)");
            }
        }
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

//-------------------------
//DELETE PLANETA
//-------------------------
    public int deletePlanet(Planeta planet) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;

        try {

            conn = connect();
            instruccion = conn.prepareStatement(SQL_DELETE_PLA);
            instruccion.setString(1, planet.getName());
            registers = instruccion.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)" + ex);

        } finally {

            try {

                instruccion.close();
                disconnect(conn);

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

/////////////////////////// 
//-------------------------
//CREACIÓN DE LA TABLA HUMANO
//-------------------------
    private void createTableHumano(Connection conn) throws SQLException {

        //Haremos que el nombre del planeta sea el identificador único (la primary key)
        //Y para poder insertarlo dentro de un planeta, usaremos las FOREIGN KEYs
        String query = "create table if not exists " + JDBC_DDBB + "." + JDBC_TABLE_HUM + "("
                + "name varchar(50) primary key,"
                + "edad int,"
                + "genero varchar(50),"
                + "planeta VARCHAR(50),"
                + "FOREIGN KEY (planeta) REFERENCES planet(name) ON DELETE CASCADE);";

        System.out.println(query);

        Statement stmt = null;

        //La clase Statemen nos permite ejecutar sentencias SQL
        stmt = conn.createStatement();

        //Liberamos los recursos de la comunicación 
        stmt.executeUpdate(query);

        stmt.close();
    }

//-------------------------
//INSERTAR HUMANO
//-------------------------
    public int insertHumano(Humano h, Planeta p) throws DAO_Excep {

        Connection conn = null;

        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_INSERT_HUM);
            instruction.setString(1, h.getName());
            instruction.setInt(2, h.getEdad());
            instruction.setString(3, h.getGenero());
            //nombre del planeta
            instruction.setString(4, p.getName());
            //TODO meter resto campos
            registers = instruction.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)" + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

//-------------------------
//DELETE HUMANO
//-------------------------
    public int deleteHumano(Humano h) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;

        try {

            conn = connect();
            instruccion = conn.prepareStatement(SQL_DELETE_HUM);
            instruccion.setString(1, h.getName());
            registers = instruccion.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)" + ex);

        } finally {

            try {

                instruccion.close();
                disconnect(conn);

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

//-------------------------
//UPDATE HUMANO
//-------------------------
    public int updateHumano(String name, int edad, String genero) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;

        try {

            conn = connect();
            instruccion = conn.prepareStatement(SQL_UPDATE_HUM);
            instruccion.setString(1, name);
            instruccion.setInt(2, edad);
            instruccion.setString(3, genero);
            registers = instruccion.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)" + ex);

        } finally {

            try {

                instruccion.close();
                disconnect(conn);

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

/////////////////////////// 
//-------------------------
//CREACIÓN DE LA TABLA ANDORIANO
//-------------------------
    private void createTableAndoriano(Connection conn) throws SQLException {

        //Haremos que el nombre del planeta sea el identificador único (la primary key)
        //Y para poder insertarlo dentro de un planeta, usaremos las FOREIGN KEYs
        String query = "create table if not exists " + JDBC_DDBB + "." + JDBC_TABLE_AND + "("
                + "name varchar(50) primary key,"
                + "rango varchar(50),"
                + "iceAtThePoles boolean,"
                + "planeta VARCHAR(50),"
                + "FOREIGN KEY (planeta) REFERENCES planet(name) ON DELETE CASCADE);";

        System.out.println(query);

        Statement stmt = null;

        //La clase Statemen nos permite ejecutar sentencias SQL
        stmt = conn.createStatement();

        //Liberamos los recursos de la comunicación 
        stmt.executeUpdate(query);

        stmt.close();
    }

//-------------------------
//INSERTAR ANDORIANO
//-------------------------
    public int insertAndoriano(Andoriano a, Planeta p) throws DAO_Excep {

        Connection conn = null;

        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_INSERT_AND);
            instruction.setString(1, a.getName());
            instruction.setString(2, a.getRango());
            instruction.setBoolean(3, a.isIceAtThePoles());
            //nombre del planeta
            instruction.setString(4, p.getName());
            //TODO meter resto campos
            registers = instruction.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)" + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

//-------------------------
//DELETE ANDORIANO
//-------------------------
    public int deleteAndoriano(Andoriano a) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;

        try {

            conn = connect();
            instruccion = conn.prepareStatement(SQL_DELETE_AND);
            instruccion.setString(1, a.getName());
            registers = instruccion.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)" + ex);

        } finally {

            try {

                instruccion.close();
                disconnect(conn);

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

//-------------------------
//UPDATE ANDORIANO
//-------------------------
    public int updateAndoriano(String name, boolean ice, String rango) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;

        try {

            conn = connect();
            instruccion = conn.prepareStatement(SQL_UPDATE_AND);
            instruccion.setString(1, name);
            instruccion.setBoolean(2, ice);
            instruccion.setString(3, rango);
            registers = instruccion.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)" + ex);

        } finally {

            try {

                instruccion.close();
                disconnect(conn);

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

/////////////////////////// 
//-------------------------
//CREACIÓN DE LA TABLA FERENGI
//-------------------------
    private void createTableFerengi(Connection conn) throws SQLException {

        //Haremos que el nombre del planeta sea el identificador único (la primary key)
        //Y para poder insertarlo dentro de un planeta, usaremos las FOREIGN KEYs
        String query = "create table if not exists " + JDBC_DDBB + "." + JDBC_TABLE_FER + "("
                + "name varchar(50) primary key,"
                + "gold int,"
                + "planeta VARCHAR(50),"
                + "FOREIGN KEY (planeta) REFERENCES planet(name) ON DELETE CASCADE);";

        System.out.println(query);

        Statement stmt = null;

        //La clase Statemen nos permite ejecutar sentencias SQL
        stmt = conn.createStatement();

        //Liberamos los recursos de la comunicación 
        stmt.executeUpdate(query);

        stmt.close();
    }

//-------------------------
//INSERTAR FERENGI
//-------------------------
    public int insertFerengi(Ferengi f, Planeta p) throws DAO_Excep {

        Connection conn = null;

        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_INSERT_FER);
            instruction.setString(1, f.getName());
            instruction.setInt(2, f.getGold());
            //nombre del planeta
            instruction.setString(3, p.getName());
            //TODO meter resto campos
            registers = instruction.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)" + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

//-------------------------
//DELETE FERENGI
//-------------------------
    public int deleteFerengi(Ferengi f) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;

        try {

            conn = connect();
            instruccion = conn.prepareStatement(SQL_DELETE_FER);
            instruccion.setString(1, f.getName());
            registers = instruccion.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)" + ex);

        } finally {

            try {

                instruccion.close();
                disconnect(conn);

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

//-------------------------
//UPDATE FERENGI
//-------------------------
    public int updateFerengi(String name, int gold) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;

        try {

            conn = connect();
            instruccion = conn.prepareStatement(SQL_UPDATE_FER);
            instruccion.setString(1, name);
            instruccion.setInt(2, gold);
            registers = instruccion.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)" + ex);

        } finally {

            try {

                instruccion.close();
                disconnect(conn);

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

/////////////////////////// 
//-------------------------
//CREACIÓN DE LA TABLA KLINGON
//-------------------------
    private void createTableKlingon(Connection conn) throws SQLException {

        //Haremos que el nombre del planeta sea el identificador único (la primary key)
        //Y para poder insertarlo dentro de un planeta, usaremos las FOREIGN KEYs
        String query = "create table if not exists " + JDBC_DDBB + "." + JDBC_TABLE_KLI + "("
                + "name varchar(50) primary key,"
                + "fuerza int,"
                + "planeta VARCHAR(50),"
                + "FOREIGN KEY (planeta) REFERENCES planet(name) ON DELETE CASCADE);";

        System.out.println(query);

        Statement stmt = null;

        //La clase Statemen nos permite ejecutar sentencias SQL
        stmt = conn.createStatement();

        //Liberamos los recursos de la comunicación 
        stmt.executeUpdate(query);

        stmt.close();
    }

//-------------------------
//INSERTAR KLINGON
//-------------------------
    public int insertKlingon(Klingon k, Planeta p) throws DAO_Excep {

        Connection conn = null;

        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_INSERT_KLI);
            instruction.setString(1, k.getName());
            instruction.setInt(2, k.getForce());
            //nombre del planeta
            instruction.setString(3, p.getName());
            //TODO meter resto campos
            registers = instruction.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)" + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

//-------------------------
//DELETE KLINGON
//-------------------------
    public int deleteKlingon(Klingon k) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;

        try {

            conn = connect();
            instruccion = conn.prepareStatement(SQL_DELETE_KLI);
            instruccion.setString(1, k.getName());
            registers = instruccion.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)" + ex);

        } finally {

            try {

                instruccion.close();
                disconnect(conn);

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

//-------------------------
//UPDATE KLINGON
//-------------------------
    public int updateKlingon(String name, int fuerza) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;

        try {

            conn = connect();
            instruccion = conn.prepareStatement(SQL_UPDATE_KLI);
            instruccion.setString(1, name);
            instruccion.setInt(2, fuerza);
            registers = instruccion.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)" + ex);

        } finally {

            try {

                instruccion.close();
                disconnect(conn);

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

/////////////////////////// 
//-------------------------
//CREACIÓN DE LA TABLA VULCANIANO
//-------------------------
    private void createTableVulcaniano(Connection conn) throws SQLException {

        //Haremos que el nombre del planeta sea el identificador único (la primary key)
        //Y para poder insertarlo dentro de un planeta, usaremos las FOREIGN KEYs
        String query = "create table if not exists " + JDBC_DDBB + "." + JDBC_TABLE_VUL + "("
                + "name varchar(50) primary key,"
                + "meditation int,"
                + "planeta VARCHAR(50),"
                + "FOREIGN KEY (planeta) REFERENCES planet(name) ON DELETE CASCADE);";

        System.out.println(query);

        Statement stmt = null;

        //La clase Statemen nos permite ejecutar sentencias SQL
        stmt = conn.createStatement();

        //Liberamos los recursos de la comunicación 
        stmt.executeUpdate(query);

        stmt.close();
    }

//-------------------------
//INSERTAR VULCANIANO
//-------------------------
    public int insertVulcaniano(Vulcaniano v, Planeta p) throws DAO_Excep {

        Connection conn = null;

        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_INSERT_VUL);
            instruction.setString(1, v.getName());
            instruction.setInt(2, v.getMeditation());
            //nombre del planeta
            instruction.setString(3, p.getName());
            //TODO meter resto campos
            registers = instruction.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)" + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

//-------------------------
//DELETE VULCANIANO
//-------------------------
    public int deleteVulcaniano(Vulcaniano v) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;

        try {

            conn = connect();
            instruccion = conn.prepareStatement(SQL_DELETE_VUL);
            instruccion.setString(1, v.getName());
            registers = instruccion.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)" + ex);

        } finally {

            try {

                instruccion.close();
                disconnect(conn);

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

//-------------------------
//UPDATE VULCANIANO
//-------------------------
    public int updateVulcaniano(String name, int meditation) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;

        try {

            conn = connect();
            instruccion = conn.prepareStatement(SQL_UPDATE_VUL);
            instruccion.setString(1, name);
            instruccion.setInt(2, meditation);
            registers = instruccion.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)" + ex);

        } finally {

            try {

                instruccion.close();
                disconnect(conn);

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

/////////////////////////// 
//-------------------------
//CREACIÓN DE LA TABLA NIBIRIANO
//-------------------------
    private void createTableNibiriano(Connection conn) throws SQLException {

        //Haremos que el nombre del planeta sea el identificador único (la primary key)
        //Y para poder insertarlo dentro de un planeta, usaremos las FOREIGN KEYs
        String query = "create table if not exists " + JDBC_DDBB + "." + JDBC_TABLE_NIB + "("
                + "name varchar(50) primary key,"
                + "floraOrFish varchar(50),"
                + "planeta VARCHAR(50),"
                + "FOREIGN KEY (planeta) REFERENCES planet(name) ON DELETE CASCADE);";

        System.out.println(query);

        Statement stmt = null;

        //La clase Statemen nos permite ejecutar sentencias SQL
        stmt = conn.createStatement();

        //Liberamos los recursos de la comunicación 
        stmt.executeUpdate(query);

        stmt.close();
    }

//-------------------------
//INSERTAR NIBIRIANO
//-------------------------
    public int insertNibiriano(Nibiriano n, Planeta p) throws DAO_Excep {

        Connection conn = null;

        //La clase PreparedStatement también permite ejecutar sentencias SQL
        //pero con mayor flexibilidad
        PreparedStatement instruction = null;
        int registers = 0;

        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_INSERT_NIB);
            instruction.setString(1, n.getName());
            instruction.setString(2, n.getFloraOrFish());
            //nombre del planeta
            instruction.setString(3, p.getName());
            //TODO meter resto campos
            registers = instruction.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)" + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return registers;
    }

//-------------------------
//DELETE NIBIRIANO
//-------------------------
    public int deleteNibiriano(Nibiriano n) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;

        try {

            conn = connect();
            instruccion = conn.prepareStatement(SQL_DELETE_NIB);
            instruccion.setString(1, n.getName());
            registers = instruccion.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)" + ex);

        } finally {

            try {

                instruccion.close();
                disconnect(conn);

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

//-------------------------
//UPDATE NIBIRIANO
//-------------------------
    public int updateNibiriano(String name, String floraOrFish) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruccion = null;
        int registers = 0;

        try {

            conn = connect();
            instruccion = conn.prepareStatement(SQL_UPDATE_NIB);
            instruccion.setString(1, name);
            instruccion.setString(2, floraOrFish);
            registers = instruccion.executeUpdate();

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_Controller.DAOSQL.delete)" + ex);

        } finally {

            try {

                instruccion.close();
                disconnect(conn);

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.delete)" + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados
        return registers;
    }

/////////////////////////// 
//-------------------------
//DESCONECTAR
//-------------------------
    public void disconnect(Connection conn) throws DAO_Excep {
        if (conn != null) {

            try {
                //Nos cerrará la conexión establecida con "close()"
                conn.close();

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not disconnect from database " + JDBC_DDBB);
            }
        }
    }

//-------------------------
//SEARCH PLANETA
//-------------------------
    public Planeta searchPlaneta(String name) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruction = null;
        ResultSet rs = null;
        Planeta p = null;

        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_SEARCH);
            instruction.setString(1, name);
            rs = instruction.executeQuery();

            if (rs.next()) {
                // Extraemos los datos de la base de datos
                String planetName = rs.getString("name");
                String galaxy = rs.getString("galaxy");
                int populationMax = rs.getInt("populationMax");
                String clima = rs.getString("clime");
                boolean flora = rs.getBoolean("flora");
                boolean aquatic = rs.getBoolean("aquatic");

                // Creamos el objeto Planeta con los datos obtenidos
                p = new Planeta(planetName, galaxy, populationMax, clima, flora, aquatic);
            }
        } catch (SQLException ex) {

            throw new DAO_Excep("Can not search planet in database: " + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database resources: " + ex);
            }
        }
        //Devolverá el planeta
        return p;
    }

//-------------------------
//SEARCH PLANETA EN LISTA
//-------------------------
    public Planeta buscarPlanetaEnLista(String name, List<Planeta> planetas) {

        for (Planeta p : planetas) {

            //Evitaremos errores por mayus o espacios
            if (p.getName().equalsIgnoreCase(name.trim())) {

                return p;
            }
        }
        return null;
    }

//-------------------------
//READ PLANETA
//-------------------------
    //haremos que lea los planetas, para poder pasarlos en el controlador más tarde
    public ArrayList<Planeta> readPlanet() throws DAO_Excep {

        ArrayList<Planeta> planeta = new ArrayList<>();
        Connection conn = null;
        PreparedStatement instruction = null;
        ResultSet rs = null;
        Planeta p = null;

        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_SELECT_PLANETA);
            rs = instruction.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String galaxy = rs.getString("galaxy");
                int populationMax = rs.getInt("populationMax");
                String clima = rs.getString("clime");
                boolean flora = rs.getBoolean("flora");
                boolean aquatic = rs.getBoolean("aquatic");

                p = new Planeta(name, galaxy, populationMax, clima, flora, aquatic);

                planeta.add(p);
            }

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert) " + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert) " + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return planeta;
    }

//-------------------------
//READ SER
//-------------------------
    //haremos que lea los seres, para poder pasarlos en el controlador más tarde
    public ArrayList<Ser> readSer() throws DAO_Excep {

        ArrayList<Ser> ser = new ArrayList<>();

        //Cargará los planetas ya, así no hace falta llamar a readPlanet todo el rato
        ArrayList<Planeta> planets = readPlanet();
        Planeta p = null;

        Connection conn = null;
        PreparedStatement instruction = null;
        ResultSet rs = null;

        //inicialización de variables
        Humano h = null;
        Andoriano a = null;
        Vulcaniano v = null;
        Ferengi f = null;
        Nibiriano n = null;
        Klingon k = null;

        //--------------
        //HUMANO
        //--------------
        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_SELECT_HUM);
            rs = instruction.executeQuery();

            while (rs.next()) {

                //obtendremos los datos del ser correspondiente
                String name = rs.getString("name");
                int edad = rs.getInt("edad");
                String genero = rs.getString("genero");

                String planet = rs.getString("planeta");

                //nos pasará el planeta correspondiente
                p = buscarPlanetaEnLista(planet, planets);

                if (p == null) {

                    System.out.println("[ERROR] [X] No se encontraron planetas llamados " + planet);

                } else {

                    h = new Humano(edad, genero, name, p);

                    ser.add(h);
                    System.out.println("[OK] Humano creado");
                }
            }

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not read to database (DAO_COntroller.DAOSQL.insert) " + ex);
        }
        //--------------
        //ANDORIANO
        //--------------
        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_SELECT_AND);
            rs = instruction.executeQuery();

            while (rs.next()) {

                //obtendremos los datos del ser correspondiente
                String name = rs.getString("name");
                String rango = rs.getString("rango");
                boolean iceAtThePoles = rs.getBoolean("iceAtThePoles");

                String planet = rs.getString("planeta");

                //nos pasará el planeta correspondiente
                p = buscarPlanetaEnLista(planet, planets);

                if (p == null) {

                    System.out.println("[ERROR] [X] No se encontraron planetas llamados " + planet);

                } else {

                    a = new Andoriano(rango, iceAtThePoles, name, p);

                    ser.add(a);
                    System.out.println("[OK] Andoriano creado");
                }
            }

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not read to database (DAO_COntroller.DAOSQL.insert) " + ex);
        }
        //--------------
        //VULCANIANO
        //--------------
        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_SELECT_VUL);
            rs = instruction.executeQuery();

            while (rs.next()) {

                //obtendremos los datos del ser correspondiente
                String name = rs.getString("name");
                int meditation = rs.getInt("meditation");

                String planet = rs.getString("planeta");

                //nos pasará el planeta correspondiente
                p = buscarPlanetaEnLista(planet, planets);

                if (p == null) {

                    System.out.println("[ERROR] [X] No se encontraron planetas llamados " + planet);

                } else {

                    v = new Vulcaniano(meditation, name, p);

                    ser.add(v);
                    System.out.println("[OK] Vulcaniano creado");
                }
            }

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not read to database (DAO_COntroller.DAOSQL.insert) " + ex);

        }
        //--------------
        //FERENGI
        //--------------
        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_SELECT_FER);
            rs = instruction.executeQuery();

            while (rs.next()) {

                //obtendremos los datos del ser correspondiente
                String name = rs.getString("name");
                int gold = rs.getInt("gold");

                String planet = rs.getString("planeta");

                //nos pasará el planeta correspondiente
                p = buscarPlanetaEnLista(planet, planets);

                if (p == null) {

                    System.out.println("[ERROR] [X] No se encontraron planetas llamados " + planet);

                } else {

                    f = new Ferengi(gold, name, p);

                    ser.add(f);
                    System.out.println("[OK] Ferengi creado");
                }
            }

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not read to database (DAO_COntroller.DAOSQL.insert) " + ex);

        }
        //--------------
        //NIBIRIANO
        //--------------
        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_SELECT_NIB);
            rs = instruction.executeQuery();

            while (rs.next()) {

                //obtendremos los datos del ser correspondiente
                String name = rs.getString("name");
                String floraOrFish = rs.getString("floraOrFish");

                String planet = rs.getString("planeta");

                //nos pasará el planeta correspondiente
                p = buscarPlanetaEnLista(planet, planets);

                if (p == null) {

                    System.out.println("[ERROR] [X] No se encontraron planetas llamados " + planet);

                } else {

                    n = new Nibiriano(floraOrFish, name, p);

                    ser.add(n);
                    System.out.println("[OK] Nibiriano creado");
                }
            }

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not read to database (DAO_COntroller.DAOSQL.insert) " + ex);

        }
        //--------------
        //KLINGON
        //--------------
        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_SELECT_KLI);
            rs = instruction.executeQuery();

            while (rs.next()) {

                //obtendremos los datos del ser correspondiente
                String name = rs.getString("name");
                int fuerza = rs.getInt("fuerza");

                String planet = rs.getString("planeta");

                //nos pasará el planeta correspondiente
                p = buscarPlanetaEnLista(planet, planets);

                if (p == null) {

                    System.out.println("[ERROR] [X] No se encontraron planetas llamados " + planet);

                } else {

                    k = new Klingon(fuerza, name, p);

                    ser.add(k);
                    System.out.println("[OK] Klingon creado");
                }
            }

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not read to database (DAO_COntroller.DAOSQL.insert) " + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert) " + ex);
            }
        }
        System.out.println("Total seres cargados: " + ser.size());
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return ser;
    }

//-------------------------
//GET POBLACIÓN
//-------------------------
    //veremos la cantidad de seres que hay en un planeta, es decir, su población máxima.
    //para ello entraremos a la tabla de planetas 
    public boolean getPoblacion(Planeta p) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruction = null;
        ResultSet rs = null;

        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_COUNT_SERES);
            instruction.setString(1, p.getName());
            instruction.setString(2, p.getName());
            instruction.setString(3, p.getName());
            instruction.setString(4, p.getName());
            instruction.setString(5, p.getName());
            instruction.setString(6, p.getName());
            rs = instruction.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");

                // Aquí decides si hay espacio. Supongamos capacidad máxima = 3
                if (total < p.getPopulationMax()) {
                    return true; //HAY espacio
                } else {
                    return false; //NO hay espacio
                }
            }
        } catch (SQLException ex) {

            throw new DAO_Excep("Can not read to database (DAO_COntroller.DAOSQL.insert) " + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert) " + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return false;
    }

//-------------------------
//GET CIUDADANO
//-------------------------
    //validaremos si existen ciudadanos o no (lo usaremos principalmente para "Buscar")
    public boolean getCiudadano() throws DAO_Excep {

        System.out.println("[OK] Entra getCiudadano");
        Connection conn = null;
        PreparedStatement instruction = null;
        ResultSet rs = null;
        System.out.println("[OK] Crea conexion getCiudadano");

        try {

            System.out.println("[OK] Try getCiudadano");
            conn = connect();

            //tengo problemas con esto, así que voy a asegurarme de que funcione antes
            if (conn != null) {
                System.out.println("[OK] Conexion exitosa");
            } else {
                System.out.println("[ERROR][X] No se pudo conectar a la base de datos");
            }

            instruction = conn.prepareStatement(SQL_SEARCH_ALL_SERES);
            System.out.println("[OK] prepareStatement getCiudadano");

            rs = instruction.executeQuery();
            System.out.println("[OK] executeQuery getCiudadano");

            if (rs.next()) {

                System.out.println("[OK] Hay ciudadanos");
                return true;

            } else {

                System.out.println("[ERROR][X] No Hay ciudadanos");
                return false;
            }
        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)" + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)" + ex);
            }
        }
    }

//-------------------------
//GET SER
//-------------------------
    //buscaremos un ser por su nombre     
    public Ser getSer(Ser s) throws DAO_Excep {
        Connection conn = null;
        PreparedStatement instruction = null;
        ResultSet rs = null;
        Ser ser = null;

        try {
            conn = connect();
            instruction = conn.prepareStatement(SQL_SEARCH_ALL_SER);
            instruction.setString(1, s.getName());
            rs = instruction.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String tipo = rs.getString("tipo");
                String atributo1 = rs.getString("atributo1");
                String atributo2 = rs.getString("atributo2");
                String extra1 = rs.getString("extra1");
                String planetaNombre = rs.getString("planeta");

                // Construimos el objeto Planeta
                Planeta planeta = new Planeta(planetaNombre);

                switch (tipo) {
                    case "humano":
                        int edad = Integer.parseInt(atributo1);
                        String genero = atributo2;
                        ser = new Humano(edad, genero, name, planeta);
                        break;
                    case "andoriano":
                        String rango = atributo1;
                        boolean ice = Boolean.parseBoolean(extra1);
                        ser = new Andoriano(rango, ice, name, planeta);
                        break;
                    case "ferengi":
                        int gold = Integer.parseInt(atributo1);
                        ser = new Ferengi(gold, name, planeta);
                        break;
                    case "klingon":
                        int fuerza = Integer.parseInt(atributo1);
                        ser = new Klingon(fuerza, name, planeta);
                        break;
                    case "vulcaniano":
                        int meditation = Integer.parseInt(atributo1);
                        ser = new Vulcaniano(meditation, name, planeta);
                        break;
                    case "nibiriano":
                        ser = new Nibiriano(name, atributo1, planeta);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new DAO_Excep("Error en getSer: " + ex.getMessage());
        } finally {
            try {
                if (instruction != null) {
                    instruction.close();
                }
                if (conn != null) {
                    disconnect(conn);
                }
            } catch (SQLException ex) {
                throw new DAO_Excep("Error cerrando conexión en getSer: " + ex.getMessage());
            }
        }

        return ser;
    }

//-------------------------
//GET PLANETA
//-------------------------
    //buscaremos un planeta por su nombre
    public Planeta getPlanet(Planeta p) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruction = null;
        ResultSet rs = null;
        Planeta planet = null;

        try {

            conn = connect();
            instruction = conn.prepareStatement(SQL_SEARCH);
            instruction.setString(1, p.getName());
            rs = instruction.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String galaxy = rs.getString("galaxy");
                int maxPopulation = rs.getInt("populationMax");
                String clime = rs.getString("clime");
                boolean flora = rs.getBoolean("flora");
                boolean aquatic = rs.getBoolean("aquatic");

                planet = new Planeta(name, galaxy, maxPopulation, clime, flora, aquatic);
            }

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)" + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)" + ex);
            }
        }
        //Devolvemos un planeta
        return planet;
    }

//-------------------------
//GET PLANETA SER
//-------------------------
    //Esta funcion es para conseguir el Planeta en donde vive el ciudadano
    public Planeta getPlanetaSer(Ser s) throws DAO_Excep {
        Connection conn = null;
        PreparedStatement instruction = null;
        ResultSet rs = null;

        try {
            conn = connect();
            instruction = conn.prepareStatement(SQL_SEARCH_ALL_SER);
            instruction.setString(1, s.getName());
            rs = instruction.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String planeta = rs.getString("planeta");

                Planeta p = new Planeta(planeta);
                Ser se = new Ser(name);
                if (s.equals(se)) {
                    instruction.close();
                    disconnect(conn);
                    return getPlanet(p);
                }
            }

        } catch (SQLException ex) {

            throw new DAO_Excep("Can not write to database (DAO_COntroller.DAOSQL.insert)" + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert)" + ex);
            }
        }
        return null;
    }

//-------------------------
//SEARCH SERES
//-------------------------
    //Nos buscará los seres que le digamos (en este caso Andorianos y Vulcanianos),
    //ya que lo usaremos para getValidPlanet
    public boolean searchSeres(Planeta p) throws DAO_Excep {

        Connection conn = null;
        PreparedStatement instruction = null;
        ResultSet rs = null;
        boolean result = false;

        try {

            conn = connect();

            // Buscaremos Andorianos...
            instruction = conn.prepareStatement(SQL_SEARCH_AND);
            instruction.setString(1, p.getName());
            rs = instruction.executeQuery();

            while (rs.next()) {
                result = true;
            }

            rs.close();
            instruction.close();

            //--------------------------------------
            //...SI NO, buscará Vulcanianos
            if (!result) {

                instruction = conn.prepareStatement(SQL_SEARCH_VUL); // asegúrate de tener esta constante
                instruction.setString(1, p.getName());
                rs = instruction.executeQuery();

                if (rs.next()) {
                    result = true;
                }
            }
        } catch (SQLException ex) {

            throw new DAO_Excep("Can not read to database (DAO_COntroller.DAOSQL.insert) " + ex);

        } finally {

            try {

                instruction.close();
                disconnect(conn);

            } catch (SQLException ex) {

                throw new DAO_Excep("Can not close database write process (DAO_COntroller.DAOSQL.insert) " + ex);
            }
        }
        //Devolvemos la cantidad de registros afectados, en nuestro caso siempre uno
        return result;
    }

//-------------------------------
// GET VALID
//-------------------------------
    //Funcion para conseguir un planeta valido
    public static void getValidPlanet(Ser s, Planeta p) throws SerExcepcion, DAO_Excep {

        DAOSQL daoValidP = new DAOSQL();
        System.out.println("[getValidPlanet] entra");

        if (daoValidP.getPoblacion(p)) { //return boolean de ser y planeta

        System.out.println("[getValidPlanet] 1");
        
//                Si s es Specie Andoriano
            if (s instanceof Vulcaniano) {
                
                System.out.println("[getValidPlanet] entra 1r if");
                
                if (daoValidP.searchSeres(p)) {
                    
                    System.out.println("[getValidPlanet] entra 2ndo if");
                    
                    //coincide en el mismo lugar que un vulcaniano
                    throw new SerExcepcion(" En el " + p.getName() + " existe un Andoriano");
                }
//        Si s es tipo Andoriano
            } else if (s instanceof Andoriano) {
                
                System.out.println("[getValidPlanet] entra 1r else if");
                
                if (daoValidP.searchSeres(p)) {
                    
                    System.out.println("[getValidPlanet] entra 2ndo if de else if");
                    
                    //coincide en el mismo lugar que un vulcaniano
                    throw new SerExcepcion(" En el " + p.getName() + " existe un Vulcaniano");
                }
//            Si es tipo klingon
            } else if (s instanceof Klingon) {
                
                System.out.println("[getValidPlanet] entra 2ndo else if");
                
//            Si el clima es de tipo Calido
                if (p.getClime().equalsIgnoreCase("Calido")) {
                    
                    System.out.println("[getValidPlanet] entra 2ndo if de else if 2");
                    
                    throw new SerExcepcion("No puede vivir en este planeta porque es de clima " + p.getClime() + ".");
                }
//            Si s es tipo Nibirianos
            } else if (s instanceof Nibiriano) {
                
                System.out.println("[getValidPlanet] entra 3er else if");
                
                Nibiriano n = (Nibiriano) s;
                // Si es vegetariano, necesita flora roja
                if (n.esVegetariano() && !p.isFlora()) {
                    
                    System.out.println("[getValidPlanet] entra 2ndo if de else if 3");
                    
                    throw new SerExcepcion("No puede vivir en este planeta porque es no tiene flora.");
                    // Si es carnivoro, necesita fauna marina
                } else if (n.esCarnivoro() && !p.isAquatic()) {
                    throw new SerExcepcion("No puede vivir en este planeta porque es no tiene fauna marina.");
                }
//            Si s es tipo Ferengi
            } else if (s instanceof Ferengi) {
                
                System.out.println("[getValidPlanet] entra 4to else if");
                
//            Si el clima es tipo Frio
                if (p.getClime().contains("Frio")) {
                    
                    System.out.println("[getValidPlanet] entra 2ndo if de else if 4");
                    
                    System.out.println(s.toString());
                    throw new SerExcepcion("No puede vivir en este planeta porque es de clima " + p.getClime() + ".");
                }
            } else if (s instanceof Humano) {
            }
        } else {
            throw new SerExcepcion("[!] El planeta " + p.getName() + "a llegado a su capacidad máxima .");
        }
        System.out.println("[getValidPlanet] salida");
    }
}

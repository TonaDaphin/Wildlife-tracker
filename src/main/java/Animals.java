import java.util.Objects;
import org.sql2o.*;
import java.util.List;

public class Animals {
    private  String name;
<<<<<<< HEAD
    private String health;
    private String age;
=======
>>>>>>> bac76b90c29c5d269aa7e3c5d52ed2db5ca26259
    private int id;

    //Constants

<<<<<<< HEAD
    public static final String  THREAT_TYPE ="Non-Endangered";
    //constructor

    public Animals(String name, String health, String age){
        this.name = name;
        this.health=health;
        this.age=age;
    }
=======
    public static final String  THREAT_TYPE ="Non-Endangereds";
    //constructor

    public Animals(String name){ this.name = name; }
>>>>>>> bac76b90c29c5d269aa7e3c5d52ed2db5ca26259

    //getters

    public String getName(){ return name; }
<<<<<<< HEAD
    public String getHealth(){ return health; }
    public String getAge(){ return age; }
=======
>>>>>>> bac76b90c29c5d269aa7e3c5d52ed2db5ca26259
    public int getId(){ return  id;}
    public static String getThreatType(){ return  THREAT_TYPE  ;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animals animal = (Animals) o;
        return id == animal.id &&
                Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
<<<<<<< HEAD
            String sql = "INSERT INTO animals (name,threattype,health,age) VALUES (:name,:threattype,:health,:age )";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("threattype", THREAT_TYPE)
                    .addParameter("health", this.health)
=======
            String sql = "INSERT INTO animals (name,threattype) VALUES (:name,:threattype)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("threattype", THREAT_TYPE)
>>>>>>> bac76b90c29c5d269aa7e3c5d52ed2db5ca26259
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Animals> all() {
<<<<<<< HEAD
        String sql = "SELECT * FROM animals";
=======
        String sql = "SELECT * FROM animals where threattype = 'Non-Endangereds'";
>>>>>>> bac76b90c29c5d269aa7e3c5d52ed2db5ca26259
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animals.class);
        }
    }

<<<<<<< HEAD

    public static Animals find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animals animals = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animals.class);
            return animals;
        }
    }


=======
    public static Animals find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animals animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animals.class);
            return animal;
        }
    }

>>>>>>> bac76b90c29c5d269aa7e3c5d52ed2db5ca26259
}

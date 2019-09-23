import java.util.Objects;
import org.sql2o.*;
import java.util.List;

public class Animals {
    private  String name;
    private int id;

    //Constants

    public static final String  THREAT_TYPE ="Non-Endangereds";
    //constructor

    public Animals(String name){ this.name = name; }

    //getters

    public String getName(){ return name; }
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
            String sql = "INSERT INTO animals (name,threattype) VALUES (:name,:threattype)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("threattype", THREAT_TYPE)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Animals> all() {
        String sql = "SELECT * FROM animals where threattype = 'Non-Endangereds'";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animals.class);
        }
    }

    public static Animals find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animals animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animals.class);
            return animal;
        }
    }

}

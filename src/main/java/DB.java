
import org.sql2o.*;

public class DB {
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "wecode", "123456");
static String connectionString = "jdbc:postgresql://ec2-54-83-33-14.compute-1.amazonaws.com:5432/dbj9fkhco5el38"; //!
    static Sql2o sql2o = new Sql2o(connectionString, "nqcwkwfyrxtjhj", "c2408e14e983aac6cbfa8c49ca71733d3d5d14e5972cac1e415ce65b3cd24814"); //!
}



import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class SightingsTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    public Sightings testSighting= new Sightings("Zone X", "Davis",1,"Elephant");

    @Test
    public void sighting_instantiatesCorrectly_true() {

        assertEquals(true, testSighting instanceof Sightings);
    }
    @Test
    public void Sighting_instantiatesWithRangerName_String() {

        assertEquals("Davis", testSighting.getRangerName());
    }


    //Save() test to database

    @Test
    public void save_assignsIdToObject() {
        testSighting.save();
        Sightings savedSighting = Sightings.all().get(0);
        assertEquals(testSighting.getId(), savedSighting.getId());
    }

    //    //return all database entries all()
    @Test
    public void all_returnsAllInstancesOfSightings_true() {
        testSighting.save();
        Sightings testSighting2 = new Sightings("Zone X", "Davis",2,"Lion");
        testSighting2.save();
        assertEquals(Sightings.all().get(0), testSighting);
        assertEquals(Sightings.all().get(1), testSighting2);
    }



    //    //Find() by id
    @Test
    public void find_returnsSightingsWithSameId_secondSighting() {

        testSighting.save();
        assertEquals(testSighting, Sightings.find(testSighting.getId()));
    }

    @Test
    public void notInstanceOfSighting_false()
    {
        Animals animal = new Animals("Lion");
        assertNotEquals(testSighting, animal);
    }




}
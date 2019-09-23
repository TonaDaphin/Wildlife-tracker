import org.junit.*;
import org.junit.Test;
import org.sql2o.*;
import static org.junit.Assert.*;

public class AnimalTest {

    public Animals testAnimal = new Animals("Elephant");

    @Test
    public void Animal_instantiatesCorrectly_true() {

        assertEquals(true, testAnimal instanceof Animals);
    }

    @Test
    public void getAnimalName_animalInstantiatesWithName_string() {
        assertEquals("Elephant", testAnimal.getName());
    }

    @Test
    public void getAnimalThreatType_animalInstantiatesThreatType_string() {
        assertEquals("Non-Endangereds", testAnimal.getThreatType());
    }

    @Test
    public void getAnimalId_animalInstantiatesWithId_int() {
        assertEquals(0, testAnimal.getId());
    }

    // Overriding equal()
    @Test
    public void equal_returnsTrueIfNameAndIdAreSame_true() {
        Animals firstAnimal = new Animals("Elephant");
        Animals anotherAnimal = new Animals("Elephant");
        assertTrue(firstAnimal.equals(anotherAnimal));
    }

    //Database setup
    @Test
    public void save_insertsObjectIntoDatabase_Animal() {
        testAnimal.save();
        assertTrue(Animals.all().get(0).equals(testAnimal));
    }

    //Returning all database entries
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animals firstAnimal = new Animals("Elephant");
        firstAnimal.save();
        Animals secondAnimal = new Animals("Fox");
        secondAnimal.save();
        assertEquals(true, Animals.all().get(0).equals(firstAnimal));
        assertEquals(true, Animals.all().get(1).equals(secondAnimal));
    }

}
}
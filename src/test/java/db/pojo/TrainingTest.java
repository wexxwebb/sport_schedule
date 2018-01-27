package db.pojo;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrainingTest {

    @Test
    public void toStringTest() {
        Training training = new Training(1, 25,  "2012-12-21", "2012-12-21");

        System.out.println(training);

    }
}
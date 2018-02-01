package db.pojo;

public interface ExerciseData {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    @Override
    String toString();
}

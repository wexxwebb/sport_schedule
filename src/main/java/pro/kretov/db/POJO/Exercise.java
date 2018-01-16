package pro.kretov.db.POJO;


public class Exercise {

    private int id;
    private int exerciseId;
    private ExerciseData exercise;
    private int trainingId;
    private Training training;
    private int approach;
    private int repetition;
    private int weigth;

    public Exercise(int id, int exerciseId, int trainingId, int approach, int repetition, int weigth) {
        this.id = id;
        this.exerciseId = exerciseId;
        this.trainingId = trainingId;
        this.approach = approach;
        this.repetition = repetition;
        this.weigth = weigth;
    }

    public Exercise(int exerciseId, int trainingId, int approach, int repetition, int weigth) {
        this.exerciseId = exerciseId;
        this.trainingId = trainingId;
        this.approach = approach;
        this.repetition = repetition;
        this.weigth = weigth;
    }


    public Exercise(int id, ExerciseData exercise, Training training, int approach, int repetition, int weigth) {
        this.id = id;
        this.exercise = exercise;
        this.training = training;
        this.approach = approach;
        this.repetition = repetition;
        this.weigth = weigth;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }


    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public ExerciseData getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseData exercise) {
        this.exercise = exercise;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getApproach() {
        return approach;
    }

    public void setApproach(int approach) {
        this.approach = approach;
    }


    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }


    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", exerciseId=" + exerciseId +
                ", trainingId=" + trainingId +
                ", approach=" + approach +
                ", repetition=" + repetition +
                ", weigth=" + weigth +
                '}';
    }
}

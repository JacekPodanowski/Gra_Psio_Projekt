package Observers;

public class PlayerStatusControler implements Observer{

    private double health;

    @Override
    public void update(double health) {
        this.health = health;
    }

    public void checkDifficulty(){

    }
}

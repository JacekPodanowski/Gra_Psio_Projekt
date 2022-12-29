package Observers;

public class PlayerStatusControler implements Observer{

    private double health;

    @Override
    public void update(double health) {
        this.health = health;
    }

    public void checkLifeStatus(){
        if(this.health < 20) {
            System.out.println("Jesteś mocno ranny, czy chcesz użyć jakiegoś przedmiotu?");
            
        }
    }
}

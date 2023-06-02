import java.util.Random;

public class Player implements Entity {
    private int stamina;
    private int skill;
    public Player()
    {
    }
    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int rollDice() {
        Random rand=new Random();
        return rand.nextInt((6 - 1) + 1) + 1;
    }

}

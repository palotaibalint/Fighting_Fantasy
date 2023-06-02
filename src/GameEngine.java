import java.util.Scanner;

public class GameEngine {
    private final Player player;
    private final Foe foe;
    private final Scanner playerInput;

    public GameEngine(){
        this.player=new Player();
        this.foe=new Foe();
        this.playerInput=new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to the Fighting Fantasy auto combat tool!");
        simulateCombat();
    }

    private void simulateCombat() {
        System.out.println("Enter your stamina:");
        this.player.setStamina(playerInput.nextInt());
        System.out.println("Enter your skill:");
        this.player.setSkill(playerInput.nextInt());

        final int initial_stamina=this.player.getStamina();

        System.out.println("Enter your opponent's stamina:");
        this.foe.setStamina(playerInput.nextInt());
        System.out.println("Enter your opponent's skill:");
        this.foe.setSkill(playerInput.nextInt());

        while(this.player.getStamina()>0 && this.foe.getStamina()>0) {
            simulateRound(player,foe);
            if(this.player.getStamina()>0 && this.foe.getStamina()>0)
            {
                System.out.println("Next turn...");
            }
        }
        showResult(initial_stamina);
    }

    private void simulateRound(Player player, Foe foe){
        System.out.println(
                        "You: H:"
                        +player.getStamina() +", S:"
                        +player.getSkill()+ "   vs.   Foe: H:"
                        +foe.getStamina()+", S:"
                        +foe.getSkill());
        clashEntities(player,foe);
    }

    private void clashEntities(Player player,Foe foe){
        int player_roll= player.rollDice()+player.rollDice();
        int player_strength=player.getSkill()+player_roll;
        System.out.println("You rolled "
                +player_roll+", your attack strength is S:"
                +player.getSkill()+" + "
                +player_roll+" (dice) = "+player_strength);

        int foe_roll= foe.rollDice()+foe.rollDice();
        int foe_strength=foe.getSkill()+foe.getSkill();
        System.out.println("Foe rolled "
                +foe_roll+", their attack strength is S:"
                +foe.getSkill()+" + "
                +foe_roll+" (dice) = "+foe_strength);

        int p_stamina=player.getStamina();
        int f_stamina=foe.getStamina();

        if(player_strength>foe_strength) {
            foe.setStamina(f_stamina-2);
            System.out.println("You hit the Foe,causing 2 damage: H:"+f_stamina+" -> H:"+foe.getStamina());
        }
        else if(foe_strength>player_strength){
            player.setStamina(p_stamina-2);
            System.out.println("Foe hit You,causing 2 damage: H:"+p_stamina+" -> H:"+player.getStamina());
        }
    }

    private void showResult(int initial_stamina){
        if(player.getStamina()>0)
        {
            System.out.println("You have defeated the Foe, your stats:\n"
                    +"Initial: H:"+initial_stamina+", S:"+this.player.getSkill()
                    +"\nEnd: H:"+player.getStamina()+", S:"+this.player.getSkill());
        }
        else
        {
            System.out.println("You have been defeated by the Foe, your stats:\n"
                    +"Initial: H:"+initial_stamina+", S:"+this.player.getSkill()
                    +"\nEnd: H:"+player.getStamina()+", S:"+this.player.getSkill());
        }
    }
}

package lib;
import java.util.Scanner;



/* SessionHandler takes in two pokemon objects and handles the turn based gameplay between them*/
public class SessionHandler {

    private Player p1;
    private Player p2;

    private Pokemon p1_pokemon;
    private Pokemon p2_pokemon;

    private Pokemon currentpokemon;
    private Pokemon enemypokemon;

    private Player currentplayer;
    private Player enemyplayer;


    /* Method to start running a game session between two players */
    public void RunSessionHandler() { 
        //"Globals"
        int ATTACK = 1;
        int SWAP = 2;

        /* creating two players */
        System.out.println("Please enter number of Pokemon to be used in battle:");

        Scanner scanin = new Scanner(System.in);
        String numofPokemonStr = scanin.nextLine();
        int numofPokemonInt = Integer.parseInt(numofPokemonStr);
        

        System.out.println("\nPlease enter the name of player 1:");
        String p1_name = scanin.nextLine();
        
        System.out.println("\nPlease enter the name of player 2:");
        String p2_name = scanin.nextLine();
        
         
        this.p1 = new Player(numofPokemonInt, p1_name);
        this.p2 = new Player(numofPokemonInt, p2_name);
        System.out.printf("\nPlayer 1 is %s, Player 2 is %s\n", p1.getPlayerName(), p2.getPlayerName());

        for (int i = 0; i < numofPokemonInt; i++){
            System.out.println("\nAvailable Pokemon: Mudkip Torchick");
            System.out.printf("\n%s please select Pokemon number %d:\n", p1.getPlayerName(), i+1);
            String pokemon_name = scanin.nextLine();
            int added = p1.addPokemonToTeam(pokemon_name, i);
            //handle invalid pokemon
            if (added == 0){
                i--;
                continue;
            }
            System.out.println("Pokemon added successfully!");
        }

        //adding pokemon to team
        for (int i = 0; i < numofPokemonInt; i++){
            System.out.println("\nAvailable Pokemon: Mudkip Torchick");
            System.out.printf("\n%s please select Pokemon number %d:\n", p2.getPlayerName(), i+1);
            String pokemon_name = scanin.nextLine();
            int added = p2.addPokemonToTeam(pokemon_name, i);
            //handle invalid pokemon
            if (added == 0){
                i--;
                continue;
            }
            System.out.println("Pokemon added successfully!");
        }

        //selecting pokemon for battle
        System.out.println("\nPlayer 1 select Pokemon for battle.");
        p1.PrintPokemonTeam();
        int index = scanin.nextInt();
        this.p1_pokemon = p1.getPokemonSingle(index-1); //adjust index because array starts at 0


        System.out.println("\nPlayer 2 select Pokemon for battle.");
        p2.PrintPokemonTeam();
        index = scanin.nextInt();
        this.p2_pokemon = p2.getPokemonSingle(index-1); //adjust index because array starts at 0

        System.out.printf("\n%s chose %s for battle.\n", p1.getPlayerName(), p1_pokemon.getName());
        System.out.printf("%s chose %s for battle.\n\n", p2.getPlayerName(), p2_pokemon.getName());

        this.currentplayer = p1;
        this.enemyplayer = p2;

        this.currentpokemon = this.p1_pokemon;
        this.enemypokemon = this.p2_pokemon;

        
        //While loop where main battle takes place
        while (true){
            int player_action = 0;
            //if current pokemon is dead ask player to swap it out
            if (currentpokemon.hp<=0){
                System.out.printf("Player %s please swap out current pokemon\n", currentplayer.getPlayerName());
                player_action = SWAP;
            }

            //else current player chooses action to use
            else{
                System.out.printf("Player %s choose action:\n1. Attack 2.Swap Pokemon\n", currentplayer.getPlayerName());
                player_action = scanin.nextInt();
            }
            

            /* Player chose ATTACK */
            if (player_action == ATTACK){
                //choose attack to use
                System.out.println("Please choose an attack to use.");
                currentpokemon.printAttackList();
                int selected_attack = scanin.nextInt();
                selected_attack--;  //adjust index because array starts from 0
                Attack attack_used = currentpokemon.returnAttackUsed(selected_attack);

                //register attack used and enemy pokemon in battle arena for damage calculation
                BattleArena ba = new BattleArena(attack_used, enemypokemon);
                //calculation of damage
                ba.calculateDamageDealt();

                //check if all pokemon dead
                if(this.checkAllDead(enemyplayer) == 1){
                    System.out.printf("\nCongrats %s!. You win!!!!\n", currentplayer.getPlayerName());
                    break;
                }

                else{
                    //continue the game
                    Player tmp_player;
                    Pokemon tmp_pokemon;

                    tmp_player = this.currentplayer;
                    tmp_pokemon = this.currentpokemon;

                    this.currentplayer = this.enemyplayer;
                    this.currentpokemon = this.enemypokemon;

                    this.enemyplayer = tmp_player;
                    this.enemypokemon = tmp_pokemon;
                }
            }

            /* Player chose SWAP */
            else if(player_action == SWAP){
                //swap pokemon being used
                while (true) {
                    System.out.println("Please choose an Pokemon to use.");
                    currentplayer.PrintPokemonTeam();
                    int swap_ind = scanin.nextInt();
                    Pokemon newpokemon = currentplayer.getPokemonSingle(swap_ind - 1);
                    if (newpokemon == currentpokemon && currentplayer.getPokemonTeam().length>1){
                        System.out.println("Current Pokemon is already in use. Please try again.");
                        continue;
                    }

                    else if (newpokemon == currentpokemon && currentplayer.getPokemonTeam().length<=1){
                        System.out.println("No other Pokemon to swap to\n");
                        break;
                    }
                    currentpokemon = newpokemon;
                    System.out.printf("%s: Your Pokemon has been swapped to %s\n", currentplayer.getPlayerName(), currentpokemon.getName());
                    break;
                }
                
                Player tmp_player;
                Pokemon tmp_pokemon;

                tmp_player = this.currentplayer;
                tmp_pokemon = this.currentpokemon;

                this.currentplayer = this.enemyplayer;
                this.currentpokemon = this.enemypokemon;

                this.enemyplayer = tmp_player;
                this.enemypokemon = tmp_pokemon;
            }
        }
        //this block should be when the game starts i.e. inside the while(1) loop
        // System.out.println("Please enter an attack to use.");
        // System.out.printf("\n%s:\n", this.name);

        scanin.close();
    }
    private int checkAllDead(Player player){
        //assume all dead first
        int alldead = 1;
        Pokemon[] player_team = player.getPokemonTeam();
        for (Pokemon poke : player_team){
            if (poke.hp >= 0){
                //if any pokemon is alive then change assumption
                alldead = 0;
                break;
            }
        }

        return alldead;
    }
}




class Player{
    private String playername;
    private Pokemon[] teampokemons;

    public Player(int numofpokemon, String playername){
        this.setPlayerName(playername);
        this.setNumOfPokemon(numofpokemon);
    }
    
    private void setPlayerName(String playername){
        this.playername = playername;
    }

    public String getPlayerName(){
        return this.playername;
    }

    private void setNumOfPokemon(int numofPokemon){
        teampokemons = new Pokemon[numofPokemon];
    }

    public int addPokemonToTeam(String pokemon_name, int indexof_pokemon){
        Pokemon p = this.createPokemonObj(pokemon_name);
        //handle unrecognized pokemon
        if (p == null){
            System.out.println("Error in adding Pokemon. Please select Pokemon again.");
            return 0;
        }

        this.teampokemons[indexof_pokemon] = p;
        return 1;
    }

    public Pokemon[] getPokemonTeam(){
        return this.teampokemons;
    }

    public Pokemon getPokemonSingle(int indexof_pokemon){
        return this.teampokemons[indexof_pokemon];
    }

    public void PrintPokemonTeam(){
        for (int i = 0; i < this.teampokemons.length; i++) {
            if (this.teampokemons[i].hp>0){
                System.out.printf("%d. %s \n", i+1, this.teampokemons[i].getName());
            }
            else{
                System.out.printf("%d. %s (DEAD)\n", i+1, this.teampokemons[i].getName());
            }
        }
    }

    private Pokemon createPokemonObj(String pokemon_name){
        switch (pokemon_name){
            case "Mudkip":
                return new Mudkip();

            case "Torchick":
                return new Torchick();

            default:
                return null;
        }
    }
}


/* BattleArena class that pits two Pokemon against each other*/
class BattleArena {
    private Pokemon pokemon_under_attack;
    private Attack attack_being_used;

    public BattleArena(Attack attack_being_used, Pokemon pokemon_under_attack){
        this.attack_being_used = attack_being_used;
        this.pokemon_under_attack = pokemon_under_attack;
    }

    public void calculateDamageDealt(){
        float damagemodifier = this.returnDamageModifier();
        float damage_given = damagemodifier * attack_being_used.power;
        int damage_given_int = (int) damage_given;
        pokemon_under_attack.takeDamage(damage_given_int);
        this.printDamageDealt(attack_being_used, damage_given_int, pokemon_under_attack, damagemodifier);
    }

    private void printDamageDealt(Attack attack_being_used2, int damage_given, Pokemon pokemon_under_attack, float damagemodifier){
        if (damagemodifier == 1.0){
            System.out.printf("\n%s did %d damage to %s\n", attack_being_used2.name, damage_given, pokemon_under_attack.getName());
        }

        else if(damagemodifier > 1.0){
            System.out.printf("\n%s did %d damage to %s. It was super effective!\n", attack_being_used2.name, damage_given, pokemon_under_attack.getName());
        }

        else if(damagemodifier < 1.0){
            System.out.printf("\n%s did %d damage to %s. It was not very effective!\n", attack_being_used2.name, damage_given, pokemon_under_attack.getName());
        }

        pokemon_under_attack.displayRemainingHP();
    }

    private float returnDamageModifier(){
        float retval = 1;
        String [] pokemon_under_attack_types = pokemon_under_attack.getPokemonTypes();

        //Check WATER attack type against pokemon types
        if (attack_being_used.type.equals("WATER")){
            for(int i = 0; i < 2; i++){
                if (pokemon_under_attack_types[i].equals("WATER")){
                    retval += 0;
                }

                else if (pokemon_under_attack_types[i].equals("FIRE")){
                    retval += 0.33;
                }

                else if (pokemon_under_attack_types[i].equals("GRASS")){
                    retval -= 0.33;
                }
            }
        }

        //Check FIRE attack type against pokemon types
        else if (attack_being_used.type.equals("FIRE")){
            for(int i = 0; i < 2; i++){
                if (pokemon_under_attack_types[i].equals("WATER")){
                    retval -= 0.33;
                }

                else if (pokemon_under_attack_types[i].equals("FIRE")){
                    retval += 0.0;
                }

                else if (pokemon_under_attack_types[i].equals("GRASS")){
                    retval += 0.33;
                }
            }
        }

        //Check GRASS attack type against pokemon types
        else if (attack_being_used.type.equals("GRASS")){
            for(int i = 0; i < 2; i++){
                if (pokemon_under_attack_types[i].equals("WATER")){
                    retval += 0.33;
                }

                else if (pokemon_under_attack_types[i].equals("FIRE")){
                    retval -= 0.33;
                }

                else if (pokemon_under_attack_types[i].equals("GRASS")){
                    retval += 0.0;
                }
            }
        }

        return retval;
    }

}
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
            System.out.println("\nAvailable Pokemon: Mudkip ");
            System.out.printf("\n%s please select Pokemon number %d:\n", p1.getPlayerName(), i+1);
            String pokemon_name = scanin.nextLine();
            int added = p1.addPokemonToTeam(pokemon_name, i);
            //handle invalid pokemon
            if (added == 0){
                i--;
                continue;
            }
        }

        //adding pokemon to team
        for (int i = 0; i < numofPokemonInt; i++){
            System.out.println("\nAvailable Pokemon: Mudkip ");
            System.out.printf("\n%s please select Pokemon number %d:\n", p2.getPlayerName(), i+1);
            String pokemon_name = scanin.nextLine();
            int added = p2.addPokemonToTeam(pokemon_name, i);
            //handle invalid pokemon
            if (added == 0){
                i--;
                continue;
            }
        }

        //selecting pokemon for battle
        System.out.println("\nPlayer 1 select Pokemon for battle.");
        p1.PrintPokemonTeam();
        int index = scanin.nextInt();
        this.p1_pokemon = p1.getPokemonSingle(index-1);


        System.out.println("\nPlayer 2 select Pokemon for battle.");
        p2.PrintPokemonTeam();
        index = scanin.nextInt();
        this.p2_pokemon = p2.getPokemonSingle(index-1);

        System.out.println(p1_pokemon.getName());
        System.out.println(p2_pokemon.getName());

        this.currentplayer = p1;
        this.enemyplayer = p2;

        this.currentpokemon = this.p1_pokemon;
        this.enemypokemon = this.p2_pokemon;

        
        //While loop where main battle takes place
        while (true){
            //current player chooses action to use
            System.out.println("Player 1 choose action:\n1. Attack 2.Swap Pokemon");
            int player_action = scanin.nextInt();
            if (player_action == ATTACK){
                //choose attack to use
                System.out.println("Please enter an attack to use.");
                currentpokemon.printAttackList();
                int selected_attack = scanin.nextInt();
                selected_attack--;  //adjust index because array starts from 0
                Attack attack_used = currentpokemon.returnAttackUsed(selected_attack);

                //register attack used and enemy pokemon in battle arena for damage calculation

            }

            else if(player_action == SWAP){
                //swap pokemon being used
            }
        }
        //this block should be when the game starts i.e. inside the while(1) loop
        // System.out.println("Please enter an attack to use.");
        // System.out.printf("\n%s:\n", this.name);

        scanin.close();
    }
    //TODO: check all dead function
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
            System.out.printf("%d. %s \n", i+1, this.teampokemons[i].getName());
        }
    }

    private Pokemon createPokemonObj(String pokemon_name){
        switch (pokemon_name){
            case "Mudkip":
                return new Mudkip();

            default:
                return null;
        }
    }
}


/* BattleArena class that pits two Pokemon against each other*/
class BattleArena {
    public Pokemon pokemon_under_attack;
    public Attack attack_being_used;

    public BattleArena(Attack attack_being_used, Pokemon pokemon_under_attack){
        this.attack_being_used = attack_being_used;
        this.pokemon_under_attack = pokemon_under_attack;
    }

    //TODO: Attack calculation function and returnDamageModifier helper function
    public void calculateDamageDealt(){
        float damagemodifier = this.returnDamageModifier();
        float damage_given = damagemodifier * attack_being_used.power;
        pokemon_under_attack.hp -= damage_given;
    }

    private float returnDamageModifier(){
        float retval = 1;
        String [] pokemon_under_attack_types = pokemon_under_attack.getPokemonTypes();

        //Check WATER attack type against pokemon types
        if (attack_being_used.type.equals("WATER")){
            for(int i = 0; i < 2; i++){
                switch(pokemon_under_attack_types[i]){
                    case "WATER":
                        retval += 0;

                    case "FIRE":
                        retval += 0.33;
                        
                    case "GRASS":
                        retval -= 0.33;
                    
                    default:
                        break;
                }
            }
        }

        //Check FIRE attack type against pokemon types
        if (attack_being_used.type.equals("FIRE")){
            for(int i = 0; i < 2; i++){
                switch(pokemon_under_attack_types[i]){
                    case "WATER":
                        retval -= 0.33;

                    case "FIRE":
                        retval += 0;
                        
                    case "GRASS":
                        retval += 0.33;

                    default:
                        break;
                }
            }
        }

        //Check GRASS attack type against pokemon types
        if (attack_being_used.type.equals("GRASS")){
            for(int i = 0; i < 2; i++){
                switch(pokemon_under_attack_types[i]){
                    case "WATER":
                        retval += 0.33;

                    case "FIRE":
                        retval -= 0.33;
                        
                    case "GRASS":
                        retval += 0;

                    default:
                        break;
                }
            }
        }

        return retval;
    }

}
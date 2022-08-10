package lib;
import java.util.Scanner;



/* SessionHandler takes in two pokemon objects and handles the turn based gameplay between them*/
public class SessionHandler {

    private Player p1;
    private Player p2;

    /* Constructor to create and initialise two players */
    public void RunSessionHandler() { 
        /* creating two players */
        System.out.println("Please enter number of Pokemon to be used in battle:");

        Scanner scanin = new Scanner(System.in);
        String numofPokemonStr = scanin.nextLine();
        int numofPokemonInt = Integer.parseInt(numofPokemonStr);
        

        System.out.println("Please enter the name of player 1:");
        String p1_name = scanin.nextLine();
        
        System.out.println("Please enter the name of player 2:");
        String p2_name = scanin.nextLine();
        
         
        this.p1 = new Player(numofPokemonInt, p1_name);
        this.p2 = new Player(numofPokemonInt, p2_name);
        System.out.printf("Player 1 is %s, Player 2 is %s\n", this.p1.getPlayerName(), this.p2.getPlayerName());

        for (int i = 0; i < numofPokemonInt; i++){
            System.out.println("Available Pokemon: Mudkip ");
            System.out.printf("%s please select Pokemon number %d:\n", this.p1.getPlayerName(), i+1);
            String pokemon_name = scanin.nextLine();
            int added = p1.addPokemonToTeam(pokemon_name, i);
            if (added == 0){
                i--;
                continue;
            }
        }

        for (int i = 0; i < numofPokemonInt; i++){
            System.out.println("Available Pokemon: Mudkip ");
            System.out.printf("%s please select Pokemon number %d:\n", this.p2.getPlayerName(), i+1);
            String pokemon_name = scanin.nextLine();
            int added = p2.addPokemonToTeam(pokemon_name, i);
            if (added == 0){
                i--;
                continue;
            }
        }

        scanin.close();
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
        if (p == null){
            System.out.println("Error in adding Pokemon. Please select Pokemon again.");
            return 0;
        }

        this.teampokemons[indexof_pokemon] = p;
        return 1;
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

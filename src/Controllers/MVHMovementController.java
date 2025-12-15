package Controllers;


import java.util.Scanner;

import Adapters.PartyPositionAdapter;
import Entities.Hero;
import Enums.Direction;
import Game.Battle;
import Game.GameUI;
import Utility.RollDie;
import WorldSets.Space;
import WorldSets.Maps.World;
import WorldSets.Spaces.MarketSpace;
import WorldSets.Spaces.ObstacleSpace;
import WorldSets.Spaces.PlainSpace;

public class MVHMovementController extends MovementController<World, PartyPositionAdapter> {

    public MVHMovementController(World world_map, PartyPositionAdapter movable, GameUI ui) {
        super(world_map, movable);
    }

    @Override
    public boolean move(Direction direction) {

        int current_row = target.getRow();
        int current_col = target.getCol();

        int target_row = current_row + direction.dRow();
        int target_col = current_col + direction.dCol();

        if(!mapSet.inBounds(target_row, target_col)) {
            onOutOfBounds(target_row,target_col);
            return false;
        }

        Space destination = mapSet.getSpace(target_row, target_col);
        if(destination instanceof ObstacleSpace) {
            onBlocked(mapSet.getSpace(target_row, target_col));
            return false;
        }

        target.setPosition(target_row, target_col);
        checkForBattle();
        return true;
    }

    @Override
    protected void onBlocked(Space space) {
        System.out.println("There appears to be a massive rock formation blocking your path...");
        System.out.println("You imagine there is some way around it.");
    }

    @Override
    protected void onOutOfBounds(int row, int col) {
        System.out.println("You stop in your tracks and look over at the hazy fog with no ground in sight...");
        System.out.println("You decide that it's best not to proceed.");
    }


    public boolean handleFastTravel() {
        if (mapSet.getSpace(mapSet.getParty_row(), mapSet.getParty_col()) instanceof MarketSpace) {
            System.out.println("Market fast travel locations\n Copy the desired coordinates to fast travel");
            for(Space market: mapSet.getMarkets()) {
                System.out.println("Coordinates: " + market.getRow() + "," + market.getCol());
            }
            Scanner scanner = new Scanner(System.in);
            int row = scanner.nextInt();
            int col = scanner.nextInt();
//            scanner.close();

            System.out.println("fast traveling to next location");
            mapSet.fastTravel(row, col);
            return true;
        }
        else {
            System.out.println("Not on a market square no fast travel available");
            return false;
        }
    }

    public PartyPositionAdapter getTarget() {
        return target;
    }


    @Override
    public void getHeroInfo() {
        GameUI ui = new GameUI();

        mapSet.getPlayerParty().getPartyInfo();

        System.out.print("Select a hero by number: ");
        int choice = ui.askInt() - 1;

        if (choice < 0 || choice >= mapSet.getPlayerParty().size()) {
            System.out.println("Invalid hero selection.");
            return;
        }

        Hero hero = mapSet.getPlayerParty().get(choice);

        HeroInfoController infoController = new HeroInfoController(ui);
        infoController.showHeroDetails(hero);
    }

    private void checkForBattle() {
        int row = mapSet.getParty_row();
        int col = mapSet.getParty_col();

        // Only battle on plain spaces
        if (!(mapSet.getSpace(row, col) instanceof PlainSpace)) {
            return;
        }

        // Random battle trigger
        if (!RollDie.rollDie(7)) {
            return;
        }

        // Initiate battle
        System.out.println("A wild group of monsters appears!");

        Battle battle = new Battle(mapSet.getPlayerParty());
        boolean survived = battle.battle();

        if (!survived) {
            System.out.println("Your party has fallen...");
            System.exit(0);  // End entire program cleanly
        }

    }
}

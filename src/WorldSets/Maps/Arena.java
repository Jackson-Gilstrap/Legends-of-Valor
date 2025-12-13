package WorldSets.Maps;

import Entities.Hero;
import Game.GameUI;
import Parties.MonsterParty;
import Parties.Party;
import WorldSets.MapSet;
import WorldSets.Space;
import WorldSets.Spaces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena extends MapSet {
    private final Space[][] arena;
    private final Party player_party;
    private final MonsterParty monster_party;
    private final GameUI ui;

    public Arena(int rows, int cols, Party player_party, MonsterParty monster_party) {
        super(rows, cols);
        this.arena = new Space[cols][rows];
        this.player_party = player_party;
        this.monster_party = monster_party;
        this.ui = new GameUI();
        build();
        spawnHeroes();
    }

    public testSeedHeroes

    private void spawnHeroes(){
        for (int i = 0; i < player_party.getPartySize(); i++) {
            Hero hero = player_party.getHeroFromParty(i);
            //ask what lane they want the hero in
            String lane = ui.askOneWord("What lane do you want to spawn " + hero.getName() + " in?\nTop | Mid | Bottom: ").toLowerCase();
            //get the nexi with that lane
            int lane_index = -1;
            while(lane_index == -1) {
                switch (lane) {
                    case "top": lane_index = 0; break;
                    case "mid": lane_index = 1; break;
                    case "bottom": lane_index = 2; break;
                    default: break;
                }
                if (lane_index == 0) {
                    Space nexus1 = getSpace(getRows() - 1, 0);
                    Space nexus2 = getSpace(getRows() - 1, 1);
                    //pick between the spaces
                    String space_choice = ui.askOneWord("Would you like the left or right spawn point");
                    switch (space_choice) {
                        case "left":
                            hero.setPosition(nexus1.getRow(), nexus1.getCol());
                            nexus1.setHero(hero);
                            break;
                        case "right":
                            hero.setPosition(nexus2.getRow(), nexus2.getCol());
                            nexus2.setHero(hero);
                        default: // left tile on default
                            hero.setPosition(nexus1.getRow(), nexus1.getCol());
                            nexus1.setHero(hero);
                    }
                }else if (lane_index == 1) {
                    Space nexus1 = getSpace(getRows() - 1, 3);
                    Space nexus2 = getSpace(getRows() - 1, 4);
                    //pick between the spaces
                    String space_choice = ui.askOneWord("Would you like the left or right spawn point");
                    switch (space_choice) {
                        case "left":
                            hero.setPosition(nexus1.getRow(), nexus1.getCol());
                            nexus1.setHero(hero);
                            break;
                        case "right":
                            hero.setPosition(nexus2.getRow(), nexus2.getCol());
                            nexus2.setHero(hero);
                        default: // left tile on default
                            hero.setPosition(nexus1.getRow(), nexus1.getCol());
                            nexus1.setHero(hero);
                    }
                } else if (lane_index == 2) {
                    Space nexus1 = getSpace(getRows() - 1, 6);
                    Space nexus2 = getSpace(getRows() - 1, getCols()-1);
                    //pick between the spaces
                    String space_choice = ui.askOneWord("Would you like the left or right spawn point");
                    switch (space_choice) {
                        case "left":
                            hero.setPosition(nexus1.getRow(), nexus1.getCol());
                            nexus1.setHero(hero);
                            break;
                        case "right":
                            hero.setPosition(nexus2.getRow(), nexus2.getCol());
                            nexus2.setHero(hero);
                        default: // left tile on default
                            hero.setPosition(nexus1.getRow(), nexus1.getCol());
                            nexus1.setHero(hero);

                    }
                }
            }
        }
    }




    @Override
    public Space getSpace(int row, int col) {
        return arena[col][row];
    }

    protected void build() {
        for (int r = 0; r < getRows() ; r++) {
            for (int c = 0; c < getCols(); c++) {

                //print the Monster Nexus
                if(c == 2 || c == 5) {
                    arena[c][r] = new WallSpace("Wall", r, c);
                    continue;
                }

                if(r == 0) {
                    arena[c][r] = new NexusSpace("Nexus", r, c, NexusSpace.NexusType.MONSTER);
                    continue;
                }

                if(r == getRows() - 1) {
                    arena[c][r] = new NexusSpace("Nexus",r,c, NexusSpace.NexusType.HERO);
                    continue;
                }


                arena[c][r] = generateRandomLaneSpace(r,c);
            }

        }

    }

    private Space generateRandomLaneSpace(int r,int c) {
        Random rand = new Random();
        int rand_num= rand.nextInt(10);
        if(rand_num % 2 == 0) {
            return new PlainSpace("Plain", r,c);
        }else {
            if (rand_num == 3) {
                return new KoulouSpace("Koulou",r,c);
            } else if (rand_num == 5) {
                return new CaveSpace("Cave",r,c);
            }else if  (rand_num == 7) {
                return new BushSpace("Bush",r,c);
            } else if  (rand_num == 9) {
                return new ObstacleSpace("Obstacle",r,c);
            }else {
                return new PlainSpace("Plain",r,c);
            }
        }

    }

    public String render() {
        StringBuilder sb = new StringBuilder();
        int spaceheight = 3;

        for (int r = 0; r < getRows(); r++) {

            List<StringBuilder> rowBuilders = new ArrayList<>();
            for (int i = 0; i < spaceheight; i++) {
                rowBuilders.add(new StringBuilder());
            }

            for(int c =0; c < getCols(); c++) {
                Space space = arena[c][r];
                List<String> lines = space.renderLines();

                for (int i = 0; i < spaceheight; i++) {
                    rowBuilders.get(i).append(lines.get(i)).append(" ");

                }

            }

            for (StringBuilder line : rowBuilders) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }


    public Space getSpaceAt(int row, int col) {return arena[col][row];}
}

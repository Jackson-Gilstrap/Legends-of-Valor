package Controllers;

import Enums.Direction;
import Interfaces.Positionable;
import WorldSets.MapSet;
import WorldSets.Space;
import WorldSets.Spaces.WallSpace;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;

public class LVMovementController extends MovementController {

    private final List<? extends Positionable> heroes;
    private final List<? extends Positionable> monsters;
    private final boolean isHero;
    private final int spawnRow;
    private final int spawnCol;

    public LVMovementController(MapSet mapSet,
                                Positionable target,
                                List<? extends Positionable> heroes,
                                List<? extends Positionable> monsters,
                                boolean isHero) {
        super(mapSet, target);
        this.heroes = heroes == null ? Collections.emptyList() : heroes;
        this.monsters = monsters == null ? Collections.emptyList() : monsters;
        this.isHero = isHero;
        this.spawnRow = target.getRow();
        this.spawnCol = target.getCol();
    }

    @Override
    public void move(Direction direction) {
        int targetRow = target.getRow() + direction.dRow();
        int targetCol = target.getCol() + direction.dCol();

        if (!mapSet.inBounds(targetRow, targetCol)) {
            onOutOfBounds(targetRow, targetCol);
            return;
        }

        Space destination = mapSet.getSpace(targetRow, targetCol);
        if (destination instanceof WallSpace || !destination.canEnter()) {
            onBlocked(destination);
            return;
        }

        if (isHero && isHeroOccupied(targetRow, targetCol)) {
            System.out.println("Another hero already occupies that space.");
            return;
        }

        if (isHero && isMonsterOccupied(targetRow, targetCol)) {
            System.out.println("A monster is blocking that space. Defeat it before advancing.");
            return;
        }

        if (isHero && isBlockedByMonsterAhead(targetRow, targetCol)) {
            System.out.println("You cannot advance past an enemy monster in this lane.");
            return;
        }

        if (!isHero && isMonsterOccupied(targetRow, targetCol)) {
            System.out.println("Another monster already occupies that space.");
            return;
        }

        if (!isHero && isHeroOccupied(targetRow, targetCol)) {
            System.out.println("The path is blocked by a hero.");
            return;
        }

        target.setPosition(targetRow, targetCol);
    }

    /**
     * Teleport adjacent to a target hero while observing Legends of Valor lane rules.
     *
     * @param targetHero hero to teleport next to
     * @return true if teleport succeeded
     */
    public boolean teleportToHero(Positionable targetHero) {
        if (!isHero) {
            System.out.println("Only heroes may teleport.");
            return false;
        }

        if (targetHero == null) {
            System.out.println("No target hero selected to teleport to.");
            return false;
        }

        if (laneOf(target.getCol()) == laneOf(targetHero.getCol())) {
            System.out.println("Teleport works only between different lanes.");
            return false;
        }

        List<int[]> candidates = Arrays.asList(
                new int[]{targetHero.getRow() - 1, targetHero.getCol()},
                new int[]{targetHero.getRow() + 1, targetHero.getCol()},
                new int[]{targetHero.getRow(), targetHero.getCol() - 1},
                new int[]{targetHero.getRow(), targetHero.getCol() + 1}
        );

        for (int[] candidate : candidates) {
            int row = candidate[0];
            int col = candidate[1];

            if (!mapSet.inBounds(row, col)) {
                continue;
            }

            // must change lanes
            if (laneOf(col) == laneOf(target.getCol())) {
                continue;
            }

            Space destination = mapSet.getSpace(row, col);
            if (destination instanceof WallSpace || !destination.canEnter()) {
                continue;
            }

            if (row < targetHero.getRow()) {
                // cannot teleport ahead of the target hero
                continue;
            }

            if (isHeroOccupied(row, col)) {
                continue;
            }

            if (isBlockedByMonsterAhead(row, col)) {
                continue;
            }

            target.setPosition(row, col);
            return true;
        }

        System.out.println("No valid teleport destination found near that hero.");
        return false;
    }

    /**
     * Recall the acting hero back to their original nexus space.
     *
     * @return true if the recall succeeds
     */
    public boolean recallToNexus() {
        if (!isHero) {
            System.out.println("Only heroes may recall to a nexus.");
            return false;
        }

        if (!mapSet.inBounds(spawnRow, spawnCol)) {
            System.out.println("Your nexus cannot be located on the map.");
            return false;
        }

        Space destination = mapSet.getSpace(spawnRow, spawnCol);
        if (destination instanceof WallSpace || !destination.canEnter()) {
            System.out.println("Your nexus space is blocked.");
            return false;
        }

        if (isHeroOccupied(spawnRow, spawnCol)) {
            System.out.println("Another hero is occupying your nexus.");
            return false;
        }

        if (isMonsterOccupied(spawnRow, spawnCol)) {
            System.out.println("A monster is occupying your nexus. Clear it before recalling.");
            return false;
        }

        target.setPosition(spawnRow, spawnCol);
        System.out.printf("You have been recalled to your nexus at (%d,%d).%n", spawnRow, spawnCol);
        return true;
    }

    private boolean isHeroOccupied(int row, int col) {
        for (Positionable hero : heroes) {
            if (hero == null || hero == target) {
                continue;
            }
            if (hero.getRow() == row && hero.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    private boolean isMonsterOccupied(int row, int col) {
        for (Positionable monster : monsters) {
            if (monster == null || monster == target) {
                continue;
            }
            if (monster.getRow() == row && monster.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    private boolean isBlockedByMonsterAhead(int targetRow, int targetCol) {
        int lane = laneOf(targetCol);
        for (Positionable monster : monsters) {
            if (monster == null) {
                continue;
            }
            if (laneOf(monster.getCol()) != lane) {
                continue;
            }
            // heroes advance upward, so a smaller row index is "ahead"
            if (targetRow < monster.getRow()) {
                return true;
            }
        }
        return false;
    }

    private int laneOf(int col) {
        // Arena uses walls at columns 2 and 5 to create three lanes.
        if (col < 2) {
            return 0;
        }
        if (col < 5) {
            return 1;
        }
        return 2;
    }

    public Positionable getTarget() {
        return target;
    }

    @Override
    protected void onOutOfBounds(int row, int col) {
        System.out.println("That move would leave the arena bounds.");
    }

    @Override
    protected void onBlocked(Space space) {
        if (space instanceof WallSpace) {
            System.out.println("A lane wall blocks that path.");
            return;
        }
        System.out.println("You cannot move onto that space.");
    }
}

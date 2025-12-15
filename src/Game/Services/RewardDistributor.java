package Game.Services;

import Entities.Hero;
import Entities.Monster;
import Parties.MonsterParty;
import Parties.Party;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Distributes gold/exp for newly defeated monsters.
 */
public class RewardDistributor {
    private final Set<Monster> rewardedMonsters = new HashSet<>();

    public void distribute(MonsterParty monsterParty, Party party) {
        ArrayList<Monster> deadMonsters = monsterParty.getDeadMonsters();
        ArrayList<Hero> aliveHeroes = party.getAliveHeroes();

        if (deadMonsters.isEmpty() || aliveHeroes.isEmpty()) {
            return;
        }

        for (Monster m : deadMonsters) {
            if (rewardedMonsters.contains(m)) {
                continue;
            }
            int goldDrop = m.getGoldDrop();
            int expDrop = m.getExperienceDrop();

            int goldPerHero = goldDrop / aliveHeroes.size();
            int expPerHero = expDrop / aliveHeroes.size();

            for (Hero h : aliveHeroes) {
                h.getWallet().addGold(goldPerHero);
                h.gainExperiencePoints(expPerHero);
            }

            rewardedMonsters.add(m);
        }
    }
}

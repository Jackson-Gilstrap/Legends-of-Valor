package Enums;

import Utility.Level;

public enum SpellType {
    FIRE {
        @Override
        public String effect() {
            return "The Fire Spell reduces the defense of a monster it is used on";
        }
        @Override
        public double debuffMultiplier(Level level) {
            return 0.10 + (level.getCurrentLevel()- 1) * 0.01;
        }

    },
    ICE {
        @Override
        public String effect() {
            return "The Ice Spell reduces the damage of a monster it is used on";
        }
        @Override
        public double debuffMultiplier(Level level) {
            return 0.10 + (level.getCurrentLevel()- 1) * 0.01;
        }
    },
    LIGHTNING {
        @Override
        public String effect() {
            return "The Lightning Spell reduces the dodge chance of a monster it is used on";
        }
        @Override
        public double debuffMultiplier(Level level) {
            return 0.10 + (level.getCurrentLevel()- 1) * 0.01;
        }
    };

    // 🔸 This MUST be inside the enum body, after the semicolon.
    public abstract String effect();
    public abstract double debuffMultiplier(Level level);
}
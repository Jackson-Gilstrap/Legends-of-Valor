package Builders;

import Enums.MarketType;
import Enums.SpellType;
import Items.*;
import Items.Armors.ChestPiece;
import Items.Armors.Helmet;
import Items.Armors.Leggings;
import Items.Potions.*;
import Items.Weapons.Melee;
import Items.Weapons.Ranged;
import Items.Weapons.Shield;
import Utility.Level;


public class ItemBuilder {
    private MarketType marketType;
    private SpellType spellType ;
    private String itemName, potionType;
    private int attack, dexterity, price, number_of_hands, level,mana_cost;
    private double agility, damage_reduction;

    public ItemBuilder setMarketType(MarketType marketType) {
        this.marketType = marketType;
        return this;
    }

    public ItemBuilder setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public ItemBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public ItemBuilder setNumber_of_hands(int number_of_hands) {
        this.number_of_hands = number_of_hands;
        return this;
    }

    public ItemBuilder setMana_cost(int mana_cost) {
        this.mana_cost = mana_cost;
        return this;
    }

    public ItemBuilder setPotionType(String potionType) {
        this.potionType = potionType;
        return this;
    }


    public ItemBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public ItemBuilder setAttack(int attack) {
        this.attack =attack;
        return this;
    }
    public ItemBuilder setDexterity(int dexterity) {
        this.dexterity = dexterity;
        return this;
    }
    public ItemBuilder setAgility(double agility) {
        this.agility = agility;
        return this;
    }
    public ItemBuilder setDamageReduction(double damage_reduction) {
        this.damage_reduction = damage_reduction;
        return this;
    }


    public Weapon buildMeleeWeapon() {return new Melee(itemName, price, attack, number_of_hands, new Level(level));}
    public Weapon buildRangedWeapon() {return new Ranged(itemName, price, attack, number_of_hands, new Level(level));}
    public Weapon buildShieldWeapon() {return new Shield(itemName, price, attack, number_of_hands, new Level(level));}

    public Armor buildHelmet() {return new Helmet(itemName, price, new Level(level), damage_reduction);}
    public Armor buildChestpiece() {return new ChestPiece(itemName, price, new Level(level), damage_reduction);}
    public Armor buildLeggings() {return new Leggings(itemName, price, new Level(level), damage_reduction);}

    public Spell buildSpell() {return new Spell(itemName,price, new Level(level), dexterity, mana_cost, spellType);}

    public Potion buildHealthPotion() {return new HealthPotion(itemName,price, new Level(level),potionType);}
    public Potion buildManaPotion() {return new ManaPotion(itemName,price, new Level(level),potionType);}
    public Potion buildAttackPotion() {return new StrengthPotion(itemName,price, new Level(level),potionType);}
    public Potion buildDexterityPotion() {return new DexterityPotion(itemName,price, new Level(level),potionType);}
    public Potion buildAgilityPotion() {return new AgilityPotion(itemName,price, new Level(level),potionType);}


    }


package Utility;

// Wallet.java
// Represents the wallet for a hero in LoV and MvH

public class Wallet {
    private int current_gold;

    public Wallet() {
        this.current_gold = 0;
    }

    public int getCurrent_gold() {return current_gold;}

    private boolean isEmpty() {return current_gold == 0;}

    public void addGold(int gold_amount) {
        this.current_gold += gold_amount;
    }

    public void makeTransaction(int amount) {
        // if empty reject the purchase
        if(isEmpty()){
            System.out.println("Wallet is empty no transaction can be made");
             return;
        }
        // if the amount is greater than current gold reject the purchase
        if(current_gold < amount){
            System.out.println("Not enough money.\nYou currently have " + current_gold + " gold, you are short " + (amount - current_gold) + " gold");
            return;
        }
        // accept the purchase and set gold to new amount
        this.current_gold -= amount;

    }

    public boolean canMakePurchase(int item_amount) {
        return current_gold >= item_amount;
    }

    @Override
    public String toString() {
        return "Gold: " + current_gold;
    }
}


package items;

import characters.Hero;

import java.util.Random;

public enum Item {
    MACE("sprites/weapons/mace.gif", 5, 0, ItemType.WEAPON),
    DAGGER("sprites/weapons/dagger.gif", 15, 0, ItemType.WEAPON),

    LEATHER_ARMOR("sprites/armore/leatherArmor.gif", 0, 10, ItemType.ARMORE),
    CHAIN_MAIL("sprites/armore/chainMail.gif", 0, 5, ItemType.ARMORE);

    public int getDamage() {
        return damage;
    }

    public int getArmore() {
        return armore;
    }

    public String getPath() {
        return path;
    }

    public ItemType type() {
        return type;
    }

    public static Item generateWeapon(int level) {
        Random rand = new Random();
        int ind = rand.nextInt(level);
        Item res = null;
        switch (ind) {
            case 0:
                res = MACE;
                break;
            default:
                res = DAGGER;
                break;
        }
        return res;
    }

    public static Item generateArmor(int level) {
        Random rand = new Random();
        int ind = rand.nextInt(level);
        Item res = null;
        switch (ind) {
            case 0:
                res = LEATHER_ARMOR;
                break;
            default:
                res = CHAIN_MAIL;
                break;
        }
        return res;
    }

    private int damage;
    private int armore;
    private String path;
    private ItemType type;

    Item(String path, int demag, int armore, ItemType type) {
        this.path = path;
        this.damage = demag;
        this.armore = armore;
        this.type = type;
    }

    public void action(Hero hero) {
        if (type == ItemType.WEAPON || type == ItemType.ARMORE) {
            Item item = hero.putOnItem(this);
            hero.getInventory().add(item);
        }
    }
}

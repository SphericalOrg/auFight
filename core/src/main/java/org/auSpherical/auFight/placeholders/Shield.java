package org.auSpherical.auFight.placeholders;

public class Shield implements Damageable {

    public int health = 100;

    private boolean isActive;


    public boolean isActive(){
        return isActive;
    }

    public void regenerar(int amm) {
        this.health += amm;
        if (health > 100){
            health = 100;
        }
    }


    @Override
    public int receiveDamage(float damage) {
        this.health -= 10;
        return health > 0 ? 0 : 1;
    }
}

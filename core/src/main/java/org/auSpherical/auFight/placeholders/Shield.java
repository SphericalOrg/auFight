package org.auSpherical.auFight.placeholders;

public class Shield {

    public float health = 100;
    private boolean isActive = false;

    public void regenerar() {
        if (health < 100 && !isActive) {
            health = Math.min(100, health + 10);
        }

    }


    public void activate() {
        isActive = true;
    }
    public void deactivate() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }


    public int receiveDamage(float damage) {
        this.health -= damage;
        return Math.round(health > 0 ? damage : 100);
    }
}

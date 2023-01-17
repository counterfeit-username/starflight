package starflight.game;

import com.badlogic.gdx.math.Vector2;

public class Star extends Sprite {
    private Vector2 starPosition;

    private Sprite ship;

    public Star(float x, float y, Sprite ship) {
        starPosition = new Vector2();

        this.ship = ship;
    }

    public void update() {
        int starSpread = 1000;
        position.x = (starPosition.x + ship.position.x) % starSpread;
        position.y = (starPosition.y + ship.position.y) % starSpread;
    }
}

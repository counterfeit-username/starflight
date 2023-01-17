package starflight.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Ship extends Sprite {
    private Vector2 velocity;

    private float rotationVelocity;

    public Ship(float x, float y) {
        velocity = new Vector2();
        rotationVelocity = 0;

        position.set(x, y);
    }

    public void update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            velocity.add(new Vector2(0, 100 * deltaTime).rotateDeg(direction));
        }

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            rotationVelocity += 100 * deltaTime;
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            rotationVelocity -= 100 * deltaTime;
        }

        position.mulAdd(velocity, deltaTime);
        direction += rotationVelocity * deltaTime;
    }
}

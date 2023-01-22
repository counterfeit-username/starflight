package starflight.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Thruster extends Sprite {
    private int[] keys;

    private float power;

    public Vector2 force;
    public float torque;

    private Vector2 thrustVector;

    public Vector2 rotatedPosition;

    public Thruster(Vector2 position, float direction, float power, Texture texture, int[] keys, Ship ship) {
        this.position = position;
        this.direction = direction;
        this.power = power;

        thrustVector = new Vector2();

        setTexture(texture);
        this.scale.set(0.1f, 0.1f);
        ;

        this.keys = keys;

        force = new Vector2();
    }

    public void update(float deltaTime) {
        super.update();

        for (int key : keys) {
            if (Gdx.input.isKeyPressed(key)) {
                force.set(0, power * deltaTime);
                force.rotateDeg(direction);
                torque = calculateTorque() * deltaTime;
                visible = true;
                this.scale.y = 0.1f + MathUtils.random(0.1f);
                return;
            }
        }
        force.setZero();
        torque = 0;

        visible = false;
    }

    public float calculateTorque() {
        // Get the angle of the torque bar
        float angle = MathUtils.radiansToDegrees * MathUtils.asin(position.y / position.len());

        float originalX = position.x;

        // Rotate to the nearest normal position (either 0 or 180 degrees)
        if (originalX < 0) {
            position.rotateDeg(angle);
            direction += angle;
        } else {
            position.rotateDeg(-angle);
            direction -= angle;
        }

        // Get the thrustVector
        thrustVector.set(0, power);
        thrustVector.rotateDeg(direction);

        float torque = position.x * thrustVector.len() * (thrustVector.y / thrustVector.len());

        if (originalX < 0) {
            position.rotateDeg(-angle);
            direction -= angle;
        } else {
            position.rotateDeg(angle);
            direction += angle;
        }

        position.x = originalX;

        return torque;
    }
}

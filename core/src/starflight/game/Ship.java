package starflight.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Ship extends Sprite {
        public Vector2 velocity;
        private float rotationVelocity;

        private float mass;
        private float massMomentOfInertia;

        private AssetManager assets;

        private Array<Thruster> thrusters;

        public Ship(float x, float y, AssetManager assets) {
                velocity = new Vector2();
                rotationVelocity = 0;

                position.set(x, y);

                mass = 50f;

                this.assets = assets;

                thrusters = new Array<Thruster>();

                // Main Engines
                thrusters.add(new Thruster(new Vector2(-4.4f, -37f), 0f, 500f,
                                assets.get("images/mainthrust.png", Texture.class), new int[] { Keys.SPACE, }, this));

                thrusters.add(new Thruster(new Vector2(4.4f, -37f), 0f, 500f,
                                assets.get("images/mainthrust.png", Texture.class), new int[] { Keys.SPACE, }, this));

                // Left side
                thrusters.add(new Thruster(new Vector2(-17f, 20f), -90f, 50f,
                                assets.get("images/rcsthrust.png", Texture.class), new int[] { Keys.E, Keys.D }, this));

                thrusters.add(new Thruster(new Vector2(-25f, -20f), -90f, 50f,
                                assets.get("images/rcsthrust.png", Texture.class), new int[] { Keys.E, Keys.A }, this));

                // Right side
                thrusters.add(new Thruster(new Vector2(17f, 20f), 90f, 50f,
                                assets.get("images/rcsthrust.png", Texture.class), new int[] { Keys.Q, Keys.A }, this));

                thrusters.add(new Thruster(new Vector2(25f, -20f), 90f, 50f,
                                assets.get("images/rcsthrust.png", Texture.class), new int[] { Keys.Q, Keys.D }, this));

                // Top
                thrusters.add(new Thruster(new Vector2(-5f, 39f), 180f, 50f,
                                assets.get("images/rcsthrust.png", Texture.class), new int[] { Keys.S, }, this));

                thrusters.add(new Thruster(new Vector2(5f, 39f), 180f, 50f,
                                assets.get("images/rcsthrust.png", Texture.class), new int[] { Keys.S, }, this));

                // Bottom
                thrusters.add(new Thruster(new Vector2(-13f, -33f), 0f, 50f,
                                assets.get("images/rcsthrust.png", Texture.class), new int[] { Keys.W, }, this));

                thrusters.add(new Thruster(new Vector2(13f, -33f), 0f, 50f,
                                assets.get("images/rcsthrust.png", Texture.class), new int[] { Keys.W, }, this));

                for (Thruster thruster : thrusters) {
                        thruster.parent = this;
                }
        }

        public void update(float deltaTime) {

                for (Thruster thruster : thrusters) {
                        thruster.update(deltaTime);
                        thruster.force.rotateDeg(direction);
                        velocity.x += thruster.force.x / mass;
                        velocity.y += thruster.force.y / mass;

                        rotationVelocity += thruster.torque / mass;
                        System.out.println();
                }

                position.mulAdd(velocity, deltaTime);
                direction += rotationVelocity * deltaTime;
        }

        public void draw(SpriteBatch batch) {
                for (Thruster thruster : thrusters) {
                        thruster.draw(batch);
                }
                super.draw(batch);
        }
}
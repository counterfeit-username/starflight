package starflight.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Sprite {
    private Texture texture;

    public boolean visible = true;

    public Vector2 position = new Vector2();
    public Vector2 origin = new Vector2();
    public float direction = 0;

    public Vector2 dimensions = new Vector2();
    public Vector2 size = new Vector2();

    public Vector2 scale = new Vector2(1, 1);

    public Sprite parent;

    public void draw(SpriteBatch batch) {
        if (visible) {
            dimensions.set(texture.getWidth(), texture.getHeight());

            if (parent != null) {
                position.rotateDeg(parent.direction);
                position.add(parent.position);

                direction += parent.direction;
            }

            batch.draw(texture, position.x - dimensions.x / 2 * scale.x, position.y - dimensions.y / 2 * scale.y,
                    origin.x + dimensions.x / 2 * scale.x, origin.y + dimensions.y / 2 * scale.y,
                    dimensions.x * scale.x, dimensions.y * scale.y,
                    1, 1,
                    direction,
                    0, 0,
                    (int) dimensions.x, (int) dimensions.y,
                    false, false);

            if (parent != null) {
                position.sub(parent.position);
                position.rotateDeg(-parent.direction);

                direction -= parent.direction;
            }
        }
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
        size.set(texture.getWidth() / scale.x, texture.getHeight() / scale.y);
    };
}

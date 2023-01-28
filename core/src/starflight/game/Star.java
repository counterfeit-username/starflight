package starflight.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Star extends Sprite {
    private AtlasRegion atlasRegion;

    private Vector2 starPosition;

    private Sprite ship;

    private int spread;

    public Star(AtlasRegion atlasRegion, Sprite ship, int spread) {
        setTexture(atlasRegion);

        starPosition = new Vector2();
        starPosition.set(MathUtils.random(-spread / 2, spread / 2),
                MathUtils.random(-spread / 2, spread / 2));

        this.ship = ship;

        this.spread = spread;

        float scaleFloat = MathUtils.random(0.1f, 0.5f);

        scale.set(scaleFloat, scaleFloat);
    }

    public void update() {
        position.x = starPosition.x;
        position.y = starPosition.y;

        while (position.x < ship.position.x - spread / 2) {
            position.x += spread;
        }

        while (position.x > ship.position.x + spread / 2) {
            position.x -= spread;
        }

        while (position.y < ship.position.y - spread / 2) {
            position.y += spread;
        }

        while (position.y > ship.position.y + spread / 2) {
            position.y -= spread;
        }
    }

    public void draw(SpriteBatch batch) {
        if (visible) {
            dimensions.set(atlasRegion.packedWidth, atlasRegion.packedWidth);

            batch.draw(atlasRegion, position.x - dimensions.x / 2 * scale.x, position.y - dimensions.y / 2 * scale.y,
                    origin.x + dimensions.x / 2 * scale.x, origin.y + dimensions.y / 2 * scale.y,
                    dimensions.x * scale.x, dimensions.y * scale.y,
                    1, 1,
                    direction);
        }
    }

    public void setTexture(AtlasRegion atlasRegion) {
        this.atlasRegion = atlasRegion;
        size.set(atlasRegion.packedWidth / scale.x, atlasRegion.packedHeight / scale.y);
    };
}

package starflight.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
//import com.badlogic.gdx.utils.ArrayMap;

public class Sprite {
    //public ArrayMap<String, Texture> textures = new ArrayMap<String, Texture>();
    private Texture texture;
    public int textureNumber = 0;

    public boolean visible = true;

    public Vector2 position = new Vector2();
    public Vector2 origin = new Vector2();
    public float direction = 0;

    private Vector2 truePosition = new Vector2();
    private Vector2 trueOrigin = new Vector2();
    private float trueDirection = 0;

    public float height = 0;
    public float width = 0;

    public float scale = 1;

    public Sprite parent;

    public Array<Sprite> children = new Array<Sprite>();


    public void draw(SpriteBatch batch) {
        if (visible) {
        //texture = textures.getValueAt(textureNumber);
        width = texture.getWidth();
        height = texture.getHeight();

        updateTruePosition();
        updateTrueOrigin();
        updateTrueDirection();

        batch.draw(texture, truePosition.x, truePosition.y, 
                    trueOrigin.x + width / 2 * scale, trueOrigin.y + height / 2 * scale, 
                    width * scale, height * scale, 
                    1, 1, 
                    trueDirection, 
                    0, 0, 
                    (int) width, (int) height, 
                    false, false);
        }
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    };

    public void updateTruePosition() {
        if (parent != null) {
            parent.updateTruePosition();
            truePosition.x = position.x + parent.truePosition.x;
            truePosition.y = position.y + parent.truePosition.y;
        } else {
            truePosition.x = position.x;
            truePosition.y = position.y;
        }
    }

    public void updateTrueOrigin() {
        if (parent != null) {
            parent.updateTrueOrigin();
            trueOrigin.x = origin.x + parent.trueOrigin.x  + width / 2;
            trueOrigin.y = origin.y + parent.trueOrigin.y;
        } else {
            trueOrigin.x = origin.x;
            trueOrigin.y = origin.y;
        }
    }

    public void updateTrueDirection() {
        if (parent != null) {
            parent.updateTrueDirection();
            trueDirection = direction + parent.trueDirection;
        } else {
            trueDirection = direction;
        }
    }
}

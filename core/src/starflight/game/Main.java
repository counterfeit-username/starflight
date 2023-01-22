package starflight.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

//import com.bitfire.postprocessing.PostProcessor;
//import com.bitfire.postprocessing.PostProcessorEffect;
//import com.bitfire.postprocessing.effects.Fxaa;
//import com.bitfire.postprocessing.effects.Nfaa;
//import com.bitfire.utils.ShaderLoader;

public class Main extends ApplicationAdapter {
	private int screenW, screenH;
	private ExtendViewport viewport;
	private OrthographicCamera camera;

	// private PostProcessor post;

	private TextureParameter textureParameters;
	private AssetManager assets;
	private SpriteBatch batch;

	private Ship ship;

	@Override
	public void create() {
		// ShaderLoader.BasePath = "shaders/";

		screenW = 800;
		screenH = 500;

		camera = new OrthographicCamera();
		camera.setToOrtho(false);

		viewport = new ExtendViewport(screenW, screenH);
		viewport.setCamera(camera);

		// post = new PostProcessor(false, false, Gdx.app.getType() ==
		// ApplicationType.Desktop);

		// Nfaa nfaa = new Nfaa(screenW, screenH);

		// post.addEffect(nfaa);

		textureParameters = new TextureParameter();
		textureParameters.genMipMaps = true;
		textureParameters.minFilter = TextureFilter.MipMap;
		textureParameters.magFilter = TextureFilter.MipMap;

		assets = new AssetManager();
		batch = new SpriteBatch();

		assets.load("images/ship.png", Texture.class, textureParameters);
		assets.load("images/mainthrust.png", Texture.class, textureParameters);
		assets.load("images/rcsthrust.png", Texture.class, textureParameters);
		assets.finishLoading();

		ship = new Ship(0, 0, assets);
		ship.direction = 0;
		ship.setTexture(assets.get("images/ship.png", Texture.class));
		ship.scale.set(0.1f, 0.1f);
	}

	@Override
	public void render() {
		float deltaTime = Gdx.graphics.getDeltaTime();

		update(deltaTime);
		draw(deltaTime);
	}

	private void update(float deltaTime) {

		ship.update(deltaTime);

	}

	private void draw(float deltaTime) {
		camera.position.x = 0;
		camera.position.y = 0;
		camera.update();

		ScreenUtils.clear(0, 0, 0, 1);

		batch.setProjectionMatrix(camera.combined);
		// post.capture();
		batch.begin();

		ship.draw(batch);

		batch.end();
		// post.render();
	}

	@Override
	public void resize(int width, int height) {
		screenW = width;
		screenH = height;
		viewport.update(width, height);
	}

	@Override
	public void dispose() {
		// post.dispose();
		assets.dispose();
		batch.dispose();
	}
}

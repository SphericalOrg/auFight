import com.badlogic.gdx.Screen;
import com.mygdx.game.AuConstans;

public class AuWorld implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Body groundBody;
    private Sprite groundSprite;
    
    public ChessWorld()  {
        camera = new OrthographicCamera();
        camera.setToOrtho(
                false,
                Gdx.graphics.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f);
        batch = new SpriteBatch();
        world = new World(ChessConstants.GRAVITY, true);
        debugRenderer = new Box2DDebugRenderer();

        float worldWidth = Gdx.graphics.getWidth() * 50f / AuConstans.PPM;
        player = new PawnPlayer(world,
                2,
                48,
                -worldWidth / 2f,
                worldWidth / 2f, camera);
        createGround(worldWidth);
        enemies = new Array<>();
        killedPawns = 0;
        spawnInitialEnemies();
    }

    private void spawnNewEnemy() throws NegativeLifeException, NegativeAttackException {
        spawnEnemy();
    }

    private void renderEnemies(SpriteBatch batch) {
        for (Enemy enemy : enemies) {
            enemy.update(player.getPlayerBody().getPosition().x);
            enemy.draw(batch);
        }
    }

    private void spawnHorse() throws NegativeLifeException, NegativeAttackException {
        Texture horseTexture = new Texture("assets/box.png");
        BlackHorse horse = new BlackHorse(100, 20, horseTexture);
        float spawnX = MathUtils.random(-50, 50);
        float spawnY = 32;
        horse.createEnemy(spawnX, spawnY, batch);
        enemies.add(horse);
    }

    private void createGround(float worldWidth) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 32);

        groundBody = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(worldWidth / 2f, 1 / ChessConstants.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        groundBody.createFixture(fixtureDef);
        shape.dispose();

        groundSprite = new Sprite(new Texture("assets/boardFIX.png"));
        groundSprite.setSize(worldWidth, 32);
        groundSprite.setPosition(-worldWidth / 2f, 0);
    }

    @Override
    public void show() {
        // Implementa según sea necesario
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.handleInput();
        try {
            update(delta);
        } catch (NegativeLifeException e) {
            throw new RuntimeException(e);
        } catch (NegativeAttackException e) {
            throw new RuntimeException(e);
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        groundSprite.draw(batch);
        player.getPlayerSprite().draw(batch);
        renderEnemies(batch);

        batch.end();

        debugRenderer.render(world, camera.combined);

        try {
            handleCollisions();
        } catch (NegativeLifeException e) {
            throw new RuntimeException(e);
        } catch (NegativeAttackException e) {
            throw new RuntimeException(e);
        }
    }

    private void update(float delta) throws NegativeLifeException, NegativeAttackException {
        world.step(1 / 60f, 6, 2);

        float newX = MathUtils.clamp(player.getPlayerBody().getPosition().x, player.getMinXLimit(), player.getMaxXLimit());
        player.getPlayerBody().setTransform(newX, player.getPlayerBody().getPosition().y, 0);

        player.getPlayerSprite().setPosition(
                player.getPlayerBody().getPosition().x - player.getPlayerSprite().getWidth() / 2,
                player.getPlayerBody().getPosition().y - player.getPlayerSprite().getHeight() / 2
        );

        camera.position.x = player.getPlayerBody().getPosition().x;
        camera.update();

        if (killedEnemies >= maxEnemies) {
            spawnHorse();
            maxEnemies += 5;
            killedEnemies = 0;
        }
    }

    public void handleCollisions() throws NegativeLifeException, NegativeAttackException {
        for (Enemy enemy : enemies) {
            if (enemy.getSpriteBounds() != null && player.getPlayerBounds().overlaps(enemy.getSpriteBounds())) {
                player.attack(enemy, enemies);

                if (enemy.getVida() <= 0) {
                    enemies.removeValue(enemy, true);
                    killedEnemies++;
                    spawnNewEnemy();
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        world.dispose();
        debugRenderer.dispose();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public World getWorld() {
        return world;
    }

    public Box2DDebugRenderer getDebugRenderer() {
        return debugRenderer;
    }

    public PawnPlayer getPlayer() {
        return player;
    }

    public Body getGroundBody() {
        return groundBody;
    }

    public Sprite getGroundSprite() {
        return groundSprite;
    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }

    public int getKilledPawns() {
        return killedPawns;
    }

    public int getMaxEnemies() {
        return maxEnemies;
    }

    public int getInitialEnemies() {
        return initialEnemies;
    }

    public int getKilledEnemies() {
        return killedEnemies;
    }
}
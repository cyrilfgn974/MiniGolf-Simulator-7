package golf;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.io.Serializable;

public abstract class Surface implements Serializable, Surfacable, Typable<TypeSurface>, Texturable{

	public static final int C_GRAV = 13;
	
    private static final long serialVersionUID = 1L;

    public static final int SPRITE_HEIGHT = 16;
    public static final int SPRITE_WIDTH = 16;

    protected transient SpriteSheet spriteImg;
    protected final String spriteFilename;

    public Surface(String spriteFileName) {
        this.spriteFilename = spriteFileName;
    }


    public void loadTexture() throws SlickException {
        this.spriteImg = new SpriteSheet(new Image(spriteFilename), SPRITE_WIDTH, SPRITE_HEIGHT, 0, 0);
    }


    @Override
    public void drawTexture(float x, float y, float width, float height) {
        spriteImg.getSubImage(1,1).draw(x,y,width, height);
    }

    @Override
    public boolean equals(Object obj) {
        return this.getType() == ((Surface)obj).getType();
    }
}

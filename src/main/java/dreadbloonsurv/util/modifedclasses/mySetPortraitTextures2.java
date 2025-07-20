package dreadbloonsurv.util.modifedclasses;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import static basemod.abstracts.CustomCard.imgMap;

public class mySetPortraitTextures2
{
        public void mySetPortraitTextures(CustomCard c, String cardFrameSmall, String cardFrameLarge, int x, int y) {
            loadTextureFromString(cardFrameSmall);
            loadTextureFromString(cardFrameLarge);
            Texture t = getTextureFromString(cardFrameSmall);
            c.frameSmallRegion = new TextureAtlas.AtlasRegion(t, x, y, t.getWidth(), t.getHeight());
            t = getTextureFromString(cardFrameLarge);
            c.frameLargeRegion = new TextureAtlas.AtlasRegion(t, x, y, t.getWidth(), t.getHeight());
        }

        private static void loadTextureFromString(String textureString) {
            if (!imgMap.containsKey(textureString)) {
                imgMap.put(textureString, ImageMaster.loadImage(textureString));
            }
        }

    private static Texture getTextureFromString(String textureString) {
        loadTextureFromString(textureString);
        return (Texture)imgMap.get(textureString);
    }
}
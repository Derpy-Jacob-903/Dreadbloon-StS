package dreadbloonsurv.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.vfx.BobEffect;
import dreadbloonsurv.CharacterFile;

import static dreadbloonsurv.ModFile.makeImagePath;
import static dreadbloonsurv.util.TexLoader.getTexture;

@SpirePatch(
        clz = EmptyOrbSlot.class,
        method = "render",
        paramtypez = { SpriteBatch.class } // Specify the parameter types
)
public class OrbRenderPatch {
    protected BobEffect bobEffect;
    public static void Prefix(EmptyOrbSlot ___instance, SpriteBatch sb) {
        //if (AbstractDungeon.player.chosenClass == CharacterFile.Enums.NEMDRUID) {
            Texture img = getTexture(makeImagePath("orbs/moxSlot1.png"));
            Texture img2 = getTexture(makeImagePath("orbs/moxSlot2.png"));
            BobEffect bobEffect = new BobEffect(3.0F * Settings.scale, 3.0F);;
            float scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, 1);
            sb.setColor(Settings.CREAM_COLOR.cpy());
            sb.draw(img2, ___instance.cX - 48.0F - bobEffect.y / 8.0F, ___instance.cY - 48.0F + bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, scale, scale, 0.0F, 0, 0, 96, 96, false, false);
            sb.draw(img, ___instance.cX - 48.0F + bobEffect.y / 8.0F, ___instance.cY - 48.0F - bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, scale, scale, 0f, 0, 0, 96, 96, false, false);
            //___instance.renderText(sb);
            ___instance.hb.render(sb);
        //}
    }
}


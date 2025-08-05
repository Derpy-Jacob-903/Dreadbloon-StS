package dreadbloonsurv.util;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dreadbloonsurv.LiverTest;
import dreadbloonsurv.RWDreadCharaFile;
@SpirePatch(clz = EnergyPanel.class, method = "renderOrb")
public class RenderPatchDread {
    @SpirePostfixPatch
    public static void renderWorkBarDread(EnergyPanel __instance, SpriteBatch sb) {
        if ((AbstractDungeon.player instanceof RWDreadCharaFile) && AbstractDungeon.getCurrRoom() != null &&
            (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            ((RWDreadCharaFile)AbstractDungeon.player).renderWorkLevelBar(sb);
            ((RWDreadCharaFile)AbstractDungeon.player).renderUI(sb);
        }
        if ((AbstractDungeon.player instanceof LiverTest) && AbstractDungeon.getCurrRoom() != null &&
            (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            ((LiverTest)AbstractDungeon.player).renderWorkLevelBar(sb);
            ((LiverTest)AbstractDungeon.player).renderUI(sb);
        }
    }
}
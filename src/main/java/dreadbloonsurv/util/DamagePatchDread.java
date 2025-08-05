package dreadbloonsurv.util;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dreadbloonsurv.LiverTest;
import dreadbloonsurv.RWDreadCharaFile;
import org.example.Character.Liver;

@SpirePatch(clz = AbstractPlayer.class, method = "damage") /* 1831 */
public class DamagePatchDread {
    @SpireInsertPatch(loc = 1831)
    public static SpireReturn<Void> renderWorkBarDread(AbstractPlayer __instance, DamageInfo info) {
        if ((AbstractDungeon.player instanceof RWDreadCharaFile)) {
            if (__instance.currentHealth <= 0) {
                if (((RWDreadCharaFile)__instance).workLevel > 0 || __instance.getPower("Liver:WorkLevelLockBuff") != null)
                {
                    ((RWDreadCharaFile)__instance).relive();
                    return SpireReturn.Return();
                }
            CardCrawlGame.sound.stop("Liver:MushRoom");
            CardCrawlGame.music.unsilenceBGM();
            }
        } else if (AbstractDungeon.player instanceof LiverTest) {
            if (__instance.currentHealth <= 0) {
                if (((LiverTest)__instance).workLevel > 0 || __instance.getPower("Liver:WorkLevelLockBuff") != null)
                {
                    ((LiverTest)__instance).relive();
                    return SpireReturn.Return();
                }
                CardCrawlGame.sound.stop("Liver:MushRoom");
                CardCrawlGame.music.unsilenceBGM();
            }
        }
        if (!(AbstractDungeon.player instanceof Liver)) {
            CardCrawlGame.sound.stop("Liver:MushRoom");
            CardCrawlGame.music.unsilenceBGM();
        }
        return SpireReturn.Continue();
    }
}
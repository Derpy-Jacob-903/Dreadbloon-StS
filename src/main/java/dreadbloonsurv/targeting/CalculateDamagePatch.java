package dreadbloonsurv.targeting;

import basemod.ReflectionHacks;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.BaseHero;
import dreadbloonsurv.orbs.AbstractBloon;
import dreadbloonsurv.orbs.MustHitBloon;
@SpirePatch(
        clz= AbstractMonster.class,
        method="calculateDamage"
)
public class CalculateDamagePatch {
    public static SpireReturn<Void> Prefix(AbstractMonster __instance, int dmg) {
        if (!(AbstractDungeon.player instanceof BaseHero)) { return SpireReturn.Continue(); }
        AbstractBloon highestThreat = null;
        boolean mustHitOnly = false;
        // First pass: check if any MustHitBloon exists
        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            if (orb instanceof MustHitBloon) {
                mustHitOnly = true;
                break;
            }
        }
        // Second pass: find highest threat bloon (respecting must-hit if present)
        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            if (!(orb instanceof AbstractBloon)) continue;
            if (mustHitOnly && !(orb instanceof MustHitBloon)) continue;
            AbstractBloon bloon = (AbstractBloon) orb;
            float bloonThreat = bloon.getMyThreat();
            float currentThreat = highestThreat == null ? ((BaseHero)AbstractDungeon.player).getMyThreat() : highestThreat.getMyThreat();
            if (bloonThreat > currentThreat) {
                highestThreat = bloon;
            }
        }
        if (highestThreat == null) { return SpireReturn.Continue(); }

        float tmp = (float) dmg;
        if (Settings.isEndless && AbstractDungeon.player.hasBlight("DeadlyEnemies")) {
            float mod = AbstractDungeon.player.getBlight("DeadlyEnemies").effectFloat();
            tmp *= mod;
        }

        for (AbstractPower p : __instance.powers) {
            tmp = p.atDamageGive(tmp, DamageInfo.DamageType.NORMAL);
        }

        for (AbstractPower p : __instance.powers) {
            tmp = p.atDamageFinalGive(tmp, DamageInfo.DamageType.NORMAL);
        }

        dmg = MathUtils.floor(tmp);
        if (dmg < 0) {
            dmg = 0;
        }
        ReflectionHacks.setPrivate(__instance, AbstractMonster.class, "intentDmg", dmg);
        //highestThreat.damage(new DamageInfo(__instance, dmg, DamageInfo.DamageType.NORMAL));
        return SpireReturn.Return();
    }

    private boolean applyBackAttack(AbstractMonster __instance) {
        return AbstractDungeon.player.hasPower("Surrounded") && (AbstractDungeon.player.flipHorizontal && AbstractDungeon.player.drawX < __instance.drawX || !AbstractDungeon.player.flipHorizontal && AbstractDungeon.player.drawX > __instance.drawX);
    }
}

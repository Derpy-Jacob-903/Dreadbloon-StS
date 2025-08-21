package dreadbloonsurv.targeting;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import dreadbloonsurv.orbs.AbstractBloon;
import dreadbloonsurv.orbs.ArbitrOrb;
import dreadbloonsurv.powers.bloons.ARBITRVigorPower;
import dreadbloonsurv.powers.bloons.deprecated.ARBITRPower;

public class PlayerPatches {
    @SpirePatch(
            clz= AbstractPlayer.class,
            method="damage"
    )
    public static class damage
    {
        public static SpireReturn<Void> Prefix(AbstractPlayer __instance, DamageInfo info) {
            boolean hasArbitrOrb = false;
            if (info.owner == null) { return SpireReturn.Continue(); }
            for (AbstractOrb orb : __instance.orbs) {
                if (orb instanceof ArbitrOrb) {
                    hasArbitrOrb = true;
                    break;
                }
            }
            if (!hasArbitrOrb || __instance.hasPower(ARBITRPower.POWER_ID)) return SpireReturn.Continue();
            int damageAmount = info.output;
                boolean hadBlock = __instance.currentBlock != 0;

                if (damageAmount < 0) {
                    damageAmount = 0;
                }

                if (damageAmount > 1 && __instance.hasPower("IntangiblePlayer")) {
                    damageAmount = 1;
                }

                if (info.owner == __instance) {
                    for (AbstractRelic r : __instance.relics) {
                        damageAmount = r.onAttackToChangeDamage(info, damageAmount);
                    }
                }

                if (info.owner != null) {
                    for (AbstractPower p : info.owner.powers) {
                        damageAmount = p.onAttackToChangeDamage(info, damageAmount);
                    }
                }

                for (AbstractRelic r : __instance.relics) {
                    damageAmount = r.onAttackedToChangeDamage(info, damageAmount);
                }

                for (AbstractPower p : __instance.powers) {
                    damageAmount = p.onAttackedToChangeDamage(info, damageAmount);
                }

                if (info.owner == __instance) {
                    for (AbstractRelic r : __instance.relics) {
                        r.onAttack(info, damageAmount, __instance);
                    }
                }

                if (info.owner != null) {
                    for (AbstractPower p : info.owner.powers) {
                        p.onAttack(info, damageAmount, __instance);
                    }
                }

                for (AbstractRelic r : __instance.relics) {
                    damageAmount = r.onLoseHpLast(damageAmount);
                }

                __instance.lastDamageTaken = Math.min(damageAmount, __instance.currentHealth);
                if (damageAmount > 0) {
                    for (AbstractPower p : __instance.powers) {
                        damageAmount = p.onLoseHp(damageAmount);
                    }

                    for (AbstractRelic r : __instance.relics) {
                        r.onLoseHp(damageAmount);
                    }

                    for (AbstractPower p : __instance.powers) {
                        p.wasHPLost(info, damageAmount);
                    }

                    for (AbstractRelic r : __instance.relics) {
                        r.wasHPLost(damageAmount);
                    }

                    if (info.owner != null) {
                        for (AbstractPower p : info.owner.powers) {
                            p.onInflictDamage(info, damageAmount, __instance);
                        }
                    }
                    assert info.owner != null;
                    info.owner.addPower(new ARBITRVigorPower(info.owner, damageAmount));
                    return SpireReturn.Return();
                }
            return SpireReturn.Continue();
        }
    }
}

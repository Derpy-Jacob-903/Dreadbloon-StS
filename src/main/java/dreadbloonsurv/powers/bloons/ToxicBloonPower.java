package dreadbloonsurv.powers.bloons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.cards.AbstractEasyCard.autoID;
import static dreadbloonsurv.util.Wiz.atb;
import static dreadbloonsurv.util.Wiz.removePower;

public class ToxicBloonPower extends BasicBloonPower {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

        private static final PowerType TYPE = PowerType.DEBUFF;

        private static final boolean TURN_BASED = true;

        public ToxicBloonPower(AbstractCreature owner, int delay, int health) {
            super(POWER_ID, owner, delay, health, 0, 0, "Toxic Bloon");
        }

    public ToxicBloonPower(AbstractCreature owner, int delay, int health, String name) {
        super(POWER_ID, owner, delay, health, 0, 0, name);
    }

    public void onDestroyed() {
        atb(new ApplyPowerAction(owner, AbstractDungeon.player, new PoisonPower(owner, AbstractDungeon.player, 3), 3));
    }

    public AbstractPower makeCopy() {
            return new ToxicBloonPower(this.owner, this.amount, this.amount2);
        }
}

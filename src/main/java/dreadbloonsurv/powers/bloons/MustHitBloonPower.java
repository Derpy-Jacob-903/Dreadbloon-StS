package dreadbloonsurv.powers.bloons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.cards.AbstractEasyCard.autoID;
import static dreadbloonsurv.util.Wiz.*;

public class MustHitBloonPower extends BasicBloonPower {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

        private static final PowerType TYPE = PowerType.DEBUFF;

        private static final boolean TURN_BASED = true;

        public MustHitBloonPower(AbstractCreature owner, int delay, int health) {
            super(POWER_ID, owner, delay, health, 0, 0, "Rock Bloon");
        }

    public MustHitBloonPower(AbstractCreature owner, int delay, int health, String name) {
        super(POWER_ID, owner, delay, health, 0, 0, name);
    }
    public MustHitBloonPower(String pow_id, AbstractCreature owner, int delay, int health, int armor, String name, boolean immobile) {
        super(pow_id, owner, delay, health, 0, armor, immobile, name);
    }
    public MustHitBloonPower(AbstractCreature owner, int delay, int health, int armor, String name, boolean immobile) {
        super(POWER_ID, owner, delay, health, 0, armor, immobile, name);
    }

    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        att(new GainBlockAction(AbstractDungeon.player, Math.min(damageAmount, this.amount2 + this.amountShield + this.amountArmor)));
        return damageAmount;
    }

    public AbstractPower makeCopy() {
            return new MustHitBloonPower(this.owner, this.amount, this.amount2);
        }
}

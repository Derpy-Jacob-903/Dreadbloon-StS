package dreadbloonsurv.powers.bloons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.util.Wiz.*;

public class SentinelBloonPower extends MustHitBloonPower {
        public static final String POWER_ID = makeID("SentinelBloonPower");

        private static final PowerType TYPE = PowerType.DEBUFF;

        private static final boolean TURN_BASED = true;

    public int damage = 5;

        public SentinelBloonPower(AbstractCreature owner, int delay, int health) {
            super(POWER_ID, owner, delay, health, 0, "Sentinel Bloon", false);
        }

    public SentinelBloonPower(AbstractCreature owner, int delay, int health, String name) {
        super(POWER_ID, owner, delay, health, 0, "Sentinel Bloon", false);
    }
    public SentinelBloonPower(AbstractCreature owner, int delay, int health, int armor, String name, boolean immobile) {
        super(POWER_ID, owner, delay, health, armor, name, immobile);
    }


    @Override
    public void atStartOfTurn()
    {
        if (this.amount < 1)
        {
            this.onLeaked();
            this.onDestroyed();
            removePower(this);
        }
        else {this.amount--;}
    }

    public void onDestroyed()
    {
        if (amount2 > 74.5)
            {
                atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
        else
            {
                atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
    }

    public AbstractPower makeCopy()
    {
            return new SentinelBloonPower(this.owner, this.amount, this.amount2, this.amountArmor, this.name, this.immobile);
    }
}

package dreadbloonsurv.powers.bloons;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.powers.AbstractEasyPower;
import dreadbloonsurv.actions.ReduceBloonHealthAction;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.util.Wiz.atb;
import static dreadbloonsurv.util.Wiz.removePower;

public class BaseBloon extends AbstractEasyPower implements NonStackablePower {
        public static final String POWER_ID = makeID("BaseBloon");

        private static final PowerType TYPE = PowerType.DEBUFF;

        private static final boolean TURN_BASED = true;

        public boolean immobile = false;

        public BaseBloon(AbstractCreature owner, int amount) {
            super(POWER_ID, "Attacking Bloon", TYPE, TURN_BASED, owner, amount);
        }

        //s
        public BaseBloon(String id, String name, AbstractCreature owner, int amount ) {
            super(id, name, TYPE, TURN_BASED, owner, amount);
        }

        public BaseBloon(String powerId, String name, PowerType type, boolean b, AbstractCreature owner, int delay, int health) {
            super(powerId, name, TYPE, TURN_BASED, owner, delay, health, 0, 0, false);
        }
        public BaseBloon(String powerId, String name, PowerType type, boolean b, AbstractCreature owner, int delay, int health, int shield, int armor, boolean immobile) {
            super(powerId, name, TYPE, TURN_BASED, owner, delay, health, armor, shield, false);
        }

        public BaseBloon(String powerId, AbstractCreature owner, int delay, int health, String name) {
            super(powerId, name, TYPE, TURN_BASED, owner, delay, health);
        }

    public void atStartOfTurn()
    {
        if (this.immobile) { return; }
        if (this.amount < 1)
        {
            if (amount2 > 74.5)
            {
                atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
            else
            {
                atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
            this.onLeaked();
            this.onDestroyed();
            removePower(this);
        }
        else {this.amount--;}
    }

    public void onGainedBlock(float blockAmount)
    {
        if (this instanceof MustHitBloonPower || (!this.owner.hasPower(MustHitBloonPower.POWER_ID) && !this.owner.hasPower(SentinelBloonPower.POWER_ID))) {
            addToBot(new ReduceBloonHealthAction(this.owner, this.owner, this, (int) blockAmount));
            this.onDamaged();
        }
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target)
    {
        if (target != this.owner) //Not self damage (i.e. Bloons)
        {
            addToBot(new ReduceBloonHealthAction(this.owner, this.owner, this, info.output));
            this.onDamaged();
        }
    }

    public void updateDescription()
    {
        //if (DESCRIPTIONS != null && DESCRIPTIONS.length > 2)
        //{ this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2]; }
        //else {
        String shieldText = this.amountShield > 0 ? " NL Shield: " + this.amountShield : "";
        String armorText = this.amountArmor > 0 ? " NL Armor: " + this.amountArmor : "";

        if (this.immobile) {
            this.description = "This #yBloon does not naturally pop. NL Delay: ∞ NL Health: " + this.amount2 + shieldText + armorText;
        } else {
            this.description = "In #b" + this.amount + " turn(s), this #yBloon will deal #b" + this.amount2 + " damage." + shieldText + armorText;
        }
    }


    public AbstractPower makeCopy()
    {
        return new BaseBloon(this.owner, this.amount);
    }

    public void onLeaked() {}
    public void onDamaged() {}
    public void onDestroyed() {}
    public void onPopped() {}
}

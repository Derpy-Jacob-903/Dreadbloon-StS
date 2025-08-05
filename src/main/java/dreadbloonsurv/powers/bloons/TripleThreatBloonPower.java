package dreadbloonsurv.powers.bloons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.cards.cardvars.CardTags;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.cards.AbstractEasyCard.autoID;
import static dreadbloonsurv.util.Wiz.atb;
import static dreadbloonsurv.util.Wiz.removePower;

public class TripleThreatBloonPower extends BasicBloonPower {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

        private static final PowerType TYPE = PowerType.DEBUFF;

        private static final boolean TURN_BASED = true;

        public TripleThreatBloonPower(AbstractCreature owner, int delay, int health, int shield, int armor, boolean immobile, String name) {
            super(POWER_ID, owner, delay, health, shield, armor, immobile, name);
        }

        public TripleThreatBloonPower(AbstractCreature owner, int delay, int health, int shield, int armor) {
            super(POWER_ID, owner, delay, health, shield, armor, false, "Triple Threat Bloon");
        }

        public TripleThreatBloonPower(AbstractCreature owner, int delay, int health, String name) {
            super(POWER_ID, owner, delay, health, 0, 0, false, "Triple Threat Bloon");
        }

        @Override
        public void atStartOfTurn()
        {
            if (this.amount < 1)
            {
                if (amount2 > 74.5)
                {
                    atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                }
                else
                {
                    atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                    atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                    atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                }
                this.onLeaked();
                this.onDestroyed();
                removePower(this);
            }
            else {this.amount--;}
        }

        public void onAfterCardPlayed(AbstractCard usedCard) {
            if (usedCard.hasTag(CardTags.POWER_DREADMOD))
            {
                this.amountShield += 6;
            }
        }

    public AbstractPower makeCopy() {
            return new TripleThreatBloonPower(this.owner, this.amount, this.amount2, this.amountShield, this.amountArmor, this.immobile, this.name);
        }
}

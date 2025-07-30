package dreadbloonsurv.powers.bloons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static dreadbloonsurv.ModFile.makeID;
import static dreadbloonsurv.cards.AbstractEasyCard.autoID;
import static dreadbloonsurv.util.Wiz.*;

public class LeadCoatingBloonPower extends BasicBloonPower {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());

        private static final PowerType TYPE = PowerType.DEBUFF;

        public int magicNumber = 1;

        private static final boolean TURN_BASED = true;

        public LeadCoatingBloonPower(AbstractCreature owner, int delay, int health) {
            super(POWER_ID, owner, delay, health, 0, 9, false, "Lead Coating Bloon");
        }

        public LeadCoatingBloonPower(AbstractCreature owner, int delay, int health, int shield, int armor) {
            super(POWER_ID, owner, delay, health, shield, armor, false, "Lead Coating Bloon");
        }

        public LeadCoatingBloonPower(AbstractCreature owner, int delay, int health, int shield, int armor, boolean immobile, String name) {
            super(POWER_ID, owner, delay, health, shield, armor, immobile, name);
        }

        public LeadCoatingBloonPower(String id, String name, AbstractCreature owner, int delay, int health) {
            super(id, name, owner, delay, health);
        }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDeadOrEscaped()) {
                    for (AbstractPower power : m.powers) {
                        if (power instanceof BaseBloon) {
                            ((BaseBloon) power).amount2 += magicNumber;
                        }
                    }
                }
            }
        }
    }


    public AbstractPower makeCopy() {
            return new LeadCoatingBloonPower(this.owner, this.amount, this.amount2, this.amountShield, this.amountArmor);
        }
}

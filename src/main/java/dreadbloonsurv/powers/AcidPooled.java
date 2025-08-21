package dreadbloonsurv.powers;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.orbs.AbstractBloon;

import java.util.ArrayList;

import static dreadbloonsurv.ModFile.makeID;

public class AcidPooled extends AbstractEasyPower {
        public static final String POWER_ID = makeID("Ass");

        private static final PowerType TYPE = PowerType.DEBUFF;

        private static final boolean TURN_BASED = true;

        public AcidPooled(AbstractCreature owner, int amount) {
            super(POWER_ID, "Acid Pooled", TYPE, false, owner, amount);
        }

        public void atStartOfTurn()
        {
            ArrayList<AbstractOrb> orbs = AbstractDungeon.player.orbs;
            for (int i = 0; i < AbstractDungeon.player.orbs.size(); i++) {
                if (orbs.get(i) instanceof AbstractBloon)
                {
                    ((AbstractBloon)orbs.get(i)).damage(new DamageInfo(null, 6, DamageInfo.DamageType.THORNS));
                }
            }
        }

        public void updateDescription()
        {
            this.description = this.DESCRIPTIONS[0] + this.amount + this.DESCRIPTIONS[1];
        }

        public AbstractPower makeCopy() {
            return new AcidPooled(this.owner, this.amount);
        }
    }

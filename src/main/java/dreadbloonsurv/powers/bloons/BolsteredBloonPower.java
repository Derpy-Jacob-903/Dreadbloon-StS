package dreadbloonsurv.powers.bloons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.cards.cardvars.CardTags;

import static dreadbloonsurv.ModFile.makeID;

public class BolsteredBloonPower extends BasicBloonPower {
        public static final String POWER_ID = makeID("LeadCoatingBloonPower");

        private static final PowerType TYPE = PowerType.DEBUFF;

        public int magicNumber = 5;

        private static final boolean TURN_BASED = true;

        public BolsteredBloonPower(AbstractCreature owner, int delay, int health) {
            super(POWER_ID, owner, delay, health, 10, 0, false, "Lead Coating Bloon");
        }

        public BolsteredBloonPower(AbstractCreature owner, int delay, int health, int shield, int armor) {
            super(POWER_ID, owner, delay, health, shield, armor, false, "Lead Coating Bloon");
        }

        public BolsteredBloonPower(AbstractCreature owner, int delay, int health, int shield, int armor, boolean immobile, String name) {
            super(POWER_ID, owner, delay, health, shield, armor, immobile, name);
        }

        public BolsteredBloonPower(String id, String name, AbstractCreature owner, int delay, int health) {
            super(id, name, owner, delay, health);
        }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.hasTag(CardTags.BLOON)) { this.amount2 += magicNumber; }
    }

    public AbstractPower makeCopy() {
            return new BolsteredBloonPower(this.owner, this.amount, this.amount2, this.amountShield, this.amountArmor);
        }
}

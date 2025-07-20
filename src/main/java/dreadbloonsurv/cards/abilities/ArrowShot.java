package dreadbloonsurv.cards.abilities;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.cards.AbstractPowerCostCard;

import static dreadbloonsurv.ModFile.makeID;

public class ArrowShot extends AbstractPowerCostCard {
    public final static String ID = makeID("ArrowShot");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ArrowShot() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, "RapidShot_CardArt");
        baseDamage = 6;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.HEROIC);
        tags.add(CardTags.STRIKE);
        retain = true;
        setOrbTexture("dreadbloonsurvResources/images/512/bloontonium.png", "dreadbloonsurvResources/images/1024/bloontonium.png");
        setBackgroundTexture("dreadbloonsurvResources/images/512/hero_attack.png", "dreadbloonsurvResources/images/1024/hero_attack.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    /*public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.hasTag(BLOON) && this.cost > 0) {
            this.cost = 0;
        }
    }*/

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
package dreadbloonsurv.cards.dreadbloon.bloons;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.cards.AbstractEasyCard;
import dreadbloonsurv.orbs.BasicBloon;

public class CleverGreen extends AbstractEasyCard {
    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass(), ModFile.Enums.DREAD_COLOR);
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CleverGreen() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, "CleverGreenBloon_CardArt");
        baseDamage = 7;
        baseDelay = delay = delay = 1;
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD);
        tags.add(dreadbloonsurv.cards.cardvars.CardTags.BASIC_DREADMOD);
        this.setCardBack(cardSubType.BLOON);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        chan(new BasicBloon(damage, delay, 0, 0, bloonName, Color.YELLOW));
        if (!p.discardPile.isEmpty() && !p.discardPile.getTopCard().hasTag(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD))
        { addToBot(new DrawCardAction(1)); }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (!AbstractDungeon.player.discardPile.isEmpty() && !AbstractDungeon.player.discardPile.getTopCard().hasTag(dreadbloonsurv.cards.cardvars.CardTags.BLOON_DREADMOD))
        { this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy(); }
    }

    @Override
    public void beginGlowing() {
        super.beginGlowing();
    }

    @Override
    public void upp()
    {
        upgradeDamage(6);
    }
}
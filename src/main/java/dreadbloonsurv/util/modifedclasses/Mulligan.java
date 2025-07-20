package dreadbloonsurv.util.modifedclasses;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
//@Deprecated //Was intended for dreadbloonsurv's Starter Relic
public class Mulligan extends AbstractGameAction {
    private AbstractPlayer p;
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private boolean notchip = true;

    public Mulligan(AbstractCreature source) {
        this.setValues(AbstractDungeon.player, source, -1);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.notchip = true;
    }

    public Mulligan(AbstractCreature source, boolean notChip) {
        this.setValues(AbstractDungeon.player, source, -1);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.notchip = notChip;
    }

    public void update() {
        if (this.duration == 0.5F) {
            if (this.notchip) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[1], 3, true, true);
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 3, true, true);
            }

            this.addToBot(new WaitAction(0.25F));
            this.tickDuration();
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                    this.addToTop(new DrawCardAction(this.p, AbstractDungeon.handCardSelectScreen.selectedCards.group.size()));

                    for(AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                        AbstractDungeon.player.hand.moveToBottomOfDeck(c);
                        AbstractDungeon.player.drawPile.shuffle();
                        GameActionManager.incrementDiscard(false);
                        c.triggerOnManualDiscard();
                    }
                }
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }
            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("GamblingChipAction");
        TEXT = uiStrings.TEXT;
    }
}

package dreadbloonsurv.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import java.util.function.Consumer;

import dreadbloonsurv.CharacterFile;
import dreadbloonsurv.powers.BloontoniumPower;
import dreadbloonsurv.powers.bloons.DoubleBloonPower;
import dreadbloonsurv.relics.PickleJar;
import dreadbloonsurv.relics.Ratchet;
import dreadbloonsurv.relics.SpeederatorRelic;
import dreadbloonsurv.relics.StrengthenorRelic;
import dreadbloonsurv.util.CardArtRoller;

import static dreadbloonsurv.ModFile.*;
import static dreadbloonsurv.util.Wiz.*;

public abstract class AbstractEasyCard extends CustomCard {

    public static final String ID = autoID(new Object(){}.getClass().getEnclosingClass());
    public static String autoID(Class<?> clazz) {
        return makeID(clazz.getSimpleName());
    }

    public static String autoID(Class<?> clazz, CardColor cardColor, CharacterFile.) {
        String a = "";
        switch (cardColor)
        {
            case RED: a = "_R"; break;
            case GREEN: a = "_G"; break;
            case BLUE: a = "_B"; break;
            case PURPLE: a = "_W"; break;
        }
        switch ()
        
        
        return makeID(clazz.getSimpleName() + a);
    }

    protected final CardStrings cardStrings;

    public int secondMagic;
    public int baseSecondMagic;
    public boolean upgradedSecondMagic;
    public boolean isSecondMagicModified;

    public int secondDamage;
    public int baseSecondDamage;
    public boolean upgradedSecondDamage;
    public boolean isSecondDamageModified;

    public int secondBlock;
    public int baseSecondBlock;
    public boolean upgradedSecondBlook;
    public boolean isSecondDamageBlook;

    public int delay;
    public int baseDelay;
    public boolean upgradedDelay;
    public boolean isDelayModified;
    public String bloonName = "Null Bloon";


    private boolean needsArtRefresh = false;

    public AbstractEasyCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, ModFile.Enums.DREADBLOON_COLOR, cardID.replace(modID + ":", ""));
    }

    public AbstractEasyCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final String cardArt) {
        this(cardID, cost, type, rarity, target, ModFile.Enums.DREADBLOON_COLOR, cardArt);
    }

    public AbstractEasyCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        this(cardID, cost, type, rarity, target, color, cardID.replace(modID + ":", ""));
    }

    public AbstractEasyCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color, final String cardArt) {
        super(cardID, "", myGetCardTextureString(cardArt, type),
                cost, "", type, color, rarity, target);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        rawDescription = cardStrings.DESCRIPTION;
        name = originalName = cardStrings.NAME;
        initializeTitle();
        initializeDescription();

        if (textureImg.contains("ui/missing.png")) {
            if (CardLibrary.cards != null && !CardLibrary.cards.isEmpty()) {
                CardArtRoller.computeCard(this);
            } else
                needsArtRefresh = true;
        }
        System.out.println(name + ", " + type + ", " + cost + ", " + FlavorText.CardStringsFlavorField.flavor.get(cardStrings) +  ", " + rawDescription + ", " + baseDamage + ", " + baseBlock + ", " + baseMagicNumber + ", " + baseSecondMagic);
    }

    @Override
    protected Texture getPortraitImage() {
        if (textureImg.contains("ui/missing.png")) {
            return CardArtRoller.getPortraitTexture(this);
        } else {
            return super.getPortraitImage();
        }
    }

    public enum cardSubType
    {
        MONKEY, //VESSEL
        BLOON,
        POWER, //LAND
        ABILITY,
        ARTIFACT,
        MOX
    }

    public enum moxColors
    {
        //this is for PROVIDE
        GREEN,
        RUBY,
        BLUE,
        COLORLESS, //Descryption Black Mox?
        DRAGON, //Multiple Mox
        RAINBOW, //Prism Mox
        PURPLE,
        RED,
        YELLOW,
        BLACK
    }

    public void setCardBack(cardSubType subType)
    { setCardBack(this, subType, moxColors.COLORLESS); }

    public void setCardBack(cardSubType subType, moxColors moxColor)
    { setCardBack(this, subType, moxColor); }

    public void setCardBack(final AbstractCard card, cardSubType subType)
    { setCardBack(card, subType, moxColors.COLORLESS); }

    /// sets
    public void setCardBack(final AbstractCard card, cardSubType subType, moxColors moxColor) {
        String aString;
        String bString;

        switch (card.type) {
            case ATTACK:
                bString = "_attack.png";
                break;
            case POWER:
                bString = "_power.png";
                break;
            case SKILL:
            default:
                bString = "_skill.png";
                break;
        }
        switch (subType) {
            case MONKEY:
                aString = "monkey";
                break;
            case BLOON:
                aString = "bloon";
                break;
            case POWER:
                aString = "power";
                break;
            case ABILITY:
                aString = "hero";
                break;
            case MOX:
                aString = "mox"; bString = ".png";
                switch (moxColor) {
                    case GREEN: bString = "_green.png";
                        break;
                    case RUBY: bString = "_ruby.png";
                        break;
                    case BLUE: bString = "_blue.png";
                        break;
                    case DRAGON: bString = "_dragon.png";
                        break;
                    case COLORLESS: bString = "_purple.png";
                        break;
                    case RAINBOW: bString = "_colorless.png";
                        break;
                    default: bString = "_black.png";
                        break;
                }
                break;
            case ARTIFACT:
            default:
                aString = "arti";
                break;
        }
        setBackgroundTexture(makeImagePath("512/" + aString + bString), makeImagePath("1024/" + aString + bString));
    }

    public static String getCardTextureString(final String cardName, final AbstractCard.CardType cardType) {
        String textureString;

        switch (cardType) {
            case ATTACK:
            case POWER:
            case SKILL:
                textureString = makeImagePath("cards/" + cardName + ".png");
                break;
            default:
                textureString = makeImagePath("ui/missing.png");
                break;
        }

        FileHandle h = Gdx.files.internal(textureString);
        if (!h.exists()) {
            textureString = makeImagePath("ui/missing.png");
        }
        return textureString;
    }

    public static String myGetCardTextureString(final String cardArt, final AbstractCard.CardType cardType) {
        String textureString;
        switch (cardType) {
            case ATTACK:
                textureString = makeImagePath("CardArt/Attacks/" + cardArt + ".png");
                break;
            case POWER:
                textureString = makeImagePath("CardArt/Powers/" + cardArt + ".png");
                break;
            case SKILL:
            default:
                textureString = makeImagePath("CardArt/Skills/" + cardArt + ".png");
                break;
        }
        FileHandle h = Gdx.files.internal(textureString);
        if (!h.exists()) {
            textureString = makeImagePath("ui/missing.png");
        }
        return textureString;
    }

    @Override
    public void applyPowers() {
        if (baseSecondDamage > -1) {
            secondDamage = baseSecondDamage;

            int tmp = baseDamage;
            baseDamage = baseSecondDamage;

            super.applyPowers();

            secondDamage = damage;
            baseDamage = tmp;

            super.applyPowers();

            isSecondDamageModified = (secondDamage != baseSecondDamage);
        } else super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (baseSecondDamage > -1) {
            secondDamage = baseSecondDamage;

            int tmp = baseDamage;
            baseDamage = baseSecondDamage;

            super.calculateCardDamage(mo);

            secondDamage = damage;
            baseDamage = tmp;

            super.calculateCardDamage(mo);

            isSecondDamageModified = (secondDamage != baseSecondDamage);
        } else super.calculateCardDamage(mo);
    }

    public void resetAttributes() {
        super.resetAttributes();
        secondMagic = baseSecondMagic;
        isSecondMagicModified = false;
        secondDamage = baseSecondDamage;
        isSecondDamageModified = false;
        delay = baseSecondDamage;
        isDelayModified = false;
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedSecondMagic) {
            secondMagic = baseSecondMagic;
            isSecondMagicModified = true;
        }
        if (upgradedSecondDamage) {
            secondDamage = baseSecondDamage;
            isSecondDamageModified = true;
        }
    }

    protected void upgradeSecondMagic(int amount) {
        baseSecondMagic += amount;
        secondMagic = baseSecondMagic;
        upgradedSecondMagic = true;
    }

    protected void upgradeSecondDamage(int amount) {
        baseSecondDamage += amount;
        secondDamage = baseSecondDamage;
        upgradedSecondDamage = true;
    }

    protected void upgradeDelay(int amount) {
        baseDelay += amount;
        delay = baseDelay;
        upgradedDelay = true;
    }

    protected void uDesc() {
        this.rawDescription = this.cardStrings.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upp();
            if (this.cardStrings.UPGRADE_DESCRIPTION != null) {
                this.uDesc();
            }
        }
    }

    public abstract void upp();

    public void update() {
        super.update();
        if (needsArtRefresh)
            CardArtRoller.computeCard(this);
    }

    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard result = super.makeStatEquivalentCopy();
        if (result instanceof AbstractEasyCard) {
            AbstractEasyCard c = (AbstractEasyCard) result;
            c.baseSecondDamage = c.secondDamage = baseSecondDamage;
            c.baseSecondMagic = c.secondMagic = baseSecondMagic;
            c.baseDelay = c.delay = baseDelay;
        }
        return result;
    }

    // These shortcuts are specifically for cards. All other shortcuts that aren't specifically for cards can go in Wiz.
    protected void dmg(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), fx));
    }

    protected void dmgTop(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        att(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), fx));
    }

    protected void allDmg(AbstractGameAction.AttackEffect fx) {
        atb(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, damageTypeForTurn, fx));
    }

    protected void allDmgTop(AbstractGameAction.AttackEffect fx) {
        att(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, damageTypeForTurn, fx));
    }

    protected void altDmg(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, secondDamage, damageTypeForTurn), fx));
    }

    protected void attMagicNum(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, magicNumber, damageTypeForTurn), fx));
    }

    protected void altDmgTop(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        att(new DamageAction(m, new DamageInfo(AbstractDungeon.player, secondDamage, damageTypeForTurn), fx));
    }

    private AbstractGameAction dmgRandomAction(AbstractGameAction.AttackEffect fx, Consumer<AbstractMonster> extraEffectToTarget, Consumer<AbstractMonster> effectBefore) {
        return actionify(() -> {
            AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if (target != null) {
                calculateCardDamage(target);
                if (extraEffectToTarget != null)
                    extraEffectToTarget.accept(target);
                att(new DamageAction(target, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), fx));
                if (effectBefore != null)
                    effectBefore.accept(target);
            }
        });
    }

    protected void dmgRandom(AbstractGameAction.AttackEffect fx) {
        dmgRandom(fx, null, null);
    }

    protected void dmgRandom(AbstractGameAction.AttackEffect fx, Consumer<AbstractMonster> extraEffectToTarget, Consumer<AbstractMonster> effectBefore) {
        atb(dmgRandomAction(fx, extraEffectToTarget, effectBefore));
    }

    protected void dmgRandomTop(AbstractGameAction.AttackEffect fx) {
        dmgRandomTop(fx, null, null);
    }

    protected void dmgRandomTop(AbstractGameAction.AttackEffect fx, Consumer<AbstractMonster> extraEffectToTarget, Consumer<AbstractMonster> effectBefore) {
        att(dmgRandomAction(fx, extraEffectToTarget, effectBefore));
    }

    protected void blck() {
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }
    protected void blck2() {
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, secondBlock));
    }

    protected void bloontonium() {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BloontoniumPower(AbstractDungeon.player, costForTurn), costForTurn));
    }

    protected void bloonton() {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BloontoniumPower(AbstractDungeon.player, costForTurn), costForTurn));
        //atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block*costForTurn));
    }

    protected void bloonton(int mod) {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BloontoniumPower(AbstractDungeon.player, costForTurn - mod), costForTurn - mod));
        //atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block*costForTurn));
    }


    /**
     *
     */
    protected void commonBloonMods() {
        this.resetAttributes();
        if (p().hasRelic(PickleJar.ID)) { baseDamage += 7; baseDelay = Math.max(delay + 1, 0);}
        if (p().hasRelic(Ratchet.ID) && baseDelay > 1) { baseDelay = Math.max(delay - 1, 1); if (cost >= 0){cost += 1;}}
        if (p().hasRelic(SpeederatorRelic.ID)) { baseDelay = Math.max(delay - 1, 0);}
        if (p().hasRelic(StrengthenorRelic.ID)) { baseDamage += 6;}
        if (p().hasRelic(SpeederatorRelic.ID) && !p().hasRelic(StrengthenorRelic.ID)) { baseDamage -= 5;}
    }

    protected void bloonBlck2() {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BloontoniumPower(AbstractDungeon.player, costForTurn), costForTurn));
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    protected void blckTop() {
        att(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    public String cardArtCopy() {
        return null;
    }

    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return null;
    }
}

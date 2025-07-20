package dreadbloonsurv.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dreadbloonsurv.ModFile;
import dreadbloonsurv.util.TexLoader;

public abstract class AbstractEasyPower extends AbstractPower {
    public int amount2 = -1;
    public int maxHealth = -1;
    public boolean isTwoAmount = false;
    public boolean hasArmor = false;
    public int amountArmor = 0;
    public boolean hasShield = false;
    public int amountShield = 0;
    public boolean immobile = false;
    public boolean amount2IsHealth = false;
    public boolean greenHealth = false;
    private Color color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
    private Color redColor = new Color(1.0F, 0.0F, 0.0F, 1.0F);
    private Color greenColor = new Color(0.0F, 1.0F, 0.0F, 1.0F);
    public static Color redColor2 = Color.RED.cpy();
    public static Color greenColor2 = Color.GREEN.cpy();
    public boolean canGoNegative2 = false;
    protected String[] DESCRIPTIONS;

    public AbstractEasyPower(String ID, String NAME, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
        this.ID = ID;
        this.isTurnBased = isTurnBased;

        this.name = NAME;
        PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(this.ID);
        this.DESCRIPTIONS = strings.DESCRIPTIONS;

        this.owner = owner;
        this.amount = amount;
        this.type = powerType;

        Texture normalTexture = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/" + ID.replaceAll(ModFile.modID + ":", "") + "32.png");
        Texture hiDefImage = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/" + ID.replaceAll(ModFile.modID + ":", "") + "84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        updateDescription();
    }

    public AbstractEasyPower(String ID, String NAME, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int delay, int health) {
        this.ID = ID;
        this.isTurnBased = isTurnBased;

        this.name = NAME;
        PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(this.ID);
        this.DESCRIPTIONS = strings.DESCRIPTIONS;

        this.owner = owner;
        this.isTwoAmount = true;
        this.amount2IsHealth = true;
        this.amount = delay;
        this.amount2 = health;
        this.type = powerType;

        Texture normalTexture = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/" + ID.replaceAll(ModFile.modID + ":", "") + "32.png");
        Texture hiDefImage = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/" + ID.replaceAll(ModFile.modID + ":", "") + "84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }
        updateDescription();
    }

    public AbstractEasyPower(String ID, String NAME, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int delay, int health, int armor, int shield, boolean immobile) {
        this.ID = ID;
        this.isTurnBased = isTurnBased;

        this.name = NAME;
        PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(this.ID);
        this.DESCRIPTIONS = strings.DESCRIPTIONS;

        this.owner = owner;
        this.isTwoAmount = true;
        this.amount = delay;
        this.amount2 = health;
        this.maxHealth =
        this.amountShield = health;
        this.amountArmor = armor;
        this.immobile = immobile;
        this.type = powerType;

        Texture normalTexture = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/" + ID.replaceAll(ModFile.modID + ":", "") + "32.png");
        Texture hiDefImage = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/" + ID.replaceAll(ModFile.modID + ":", "") + "84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }
        updateDescription();
    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        renderAmountNew(sb, x, y, c);
        renderAmount2(sb, x, y, c);
        renderShield(sb, x, y, c);
        renderArmor(sb, x, y, c);
    }

    public void renderAmount2(SpriteBatch sb, float x, float y, Color c) {
        if (!isTwoAmount)
            return;
        if (!amount2IsHealth)
        {
            if (amount2 > 0)
            {
                if (!isTurnBased) {
                    greenColor2.a = c.a;
                    c = greenColor2;
                }
                FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
            }
            else if (amount2 < 0 && canGoNegative2)
            {
                redColor2.a = c.a;
                c = redColor2;
                FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
            }
        }
        else
        {
            if (amount2 < maxHealth)
            {
                redColor2.a = c.a;
                c = redColor2;
                FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
                FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
            }
            else if (greenHealth)
            {
                greenColor2.a = c.a;
                c = greenColor2;
                FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
            }
        }
    }

    public void renderAmountNew(SpriteBatch sb, float x, float y, Color c) {
        if (!this.immobile) {super.renderAmount(sb, x, y, c);}
        if (this.amount > 0) {
            if (!this.isTurnBased) {
                greenColor.a = c.a;
                c = this.greenColor;
            }
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, "∞", x, y, this.fontScale, c);
        } else if (this.amount < 0 && this.canGoNegative) {
            this.redColor.a = c.a;
            c = this.redColor;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, "∞", x, y, this.fontScale, c);
        }

    }

    public void renderShield(SpriteBatch sb, float x, float y, Color c) {
        if (!isTwoAmount)
            return;
        Texture normalTexture = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/" + "BasicBloonPowerShield32.png");
        if (amount2 > 0) {
            if (!isTurnBased) {
                greenColor2.a = c.a;
                c = greenColor2;
            }

            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
        } else if (amount2 < 0 && canGoNegative2) {
            redColor2.a = c.a;
            c = redColor2;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
        }
    }

    public void renderArmor(SpriteBatch sb, float x, float y, Color c) {
        if (!isTwoAmount)
            return;
        Texture normalTexture = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/" + "BasicBloonPowerArmor32.png");
        sb.draw(normalTexture, x - (float)normalTexture.getWidth() / 2.0F, y - (float)normalTexture.getHeight() / 2.0F, (float)normalTexture.getWidth() / 2.0F, (float)normalTexture.getHeight() / 2.0F, (float)normalTexture.getWidth(), (float)normalTexture.getHeight(), Settings.scale, Settings.scale);
        if (amount2 > 0) {
            if (!isTurnBased) {
                greenColor2.a = c.a;
                c = greenColor2;
            }

            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
        } else if (amount2 < 0 && canGoNegative2) {
            redColor2.a = c.a;
            c = redColor2;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale, c);
        }
    }
}
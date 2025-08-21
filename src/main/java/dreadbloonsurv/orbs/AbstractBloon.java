package dreadbloonsurv.orbs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.*;
import dreadbloonsurv.util.modifedclasses.StrikeEffectForOrb;

import java.util.ArrayList;
import java.util.Collections;

import static basemod.BaseMod.logger;
import static dreadbloonsurv.ModFile.makeImagePath;
import static dreadbloonsurv.cards.AbstractEasyCard.autoID;
import static dreadbloonsurv.util.TexLoader.getTexture;

@SuppressWarnings("FieldMayBeFinal")
public class AbstractBloon extends AbstractOrb {
    private static final float BLOCK_ICON_X ;
    private static final float BLOCK_ICON_Y;
    public int health;
    public int delay;
    public int shield = 0;
    public int armor = 0;
    public static boolean isThrown = true;
    public int slot = -1;
    public GetLeakTargetAction tar = new GetLeakTargetAction(this, m -> this.use(m));
    public Texture img1 = getTexture(makeImagePath("orbs/Bloon1.png"));
    public Texture img2 = getTexture(makeImagePath("orbs/Bloon2.png"));
    public Texture img3 = getTexture(makeImagePath("orbs/Bloon3.png"));
    public com.badlogic.gdx.graphics.Color bloonColor = new com.badlogic.gdx.graphics.Color(1, 1, 1 ,1);
    public com.badlogic.gdx.graphics.Color lineColor = new com.badlogic.gdx.graphics.Color(.431f, .431f, .431f, 1f);
    public ArrayList<AbstractPower> powers = new ArrayList<AbstractPower>();
    private boolean isBloodied;

    public AbstractBloon(int health, int delay, int shield, int armor, String name) {
        super();
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
        this.name = name;

        // Stats
        this.health = Math.max(0, health);
        this.delay  = delay;   // -1 means ∞ in your description/UI
        this.shield = Math.max(0, shield);
        this.armor  = Math.max(0, armor);

        // Baseline transforms (engine will animate these later)
        this.angle = 0.0f;
        this.scale = 1.0f;
        this.fontScale = 0.7f; // matches vanilla orb text size nicely

        // Sync vanilla fields so tooltips/hover can reflect something sane
        this.evokeAmount   = this.health;
        this.passiveAmount = (this.delay == -1) ? 0 : this.delay;

        updateDescription();
    }

    public AbstractBloon(int health, int delay, int shield, int armor, String name, Color color) {
        super();
        this.ID = autoID(new Object(){}.getClass().getEnclosingClass());
        this.name = name;

        // Stats
        this.health = Math.max(0, health);
        this.delay  = delay;   // -1 means ∞ in your description/UI
        this.shield = Math.max(0, shield);
        this.armor  = Math.max(0, armor);

        // Colors already exist; make sure alpha matches the orb’s color (used by text)
        this.bloonColor = color.cpy();
        this.lineColor = bloonColor.cpy().mul(new com.badlogic.gdx.graphics.Color(.431f, .431f, .431f, 1f));

        // Baseline transforms (engine will animate these later)
        this.angle = 0.0f;
        this.scale = 1.0f;
        this.fontScale = 0.7f; // matches vanilla orb text size nicely

        // Sync vanilla fields so tooltips/hover can reflect something sane
        this.evokeAmount   = this.health;
        this.passiveAmount = (this.delay == -1) ? 0 : this.delay;

        updateDescription();
    }

    @Override
    public void updateDescription()
    {
        //if (DESCRIPTIONS != null && DESCRIPTIONS.length > 2)
        //{ this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2]; }
        //else {
        String shieldText = this.shield > 0 ? " NL #yShield will block #b" + this.shield + " incoming damage." : "";
        String armorText = this.armor > 0 ? " NL #yArmor reduces damage received by #b" + this.armor + "." : "";

        if (this.delay == -1) {
            this.description = "This #yBloon does not #yLeak. NL Delay: ∞ NL Health: " + this.health + shieldText + armorText;
        } else {
            this.description = "#yPassive: At the end of your turn, if delay is 0, #yEvoke. Otherwise reduce delay by 1. NL #yEvoke: If delay is 0, deal #b" + this.health + " damage to target monster.  NL Bloons are unaffected by #yFocus." + shieldText + armorText;
        }
    }

    public void onEvoke() {
        if (delay == 0) { this.use(); }
        else { endLife(ExitCause.EVOKE); }
    }

    public void onEndOfTurn() {
        if (delay != -1) {
            if (delay > 0) { this.delay--; }
            else { addToBot(new EvokeSpecificOrbAction(this)); }
        }
    }

    public void onLeaked(AbstractCreature target) {}
    public void onDamaged(AbstractCreature attacker) {}
    @Deprecated ///No 'On Destroyed' effect cares what destroyed the Bloon.
                ///(namely: Draw a card, Add a {card} to your hand, or Summon a {Bloon})
    public void onDestroyed(AbstractCreature attacker) {}
    public void onDestroyed() {}
    public void onPopped(AbstractCreature attacker) {}

    private enum ExitCause { POPPED, LEAKED, EVOKE }
    private boolean destroyedOnce = false;

    public float getMyThreat() {
        return (this.health) + (this.health + this.shield) * (1 + (float)this.armor / 10);
    }

    /**
     * triggers onPopped, onLeaked, and onDestroyed, plus removes POPPED Orbs without evoking.
     */
    private void endLife(ExitCause cause, AbstractCreature param2, DamageInfo overkill) {
        if (destroyedOnce) return;
        destroyedOnce = true;

        if (cause == ExitCause.LEAKED) { onLeaked(param2); }
        else { onPopped(param2); }
        //onDestroyed(param2);
        onDestroyed();
        if (cause == ExitCause.POPPED)
        {
            if (overkill != null) {
                if (AbstractDungeon.player.orbs.indexOf(this) + 1 < AbstractDungeon.player.orbs.size()) {
                    AbstractOrb nextOrb = AbstractDungeon.player.orbs.get(AbstractDungeon.player.orbs.indexOf(this) + 1);
                    if (nextOrb instanceof AbstractBloon) {
                        ((AbstractBloon) nextOrb).damage(overkill);
                    }
                    else {
                        AbstractDungeon.player.damage(overkill);
                    }
                }
            }
            AbstractDungeon.player.orbs.remove(this);
            AbstractDungeon.player.orbs.add(0, this);
            popOrb(AbstractDungeon.player); // remove without evoking
        }
        // don't need to remove on leak/evoke
    }

    private void endLife(ExitCause cause, AbstractCreature param2) {
        endLife(cause, param2, null);
    }
    private void endLife(ExitCause cause) {
        endLife(cause, null, null);
    }

    public void damage(int amount, AbstractCreature attacker)
    {
        damage(new DamageInfo(attacker, amount, DamageInfo.DamageType.HP_LOSS));
        if (health <= 0)
        {
            endLife(ExitCause.POPPED, attacker);
        }
    }

    @Deprecated
    public static void reducePower2(int reduceAmount, AbstractBloon reduceMe) {
        int reduceAmount2 = reduceAmount;
        reduceAmount2 -= reduceMe.armor;

        if (!(reduceAmount2 > 0)) { return; }

        //Shield calc
        if (reduceMe.shield < reduceAmount2) {
            reduceAmount2 -= reduceMe.shield;
            reduceMe.shield = 0;
        } else {
            reduceMe.shield -= reduceAmount2;
            return;
        }

        if (reduceMe.health - reduceAmount2 <= 0) {
            reduceMe.health = 0;
            //reduceMe.onDamaged();
        } else {
            reduceMe.health -= reduceAmount2;
            //reduceMe.onDamaged();
        }
    }

    public void popOrb(AbstractPlayer p) {
        if (!p.orbs.isEmpty() && !(p.orbs.get(0) instanceof EmptyOrbSlot)) {
            //((AbstractOrb)p.orbs.get(0)).onEvoke();
            AbstractOrb orbSlot = new EmptyOrbSlot();

            for(int i = 1; i < p.orbs.size(); ++i) {
                Collections.swap(p.orbs, i, i - 1);
            }

            p.orbs.set(p.orbs.size() - 1, orbSlot);

            for(int i = 0; i < p.orbs.size(); ++i) {
                ((AbstractOrb)p.orbs.get(i)).setSlot(i, p.maxOrbs);
            }
        }
    }

    public void popOrb(ArrayList<AbstractOrb> orbs, int maxOrbs) {
        if (!orbs.isEmpty() && !(orbs.get(0) instanceof EmptyOrbSlot)) {
            //orbs.get(0).onEvoke();
            AbstractOrb orbSlot = new EmptyOrbSlot();
            for (int j = 1; j < orbs.size(); j++)
                Collections.swap(orbs, j, j - 1);
            orbs.set(orbs.size() - 1, orbSlot);
            for (int i = 0; i < orbs.size(); i++)
                orbs.get(i).setSlot(i, maxOrbs);
            }
        }

    public void use() {
        tar.hasStarted = this.showEvokeValue = true;
        addToBot(tar);
        //tar.start(this, m -> this.use(m));
    }

    public void use(AbstractCreature target) {
        if (target == null || target.isDeadOrEscaped() || target.halfDead) target = AbstractDungeon.getMonsters() != null ? AbstractDungeon.getMonsters().getRandomMonster(true) : null;
        if (target == null) { popOrb(AbstractDungeon.player.orbs, this.slot); return;}
        DamageInfo info = new DamageInfo((AbstractCreature) AbstractDungeon.player, this.health, DamageInfo.DamageType.THORNS);
        info.applyEnemyPowersOnly(target);
        damageTarget(target, info);
        endLife(ExitCause.LEAKED, target);
        //popOrb(AbstractDungeon.player.orbs, this.slot);
    }
    public void damageTarget(AbstractCreature target, DamageInfo info){
        addToBot(new DamageAction(target, info, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    protected void addToBot(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }
    protected void addToTop(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }

    @Override
    public AbstractOrb makeCopy() {
        logger.warn("You should add a Override for 'makeCopy()' for this AbstractBloon!");
        return null;
    }

    @Override
    public void render(SpriteBatch sb) {
        {
            sb.setColor(com.badlogic.gdx.graphics.Color.WHITE);
            if (this.showEvokeValue) { tar.render(sb); }
            sb.setColor(this.lineColor);
            sb.draw(this.img1, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
            sb.setColor(this.bloonColor);
            sb.draw(this.img2, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
            sb.setColor(com.badlogic.gdx.graphics.Color.WHITE);
            sb.draw(this.img3, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
            renderText(sb);
            this.hb.render(sb);
        }
    }

    protected String getEvokeAmountString()
    {
        this.evokeAmount = this.health;
        return Integer.toString(this.evokeAmount);
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        if (this.showEvokeValue) {
            if (delay == 0) {FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, this.getEvokeAmountString(), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new com.badlogic.gdx.graphics.Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale); }
            else {FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(0), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new com.badlogic.gdx.graphics.Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);}
        } else {
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.health), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F, this.c, this.fontScale);
            if (this.delay != -1) {FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.delay), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F, this.c, this.fontScale);} else {FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, "inf", this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F, this.c, this.fontScale);}
            if (this.shield > 0) {FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.shield), this.cX - NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F, new com.badlogic.gdx.graphics.Color(0.6F, 1.0F, 0.882F, this.c.a), this.fontScale);}
            if (this.armor > 0) {FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.armor), this.cX - NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F, this.c, this.fontScale);}
        }
    }

    @Override
    public void playChannelSFX() {

    }

    @Override
    public void setSlot(int slotNum, int maxOrbs) {
        super.setSlot(slotNum, maxOrbs);
        this.slot = slotNum;
    }

    /*public void damage(DamageInfo info) {
        int damageAmount = info.output;

        if (damageAmount < 0) {
            damageAmount = 0;
        }

        if (info.owner != null) {
            for(AbstractPower p : info.owner.powers) {
                damageAmount = p.onAttackToChangeDamage(info, damageAmount);
            }
        }

        for(AbstractPower p : this.powers) {
            damageAmount = p.onAttackedToChangeDamage(info, damageAmount);
        }

        if (info.owner != null) {
            for(AbstractPower p : info.owner.powers) {
                p.onAttack(info, damageAmount, AbstractDungeon.player);
            }
            for(AbstractPower p : this.powers) {
                damageAmount = p.onAttacked(info, damageAmount);
            }
        } else {
            logger.info("NO OWNER, DON'T TRIGGER POWERS");
        }

        if (damageAmount > 0) {
            if (info.owner != null) {
                for(AbstractPower p : info.owner.powers) {
                    p.onInflictDamage(info, damageAmount, AbstractDungeon.player);
                }
            }
            this.damage(damageAmount);
            AbstractDungeon.effectList.add(new StrikeEffectForOrb(this, this.hb.cX, this.hb.cY, damageAmount));
        }
        {
            AbstractDungeon.effectList.add(new StrikeEffectForOrb(this, this.hb.cX, this.hb.cY, 0));
        }

    }*/

    public void damage(DamageInfo info) {
        AbstractPlayer pp = AbstractDungeon.player;
        int damageAmount = info.output;
        boolean hadBlock = true;
        if (this.shield == 0) {
            hadBlock = false;
        }

        if (damageAmount > 0 && this.armor > 0) {
            damageAmount -= this.armor;
        }

        if (damageAmount < 0) {
            damageAmount = 0;
        }

        if (damageAmount > 1 && this.hasPower("IntangiblePlayer")) {
            damageAmount = 1;
        }
        
        if (info.type != DamageInfo.DamageType.HP_LOSS && this.shield > 0) {
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
            if (damageAmount > this.shield) {
                damageAmount -= this.shield;
                if (Settings.SHOW_DMG_BLOCK) {
                    AbstractDungeon.effectList.add(new BlockedNumberEffect(this.hb.cX, this.hb.cY + this.hb.height / 2.0F, Integer.toString(this.shield)));
                }

                this.loseBlock(this.shield, false);
                //this.brokeBlock();
            } else if (damageAmount == this.shield) {
                damageAmount = 0;
                this.loseBlock(this.shield, false);
                //this.brokeBlock();
                //AbstractDungeon.effectList.add(new BlockedWordEffect(this, this.hb.cX, this.hb.cY, AbstractCreature.TEXT[1]));
            } else {
                CardCrawlGame.sound.play("BLOCK_ATTACK");
                this.loseBlock(damageAmount, false);

                for(int i = 0; i < 18; ++i) {
                    AbstractDungeon.effectList.add(new BlockImpactLineEffect(this.hb.cX, this.hb.cY));
                }

                if (Settings.SHOW_DMG_BLOCK) {
                    AbstractDungeon.effectList.add(new BlockedNumberEffect(this.hb.cX, this.hb.cY + this.hb.height / 2.0F, Integer.toString(damageAmount)));
                }

                damageAmount = 0;
            }
        }

        //damageAmount = this.decrementBlock(info, damageAmount);

        if (info.owner != null) {
            for(AbstractPower p : info.owner.powers) {
                damageAmount = p.onAttackToChangeDamage(info, damageAmount);
            }
        }

        for(AbstractPower p : this.powers) {
            damageAmount = p.onAttackedToChangeDamage(info, damageAmount);
        }

        if (info.owner != null) {
            for(AbstractPower p : info.owner.powers) {
                p.onAttack(info, damageAmount, pp);
            }

            for(AbstractPower p : this.powers) {
                damageAmount = p.onAttacked(info, damageAmount);
            }
        } else {
            logger.info("NO OWNER, DON'T TRIGGER POWERS");
        }

        if (damageAmount > 0) {
            for(AbstractPower p : this.powers) {
                damageAmount = p.onLoseHp(damageAmount);
            }

            for(AbstractPower p : this.powers) {
                p.wasHPLost(info, damageAmount);
            }

            if (info.owner != null) {
                for(AbstractPower p : info.owner.powers) {
                    p.onInflictDamage(info, damageAmount, pp);
                }
            }

            if (info.type == DamageInfo.DamageType.HP_LOSS) {
                GameActionManager.hpLossThisCombat += damageAmount;
            }

            GameActionManager.damageReceivedThisTurn += damageAmount;
            GameActionManager.damageReceivedThisCombat += damageAmount;
            int tmpHealth = this.health;
            this.health -= damageAmount;
            damageAmount -= tmpHealth;
            /*if (damageAmount > 0 && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                this.updateCardsOnDamage();
                ++this.damagedThisCombat;
            }*/

            AbstractDungeon.effectList.add(new StrikeEffectForOrb(this, this.hb.cX, this.hb.cY, damageAmount));
            if (this.health < 0) {
                this.health = 0;
            }

            if (this.health < 1) {
                this.endLife(ExitCause.POPPED, info.owner);

            }
        } else if (this.shield > 0) {
            AbstractDungeon.effectList.add(new BlockedWordEffect(pp, this.hb.cX, this.hb.cY, AbstractPlayer.uiStrings.TEXT[0]));
        } else if (hadBlock) {
            AbstractDungeon.effectList.add(new BlockedWordEffect(pp, this.hb.cX, this.hb.cY, AbstractPlayer.uiStrings.TEXT[0]));
            AbstractDungeon.effectList.add(new HbBlockBrokenEffect(this.hb.cX - this.hb.width / 2.0F + BLOCK_ICON_X, this.hb.cY - this.hb.height / 2.0F + BLOCK_ICON_Y));
        } else {
            AbstractDungeon.effectList.add(new StrikeEffectForOrb(this, this.hb.cX, this.hb.cY, 0));
        }

    }

    public void loseBlock(int amount, boolean noAnimation) {
        boolean effect = false;
        if (this.shield != 0) {
            effect = true;
        }

        this.shield -= amount;
        if (this.shield < 0) {
            this.shield = 0;
        }

        if (this.shield == 0 && effect) {
            if (!noAnimation) {
                AbstractDungeon.effectList.add(new HbBlockBrokenEffect(this.hb.cX - this.hb.width / 2.0F + BLOCK_ICON_X, this.hb.cY - this.hb.height / 2.0F + BLOCK_ICON_Y));
            }
        } else if (this.shield > 0 && amount > 0) {
            Color tmp = Color.SCARLET.cpy();
            tmp.a = new Color(0.9F, 0.9F, 0.9F, 0.0F).a;
        }
    }
    public boolean hasPower(String targetID) {
        for(AbstractPower p : this.powers) {
            if (p.ID.equals(targetID)) {
                return true;
            }
        }

        return false;
    }

    static {
        BLOCK_ICON_X = -14.0F * Settings.scale;
        BLOCK_ICON_Y = -14.0F * Settings.scale;
    }
}

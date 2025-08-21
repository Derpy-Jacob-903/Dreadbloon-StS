/*    */ package dreadbloonsurv.util.modifedclasses;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.StanceStrings;
/*    */ import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*    */
/*    */ import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
/*    */

import static dreadbloonsurv.ModFile.makeID;

/*    */
/*    */ public class ImmunityStance extends AbstractStance {
/*    */   public static final String STANCE_ID = makeID("ImmunityStance");
/* 18 */   private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(makeID("ImmunityStance"));
/* 19 */   private static long sfxId = -1L;
/*    */   
/*    */   public ImmunityStance() {
/* 22 */     this.ID = makeID("ImmunityStance");
             try {
                 this.name = stanceString.NAME;
             }
             catch (Exception e) {
                 this.name = "Immunity";
             }
/* 24 */     updateDescription();
/*    */   }

/*    */   public float atDamageReceive(float damage, DamageInfo.DamageType type) {
/* 37 */     if (type == DamageInfo.DamageType.NORMAL) {
/* 38 */       return damage * 0F;
/*    */     }
/* 40 */     return damage;
/*    */   }

    public void atStartOfTurn() {
        /* 49 */     AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction("Neutral"));
        /*    */   }

    /*    */   public void updateAnimation() {
/* 46 */     if (!Settings.DISABLE_EFFECTS) {
/*    */       
/* 48 */       this.particleTimer -= Gdx.graphics.getDeltaTime();
/* 49 */       if (this.particleTimer < 0.0F) {
/* 50 */         this.particleTimer = 0.05F;
/* 51 */         AbstractDungeon.effectsQueue.add(new ImmunityParticleEffect());
/*    */       } 
/*    */     } 
/*    */
/* 56 */     this.particleTimer2 -= Gdx.graphics.getDeltaTime();
/* 57 */     if (this.particleTimer2 < 0.0F) {
/* 58 */       this.particleTimer2 = MathUtils.random(0.3F, 0.4F);
/* 59 */       AbstractDungeon.effectsQueue.add(new StanceAuraEffectButCooler(makeID("ImmunityStance"), StanceAuraEffectButCooler.STANCECOLOR.YELLOW));
/*    */     } 
/*    */   }

/*    */   public void updateDescription() {
            try {
                this.description = stanceString.NAME;
            }
            catch (Exception e) {
                this.description = "Take no damage this turn.";
            }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnterStance() {
/* 70 */     if (sfxId != -1L) {
/* 71 */       stopIdleSfx();
/*    */     }
/*    */     
/* 74 */     CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
/* 75 */     sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_WRATH");
/* 76 */     AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.BROWN, true));
/* 77 */     AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, makeID("ImmunityStance")));
/*    */   }
/*    */   
/*    */   public void onExitStance() {
/* 83 */     stopIdleSfx();
/*    */   }
/*    */   
/*    */   public void stopIdleSfx() {
/* 88 */     if (sfxId != -1L) {
/* 89 */       CardCrawlGame.sound.stop("STANCE_LOOP_WRATH", sfxId);
/* 90 */       sfxId = -1L;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Program Files (x86)\Steam\steamapps\common\SlayTheSpire\desktop-1.0.jar!\com\megacrit\cardcrawl\stances\WrathStance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
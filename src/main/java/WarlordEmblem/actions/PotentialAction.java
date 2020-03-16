 package WarlordEmblem.actions;
 import WarlordEmblem.WarlordEmblem;
 import WarlordEmblem.relics.RuneSword;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.CardLibrary;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.PoisonPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import com.megacrit.cardcrawl.screens.CardRewardScreen;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

 import java.util.ArrayList;
 
 public class PotentialAction extends AbstractGameAction {
     private boolean freeToPlayOnce;
     private boolean upgraded;
     private AbstractPlayer p;
     private AbstractMonster m;
     private int energyOnUse;

   public PotentialAction(AbstractPlayer p, AbstractMonster m, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
       this.p = p;
       this.m = m;
       this.freeToPlayOnce = freeToPlayOnce;
       this.upgraded = upgraded;
       this.duration = Settings.ACTION_DUR_XFAST;
       this.actionType = AbstractGameAction.ActionType.SPECIAL;
       this.energyOnUse = energyOnUse;
   }

   
   public void update() {
       int effect = EnergyPanel.totalCount;
       if (this.energyOnUse != -1) {
           effect = this.energyOnUse;
       }

            if (this.p.hasRelic("Chemical X")) {
                  effect += 2;
                  this.p.getRelic("Chemical X").flash();
                }
       
            if (this.upgraded) {
                  effect++;
                }

            if (effect > 0) {
                for (AbstractMonster mo : (AbstractDungeon.getMonsters()).monsters){
                    if (!mo.isDead && !mo.isDying) {
                        addToBot(new ApplyPowerAction(mo, this.p, new StrengthPower(mo, -effect), -effect));
                        addToBot(new ApplyPowerAction(mo, this.p, new PoisonPower(mo, this.p, effect), effect));
                    }
                }

                if (!this.freeToPlayOnce) {
                        this.p.energy.use(EnergyPanel.totalCount);
                      }
                }

       if (AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword"))){
           RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
           if (rs != null)
               rs.plusRune(effect);
           this.isDone = true;
       }else {
           this.isDone = true;
       }
   }
 }



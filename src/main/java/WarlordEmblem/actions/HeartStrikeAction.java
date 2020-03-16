 package WarlordEmblem.actions;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.CardLibrary;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.screens.CardRewardScreen;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

 import java.util.ArrayList;
 
 public class HeartStrikeAction extends AbstractGameAction {

     private int num = 0;
   public HeartStrikeAction(int damageAdd) {
        this.num  = damageAdd;
       this.duration = Settings.ACTION_DUR_MED;
       this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
   }

   
   public void update() {
        if(this.duration == Settings.ACTION_DUR_MED){
            AbstractPlayer p = AbstractDungeon.player;

            upgradeAllStrikeCardsDamageInGroup(p.hand);
            upgradeAllStrikeCardsDamageInGroup(p.drawPile);
            upgradeAllStrikeCardsDamageInGroup(p.discardPile);
            upgradeAllStrikeCardsDamageInGroup(p.exhaustPile);

            this.isDone = true;
            }
       tickDuration();

   }

     private void upgradeAllStrikeCardsDamageInGroup(CardGroup cardGroup) {
         for (AbstractCard c : cardGroup.group) {
             if (c.hasTag(AbstractCard.CardTags.STRIKE)) {
                 if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                     c.superFlash();
                 }
                 c.baseDamage += this.num;
                 c.upgradedDamage = true;
                 c.applyPowers();

             }
         }
     }
 }



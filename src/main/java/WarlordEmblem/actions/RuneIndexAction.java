 package WarlordEmblem.actions;
 import WarlordEmblem.patches.CustomTagsEnum;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.CardLibrary;
 import com.megacrit.cardcrawl.screens.CardRewardScreen;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

 import java.util.ArrayList;
 
 public class RuneIndexAction extends AbstractGameAction {
     private AbstractPlayer player;
     private int numberOfCards;


   public RuneIndexAction(int numberOfCards) {
       this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
       this.duration = Settings.ACTION_DUR_FAST;
       this.player = AbstractDungeon.player;
       this.numberOfCards = numberOfCards;

   }

   
   public void update() {
       if (this.duration == this.startDuration) {
           if (this.player.drawPile.isEmpty() || this.numberOfCards <= 0) {
               this.isDone = true;
               return;
           }//为零直接结束


           ArrayList<AbstractCard> Realmcards = new ArrayList<AbstractCard>();
           for(AbstractCard c : this.player.drawPile.group){
               if(c.hasTag(CustomTagsEnum.Realm_Tag))
                   Realmcards.add(c);
           }//加入所有的领域卡



           ArrayList<AbstractCard> cardsToMove = new ArrayList<AbstractCard>();
                  if (Realmcards.size() >= this.numberOfCards) {
                      int temp;
                      for (int i = 0;i < this.numberOfCards;i++) {
                          temp = AbstractDungeon.cardRng.random(Realmcards.size() - 1);
                          cardsToMove.add(Realmcards.get(temp));
                          cardsToMove.remove(temp);
                      }
                                cardsToMove.addAll(Realmcards);
                  }


           for (AbstractCard c : cardsToMove) {
               if (this.player.hand.size() >= 10) {
                   this.player.drawPile.moveToDiscardPile(c);
                   this.player.createHandIsFullDialog(); continue;
               }
               this.player.drawPile.moveToHand(c, this.player.drawPile);
           }

           this.isDone = true;
           return;
       }
       tickDuration();
   }
 }



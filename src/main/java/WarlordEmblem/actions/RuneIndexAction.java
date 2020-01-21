 package WarlordEmblem.actions;
 import WarlordEmblem.patches.CustomTagsEnum;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.CardLibrary;
 import com.megacrit.cardcrawl.screens.CardRewardScreen;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

 import java.util.ArrayList;
 
 public class RuneIndexAction extends AbstractGameAction {
     private AbstractPlayer p;
     private int numberOfCards;
     private AbstractCard.CardTags RealmToCheck;


   public RuneIndexAction(int amount,  AbstractCard.CardTags tags) {
       this.p = AbstractDungeon.player;
       setValues(this.p, this.p, amount);
       this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
       this.duration = Settings.ACTION_DUR_MED;
       this.RealmToCheck = tags;

   }

   
   public void update() {
            if (this.duration == Settings.ACTION_DUR_MED) {
           
                  if (this.p.drawPile.isEmpty()) {
                        this.isDone = true;
               
                        return;
                      }
                  CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                  for (AbstractCard c : this.p.drawPile.group) {
                        if (c.hasTag(this.RealmToCheck)) {
                              tmp.addToRandomSpot(c);
                            }
                      }
           
                  if (tmp.size() == 0) {
                        this.isDone = true;
               
                        return;
                      }
                  for (int i = 0; i < this.amount; i++) {
                        if (!tmp.isEmpty()) {
                              tmp.shuffle();
                              AbstractCard card = tmp.getBottomCard();
                              tmp.removeCard(card);
                              if (this.p.hand.size() == 10) {
                                    this.p.drawPile.moveToDiscardPile(card);
                                    this.p.createHandIsFullDialog();
                                  } else {
                                    card.unhover();
                                    card.lighten(true);
                                    card.setAngle(0.0F);
                                    card.drawScale = 0.12F;
                                    card.targetDrawScale = 0.75F;
                                    card.current_x = CardGroup.DRAW_PILE_X;
                                    card.current_y = CardGroup.DRAW_PILE_Y;
                                    this.p.drawPile.removeCard(card);
                                    AbstractDungeon.player.hand.addToTop(card);
                                    AbstractDungeon.player.hand.refreshHandLayout();
                                    AbstractDungeon.player.hand.applyPowers();
                                  }
                            }
                      }
           
                  this.isDone = true;
                }
            tickDuration();
   }
 }



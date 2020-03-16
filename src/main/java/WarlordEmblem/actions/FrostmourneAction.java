 package WarlordEmblem.actions;
 import WarlordEmblem.WarlordEmblem;
 import WarlordEmblem.relics.DK_Ghoul;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.CardLibrary;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.screens.CardRewardScreen;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
 import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
 import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

 import java.util.ArrayList;
 
 public class FrostmourneAction extends AbstractGameAction {
    private  int relicAmount;
     private DamageInfo info;
     private static final float DURATION = 0.1F;

   public FrostmourneAction(AbstractCreature target, DamageInfo info, int relicAmount) {
        this.info = info;
        setValues(target , info);
        this.relicAmount = relicAmount;
       this.actionType = ActionType.DAMAGE;
       this.duration = Settings.ACTION_DUR_XFAST;
   }

   
   public void update() {
        if(this.duration == Settings.ACTION_DUR_XFAST && this.target != null){
             AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.NONE));
                   this.target.damage(this.info);

                   if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead &&
                             !this.target.hasPower("Minion")) {
                       if(AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("DK_Ghoul"))){
                           DK_Ghoul gou = (DK_Ghoul) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("DK_Ghoul"));
                           if (gou != null)
                           gou.getNewGhoul();
                       }else {
                           AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2.0F), (Settings.HEIGHT / 2.0F), new DK_Ghoul());
                       }

                      }

                  if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                         AbstractDungeon.actionManager.clearPostCombatActions();
                      }
                 }
       tickDuration();

   }
 }



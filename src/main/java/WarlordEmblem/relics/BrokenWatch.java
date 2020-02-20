package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class BrokenWatch extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("BrokenWatch");

 
   
   public BrokenWatch() {
       super(ID,new Texture(WarlordEmblem.assetPath("img/relics/broken_watch.png")) , RelicTier.RARE, CustomRelic.LandingSound.FLAT);
       this.counter = 0;
   }


    public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }


       public void onUseCard(AbstractCard card, UseCardAction action) {

                   this.counter++;
            
                   if (this.counter % 12 == 0) {
                         flash();
                         this.counter = 0;
                       this.pulse = false;
                       AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                       AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, null, new StrengthPower(AbstractDungeon.player, 1)));
                       AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(new Slimed(), 1));
                       }else if (this.counter == 11) {
                       beginPulse();
                       this.pulse = true;
                   }

           }

    public void atBattleStart() {
        if (this.counter == 11) {
            beginPulse();
            this.pulse = true;
        }
    }

   public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }

   
   public CustomRelic makeCopy() { return new BrokenWatch(); }
 }
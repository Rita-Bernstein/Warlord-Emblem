package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BadgeMouse extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("BadgeMouse");

    private float TurnTime = 0;
    private int EnergyAdd = 0;


   public BadgeMouse() { super(ID,new Texture(WarlordEmblem.assetPath("img/relics/badge_mouse.png")) , RelicTier.BOSS, CustomRelic.LandingSound.FLAT); }
 
 
 
   
   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }



   public void atTurnStart() {

       if(this.EnergyAdd != 0){
           flash();
           AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
           AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.EnergyAdd));
       }
       this.EnergyAdd = 0;
        this.TurnTime = CardCrawlGame.playtime;
         }

    @Override
    public void onPlayerEndTurn() {

        this.EnergyAdd = 0;
        if(CardCrawlGame.playtime - this.TurnTime <= 8f){
            this.EnergyAdd = 1;
        }
        if(CardCrawlGame.playtime - this.TurnTime <= 4f){
            this.EnergyAdd = 2;
        }
    }

    public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }




   public CustomRelic makeCopy() { return new BadgeMouse(); }
 }
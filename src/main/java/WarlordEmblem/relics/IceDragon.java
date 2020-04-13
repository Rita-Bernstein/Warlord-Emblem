package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

public class IceDragon extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("IceDragon");

   
   public IceDragon() { super(ID,new Texture(WarlordEmblem.assetPath("img/relics/ice_dragon.png")) , RelicTier.BOSS, CustomRelic.LandingSound.FLAT); }
 
 
 
   
   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }

    @Override
    public void onEquip() {
        this.counter = 0;
        super.onEquip();
    }

       public void atTurnStart() {
             if (this.counter < 3) {
                   this.counter++;
                 }
             if (this.counter >= 3) {
                   flash();
                 addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(8, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_HEAVY));
                 for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {

                     addToBot(new ApplyPowerAction(mo,null,new WeakPower(mo,2,false),2));
                     if(AbstractDungeon.player.hasRelic("CultistMask")){
                         addToBot(new ApplyPowerAction(mo, null, new StrengthPower(mo, -2), -2));
                     }else {
                         addToBot(new ApplyPowerAction(mo, null, new StrengthPower(mo, -1), -1));
                     }

                 }
                 this.counter = 0;
                 }
           }

   public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }
 
 
 
   
   public CustomRelic makeCopy() { return new IceDragon(); }
 }
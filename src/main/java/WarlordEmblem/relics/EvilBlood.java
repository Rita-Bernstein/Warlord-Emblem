package WarlordEmblem.relics;

import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class EvilBlood extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("EvilBlood");



   public EvilBlood() { super(ID,new Texture(WarlordEmblem.assetPath("img/relics/evil_blood.png")) , RelicTier.RARE, CustomRelic.LandingSound.FLAT); }




   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }



   public void atBattleStart() {

       if(!(AbstractDungeon.player.hasRelic("Burning Blood") || AbstractDungeon.player.hasRelic("Black Blood"))){
           flash();
           AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player,AbstractDungeon.player,4));
       }

 }

       public void onVictory() {
             flash();
           AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
             AbstractPlayer p = AbstractDungeon.player;
           AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(p, this));
             if (p.currentHealth > 0) {
                   p.heal(9);
                 }

           this.counter = -1;
           }
    



    public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }




   public CustomRelic makeCopy() { return new EvilBlood(); }
 }
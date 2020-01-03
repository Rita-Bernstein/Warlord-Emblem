package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.Kael.ElementInvoke;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SoulOfKael extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("SoulOfKael");


   
   public SoulOfKael() { super(WarlordEmblem.makeID("SoulOfKael"),new Texture(WarlordEmblem.assetPath("img/relics/soul_of_kral.png")) , RelicTier.STARTER, CustomRelic.LandingSound.FLAT); }
 
 
 
   
   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }


    public void atTurnStart() {
        flash();
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new ElementInvoke(), false));
    }


   public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }




   public CustomRelic makeCopy() { return new SoulOfKael(); }
 }
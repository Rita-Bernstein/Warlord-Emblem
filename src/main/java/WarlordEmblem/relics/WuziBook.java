package WarlordEmblem.relics;
 
import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class WuziBook extends CustomRelic {
   public static final String ID = WarlordEmblem.makeID("WuziBook");

    private boolean HealingEndOfTurn = false;
    private boolean        firstTurn = false;


   public WuziBook() { super(ID,new Texture(WarlordEmblem.assetPath("img/relics/wuzi_book.png")) , RelicTier.COMMON, CustomRelic.LandingSound.FLAT); }
 
 
 
   
   public String getUpdatedDescription() { return this.DESCRIPTIONS[0] ; }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        if(AbstractDungeon.player.hasRelic("Art of War")){
            addToBot(new ApplyPowerAction(AbstractDungeon.player,null,new ArtifactPower(AbstractDungeon.player,1),1));
        }
    }

    public void atPreBattle() {
             flash();
             this.counter = 0;
             this.firstTurn = true;
             this.HealingEndOfTurn = true;
             if (!this.pulse) {
                   beginPulse();
                   this.pulse = true;
                 }
           }
    
    
       public void atTurnStart() {
             beginPulse();
             this.pulse = true;
             this.firstTurn = false;
             this.HealingEndOfTurn = true;
           }


    
       public void onUseCard(AbstractCard card, UseCardAction action) {
             if (card.type == AbstractCard.CardType.SKILL) {
                   this.HealingEndOfTurn = false;
                   this.pulse = false;
                 }
           }

    public void onEquip() { this.counter = 0; }

    @Override
    public void onPlayerEndTurn() {
        if(this.HealingEndOfTurn && this.counter < 8 && this.counter >= 0){
            flash();
            AbstractPlayer p = AbstractDungeon.player;
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(p, this));
            if (p.currentHealth > 0 ) {
                p.heal(2);
                this.counter +=2;
                if (this.counter >=8 ){this.counter = -1;}
            }
        }
    }

    public boolean canSpawn() { return (Settings.isEndless || AbstractDungeon.floorNum <= 52); }

    public void onVictory() { this.counter = -1; }
 
   
   public CustomRelic makeCopy() { return new WuziBook(); }
 }
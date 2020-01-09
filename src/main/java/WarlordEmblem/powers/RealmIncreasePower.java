package WarlordEmblem.powers;
 
 import WarlordEmblem.WarlordEmblem;
 import WarlordEmblem.cards.DeathKnight.AbstractDKCard;
 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.unlock.UnlockTracker;
 
 public class RealmIncreasePower extends AbstractPower {
   public static final String POWER_ID = WarlordEmblem.makeID("RealmIncreasePower");
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(WarlordEmblem.makeID("RealmIncreasePower"));
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public RealmIncreasePower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     if (this.amount >= 999) {
       this.amount = 999;
     }
     
     if (this.amount <= -999) {
       this.amount = -999;
     }
     updateDescription();
     //loadRegion("strength");
     this.canGoNegative = true;
       this.img = new Texture(WarlordEmblem.assetPath("img/powers/RealmIncreasePower.png"));
   }
 
 
   
   public void playApplyPowerSfx() { CardCrawlGame.sound.play("POWER_STRENGTH", 0.05F); }
 
 
   
   public void stackPower(int stackAmount) {
     this.fontScale = 8.0F;
     this.amount += stackAmount;
     if (this.amount == 0) {
       addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
     }

     if (this.amount >= 999) {
       this.amount = 999;
     }
     
     if (this.amount <= -999) {
       this.amount = -999;
     }
   }
 
   
   public void reducePower(int reduceAmount) {
     this.fontScale = 8.0F;
     this.amount -= reduceAmount;
     
     if (this.amount == 0) {
       addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NAME));
     }
     
     if (this.amount >= 999) {
       this.amount = 999;
     }
     
     if (this.amount <= -999) {
       this.amount = -999;
     }
   }
 
   
   public void updateDescription() {
     if (this.amount > 0) {
       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
       this.type = AbstractPower.PowerType.BUFF;
     } else {
       int tmp = -this.amount;
       this.description = DESCRIPTIONS[1] + tmp + DESCRIPTIONS[2];
       this.type = AbstractPower.PowerType.DEBUFF;
     } 
   }


/*
   @Override
   public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
     AbstractDKCard.RealmMagicNumber = AbstractDKCard.BaseRealmMagicNumber+this.amount;
     AbstractDKCard.isRealmMagicNumberModified = true;
     AbstractDKCard.SecondRealmMagicNumber = AbstractDKCard.BaseSecondRealmMagicNumber+this.amount;
     AbstractDKCard.isSecondRealmMagicNumberModified = true;
     super.onApplyPower(power, target, source);
   }

   @Override
   public void onRemove() {
     AbstractDKCard.RealmMagicNumber = AbstractDKCard.BaseRealmMagicNumber;
     AbstractDKCard.isRealmMagicNumberModified = false;
     AbstractDKCard.SecondRealmMagicNumber = AbstractDKCard.BaseSecondRealmMagicNumber;
     AbstractDKCard.isSecondRealmMagicNumberModified = false;
     super.onRemove();
   }*/
 }



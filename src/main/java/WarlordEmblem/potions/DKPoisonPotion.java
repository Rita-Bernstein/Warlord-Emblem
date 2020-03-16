  package WarlordEmblem.potions;
 
 import WarlordEmblem.WarlordEmblem;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.GameDictionary;
 import com.megacrit.cardcrawl.helpers.PowerTip;
 import com.megacrit.cardcrawl.helpers.TipHelper;
 import com.megacrit.cardcrawl.localization.PotionStrings;
 import com.megacrit.cardcrawl.potions.AbstractPotion;
 import com.megacrit.cardcrawl.powers.PoisonPower;
 
 public class DKPoisonPotion extends AbstractPotion {
   public static final String POTION_ID = WarlordEmblem.makeID("DKPoisonPotion");
   private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("Poison Potion");
 
 
 
   
   public DKPoisonPotion() {
     super(potionStrings.NAME, POTION_ID, AbstractPotion.PotionRarity.COMMON, AbstractPotion.PotionSize.M, AbstractPotion.PotionColor.POISON);
     this.labOutlineColor = WarlordEmblem.DeathKnight_Color;
     this.isThrown = true;
     this.targetRequired = true;
   }
 
   
   public void initializeData() {
     this.potency = getPotency();
     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
     this.tips.clear();
     this.tips.add(new PowerTip(this.name, this.description));
     this.tips.add(new PowerTip(
           
           TipHelper.capitalize(GameDictionary.POISON.NAMES[0]), (String)GameDictionary.keywords
           .get(GameDictionary.POISON.NAMES[0])));
   }
 
 
   
   public void use(AbstractCreature target) { addToBot(new ApplyPowerAction(target, AbstractDungeon.player, new PoisonPower(target, AbstractDungeon.player, this.potency), this.potency)); }
 
 
 
 
 
 
 
 
   
   public int getPotency(int ascensionLevel) { return 6; }
 
 
 
   
   public AbstractPotion makeCopy() { return new DKPoisonPotion(); }
 }



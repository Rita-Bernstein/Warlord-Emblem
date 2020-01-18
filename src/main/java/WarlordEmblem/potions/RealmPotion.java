 package WarlordEmblem.potions;
 
 import WarlordEmblem.WarlordEmblem;
 import WarlordEmblem.cards.DeathKnight.AbstractDKCard;
 import WarlordEmblem.powers.RealmIncreasePower;
 import WarlordEmblem.relics.RuneSword;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.GameDictionary;
 import com.megacrit.cardcrawl.helpers.PowerTip;
 import com.megacrit.cardcrawl.helpers.TipHelper;
 import com.megacrit.cardcrawl.localization.PotionStrings;
 import com.megacrit.cardcrawl.potions.AbstractPotion;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 
 public class RealmPotion extends AbstractPotion {
     public static final String POTION_ID = WarlordEmblem.makeID("RealmPotion");
     private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
     private static final String NAME = potionStrings.NAME;
     private static final PotionRarity RARITY = PotionRarity.RARE;
     private static final PotionSize SIZE = PotionSize.BOTTLE;
     private static final PotionColor PotionColor =  AbstractPotion.PotionColor.STRENGTH;

 
   
   public RealmPotion() {
     super(NAME, POTION_ID, RARITY,SIZE, PotionColor);
     this.isThrown = false;
     this.targetRequired = false;
     this.labOutlineColor = WarlordEmblem.DeathKnight_Color;
   }
 
   
   public void initializeData() {
     this.potency = getPotency();
     this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
     this.tips.clear();
     this.tips.add(new PowerTip(this.name, this.description));
     //this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.STRENGTH.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.STRENGTH.NAMES[0])));
   }
 
   
   public void use(AbstractCreature target) {
     AbstractPlayer abstractPlayer = AbstractDungeon.player;

       addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer, new RealmIncreasePower(abstractPlayer, this.potency), this.potency));
   }
 
 
   
   public int getPotency(int ascensionLevel) { return 2; }
 
 
 
   
   public AbstractPotion makeCopy() { return new RealmPotion(); }
 }



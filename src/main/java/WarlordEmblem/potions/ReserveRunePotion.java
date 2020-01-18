 package WarlordEmblem.potions;
 
 import WarlordEmblem.WarlordEmblem;
 import WarlordEmblem.cards.DeathKnight.AbstractDKCard;
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
 
 public class ReserveRunePotion extends AbstractPotion {
     public static final String POTION_ID = WarlordEmblem.makeID("ReserveRunePotion");
     private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
     private static final String NAME = potionStrings.NAME;
     private static final PotionRarity RARITY = PotionRarity.COMMON;
     private static final PotionSize SIZE = PotionSize.S;
     private static final PotionColor PotionColor =  AbstractPotion.PotionColor.STRENGTH;

 
   
   public ReserveRunePotion() {
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
     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
         if (!AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword")))
             return;
         RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
         if (rs != null)
             rs.plusRune(this.potency);
     }
   }
 
 
   
   public int getPotency(int ascensionLevel) { return 6; }
 
 
 
   
   public AbstractPotion makeCopy() { return new ReserveRunePotion(); }
 }



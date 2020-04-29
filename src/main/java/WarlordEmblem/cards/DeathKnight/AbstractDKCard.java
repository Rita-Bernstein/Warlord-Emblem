package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CustomTagsEnum;
import WarlordEmblem.relics.RuneSword;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.VictoryRoom;

public abstract class AbstractDKCard extends CustomCard {

    public static int RealmMagicNumber = 1 ;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public static int BaseRealmMagicNumber = 1 ;    // And our base stat - the number in it's base state. It will reset to that by default.
    public static boolean upgradedRealmMagicNumber; // A boolean to check whether the number has been upgraded or not.
    public static boolean isRealmMagicNumberModified; // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)

    public static int SecondRealmMagicNumber = 2 ;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public static int BaseSecondRealmMagicNumber = 2 ;    // And our base stat - the number in it's base state. It will reset to that by default.
    public static boolean upgradedSecondRealmMagicNumber; // A boolean to check whether the number has been upgraded or not.
    public static boolean isSecondRealmMagicNumberModified; // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)

    public static int SecondMagicNumber = 2 ;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public static int BaseSecondMagicNumber = 2 ;    // And our base stat - the number in it's base state. It will reset to that by default.
    public static boolean upgradedSecondMagicNumber; // A boolean to check whether the number has been upgraded or not.
    public static boolean isSecondMagicNumberModified; // A boolean to check whether the number has been modified or not, for coloring purposes. (red/green)



    public AbstractDKCard(String id, String name,  String img, int cost, String rawDescription,
            CardType type, CardColor color, CardRarity rarity, CardTarget target) {

        super(id, name,  img, cost, rawDescription, type, color, rarity, target);

    }

    public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
        super.displayUpgrades();
        if (upgradedRealmMagicNumber) { // If we set upgradedRealmMagicNumber = true in our card.
            RealmMagicNumber = BaseRealmMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            isRealmMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }
        if (upgradedSecondRealmMagicNumber) { // If we set upgradedRealmMagicNumber = true in our card.
            SecondRealmMagicNumber = BaseSecondRealmMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            isSecondRealmMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }
        if (upgradedSecondMagicNumber) { // If we set upgradedRealmMagicNumber = true in our card.
            SecondMagicNumber = BaseSecondRealmMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            isSecondMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }

    }

    public void upgradeRealmMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        BaseRealmMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        RealmMagicNumber = BaseRealmMagicNumber; // Set the number to be equal to the base value.
        upgradedRealmMagicNumber = true; // Upgraded = true - which does what the above method does.

    }
    public void upgradeSecondRealmMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.

        BaseSecondRealmMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        SecondRealmMagicNumber = BaseSecondRealmMagicNumber; // Set the number to be equal to the base value.
        upgradedSecondRealmMagicNumber = true; // Upgraded = true - which does what the above method does.
    }
    public void upgradeSecondMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.

        BaseSecondMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        SecondMagicNumber = BaseSecondMagicNumber; // Set the number to be equal to the base value.
        upgradedSecondMagicNumber = true; // Upgraded = true - which does what the above method does.
    }




    protected boolean hasBloodRealm() {
        if (AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("DKHelm")) &&(  getRuneCount() >= 6 || getRuneCount() == 0) ) {return true;}
        if (AbstractDungeon.player.hasPower(WarlordEmblem.makeID("RealmRiderPower"))) {return true;}
        return AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("BloodRealm"));
    }

    protected boolean hasIceRealm() {
        if (AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("DKHelm")) &&( getRuneCount() >= 6 || getRuneCount() == 0) ) {return true;}
        if (AbstractDungeon.player.hasPower(WarlordEmblem.makeID("RealmRiderPower"))) {return true;}
        return AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("IceRealm"));
    }

    protected boolean hasEvilRealm() {
        if (AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("DKHelm")) &&( getRuneCount() >= 6 || getRuneCount() == 0) ) {return true;}
        if (AbstractDungeon.player.hasPower(WarlordEmblem.makeID("RealmRiderPower"))) {return true;}
        return AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("EvilRealm"));
    }

    protected int getRuneCount() {
        if (!AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword"))) {
            return 0;
        }
        if(AbstractDungeon.player.hasPower(WarlordEmblem.makeID("RuneMultiplexPower"))){
            return getRuneMax();
        }
        RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
        return rs.counter;
    }

    protected int getGainRune() {
        if (!AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword"))) {
            return 0;
        }

        RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
        return rs.runeGain;
    }

    protected int getRuneMax(){
        if (!AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword"))) {
            return 0;
        }
        RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
        return rs.getMaxRune();
    }

    protected boolean isRuneFull() {
        if (!AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword"))) {
            return false;
        }
        RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
        return rs.isRuneFull();
    }

    protected void useRune(int amount) {
        if (!AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword")))
            return;
        RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
        if (rs != null)
            if(AbstractDungeon.player.hasPower(WarlordEmblem.makeID("RuneMultiplexPower"))){
                AbstractDungeon.player.getPower(WarlordEmblem.makeID("RuneMultiplexPower")).flash();
                AbstractDungeon.player.getPower(WarlordEmblem.makeID("RuneMultiplexPower")).amount--;
                if(AbstractDungeon.player.getPower(WarlordEmblem.makeID("RuneMultiplexPower")).amount == 0){addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, WarlordEmblem.makeID("RuneMultiplexPower")));}
                return;
            }

            rs.useRune(amount);
    }

    protected void plusRune(int amount) {
        if (!AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword")))
            return;
        RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
        if (rs != null)
            rs.plusRune(amount);
    }

    protected void plusMax(int amount) {
        if (!AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword")))
            return;
        RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
        if (rs != null)
            rs.plusMax(amount);
    }
    protected void decreaseMax(int amount) {
        if (!AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword")))
            return;
        RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
        if (rs != null)
            rs.decreaseMax(amount);
    }

    // protected void plusRuneRegen(int amount) {
    // if (!AbstractDungeon.player.hasRelic("RuneSword"))
    // return;
    // RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic("RuneSword");
    // if (rs != null)
    // rs.plusRuneRegen(amount);
    // }

    @Override
    public void update() {


        if(AbstractDungeon.player != null){
            if(AbstractDungeon.player.hasPower(WarlordEmblem.makeID("RealmIncreasePower") )){
                AbstractDKCard.RealmMagicNumber = AbstractDKCard.BaseRealmMagicNumber+AbstractDungeon.player.getPower(WarlordEmblem.makeID("RealmIncreasePower")).amount;
                AbstractDKCard.isRealmMagicNumberModified = true;
                AbstractDKCard.SecondRealmMagicNumber = AbstractDKCard.BaseSecondRealmMagicNumber+AbstractDungeon.player.getPower(WarlordEmblem.makeID("RealmIncreasePower")).amount;
                AbstractDKCard.isSecondRealmMagicNumberModified = true;
                initializeDescription();
            }else {
                AbstractDKCard.RealmMagicNumber = AbstractDKCard.BaseRealmMagicNumber;
                AbstractDKCard.isRealmMagicNumberModified = false;
                AbstractDKCard.SecondRealmMagicNumber = AbstractDKCard.BaseSecondRealmMagicNumber;
                AbstractDKCard.isSecondRealmMagicNumberModified = false;
            }
        }else{
            AbstractDKCard.RealmMagicNumber = 1;
            AbstractDKCard.BaseRealmMagicNumber = 1;
            AbstractDKCard.upgradedRealmMagicNumber = false;
            AbstractDKCard.isRealmMagicNumberModified = false;

            AbstractDKCard.SecondRealmMagicNumber = 2;
            AbstractDKCard.BaseSecondRealmMagicNumber = 2;
            AbstractDKCard.upgradedSecondRealmMagicNumber = false;
            AbstractDKCard.isSecondRealmMagicNumberModified = false;
        }
        super.update();
    }
}

package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.relics.RuneSword;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public abstract class AbstractDKCard extends CustomCard {


    public AbstractDKCard(String id, String name,  String img, int cost, String rawDescription,
            CardType type, CardColor color, CardRarity rarity, CardTarget target) {

        super(id, name,  img, cost, rawDescription, type, color, rarity, target);
    }

    protected boolean hasBloodRealm() {
        if (AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("DKHelm")) && getRuneCount() >= 6) {
            return true;
        }
        return AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("BloodRealm"));
    }

    protected boolean hasIceRealm() {
        if (AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("DKHelm")) && getRuneCount() >= 6) {
            return true;
        }
        return AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("IceRealm"));
    }

    protected boolean hasEvilRealm() {
        if (AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("DKHelm")) && getRuneCount() >= 6) {
            return true;
        }
        return AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("EvilRealm"));
    }

    protected int getRuneCount() {
        if (!AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword"))) {
            return 0;
        }
        RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
        return rs.counter;
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

    // protected void plusRuneRegen(int amount) {
    // if (!AbstractDungeon.player.hasRelic("RuneSword"))
    // return;
    // RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic("RuneSword");
    // if (rs != null)
    // rs.plusRuneRegen(amount);
    // }
}

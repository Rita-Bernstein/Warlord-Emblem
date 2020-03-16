package WarlordEmblem.relics;

import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class MagicRuneSword extends CustomRelic {

    public static final String ID = WarlordEmblem.makeID("MagicRuneSword");


    public MagicRuneSword() {
        super(ID,new Texture( WarlordEmblem.assetPath("img/relics/magic_rune_sword.png")), RelicTier.SHOP, AbstractRelic.LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        flash();
        this.grayscale = true;
        if (!AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword")))
            return;
        RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
        if (rs != null)
            rs.plusMax(4);
    }

    @Override
    public void onVictory() {
        this.grayscale = false;
    }

    public AbstractRelic makeCopy() {
        return new MagicRuneSword();
    }
}

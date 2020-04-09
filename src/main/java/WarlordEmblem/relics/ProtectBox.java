package WarlordEmblem.relics;

import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class ProtectBox extends CustomRelic {

    public static final String ID = WarlordEmblem.makeID("ProtectBox");


    public ProtectBox() {
        super(ID,new Texture( WarlordEmblem.assetPath("img/relics/protect_box.png")), RelicTier.UNCOMMON, AbstractRelic.LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        if(AbstractDungeon.player.hasRelic("Pandora's Box")){
            flash();
            addToBot(new DrawCardAction(AbstractDungeon.player,1));
        }
        super.atBattleStart();
    }



    @Override
    public void atTurnStartPostDraw() {
        flash();
        if (!AbstractDungeon.player.hasRelic(WarlordEmblem.makeID("RuneSword")))
            return;
        RuneSword rs = (RuneSword) AbstractDungeon.player.getRelic(WarlordEmblem.makeID("RuneSword"));
        if (rs != null)
            rs.plusRune(1);
        super.atTurnStartPostDraw();
    }


    public AbstractRelic makeCopy() {
        return new ProtectBox();
    }
}

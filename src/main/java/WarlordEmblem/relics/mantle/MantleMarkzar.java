package WarlordEmblem.relics.mantle;

import WarlordEmblem.WarlordEmblem;

import WarlordEmblem.cards.mantle.MantleCardMarkzar;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.Settings;


import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

//马克扎尔王子
public class MantleMarkzar extends CustomRelic {
    public static final String ID = WarlordEmblem.makeID("MantleMarkzar");


    private boolean firstTurn;

    public MantleMarkzar() {
        super(ID,  new Texture(WarlordEmblem.assetPath("img/relics/mantle_markzar.png")), AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public void atPreBattle() {
        this.firstTurn = true;
    }

    @Override
    public void atTurnStart() {
        if (this.firstTurn) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new MantleCardMarkzar(),
                    1, true, false,false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
            this.firstTurn = false;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MantleMarkzar();
    }
}

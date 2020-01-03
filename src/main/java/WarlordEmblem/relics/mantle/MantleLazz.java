package WarlordEmblem.relics.mantle;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.mantle.MantleCardCaireseth;
import WarlordEmblem.cards.mantle.MantleCardLazz;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.Settings;


import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

//拉兹的斗篷
public class MantleLazz extends CustomRelic {
    public static final String ID = WarlordEmblem.makeID("MantleLazz");


    private boolean firstTurn;
    public final static int STATE_INIT = 0;
    public final static int STATE_USED = 1;
    public int state = STATE_INIT;

    public MantleLazz() {
        super(ID,  new Texture(WarlordEmblem.assetPath("img/relics/mantle_lazz.png")), AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public void atPreBattle() {
        this.firstTurn = true;
        this.state = STATE_INIT;
        this.pulse = false;
    }

    public void useMantle() {
        flash();
        this.state = STATE_USED;
        this.beginPulse();
        this.pulse = true;
    }

    @Override
    public void atTurnStart() {
        if (this.firstTurn) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new MantleCardLazz(),
                    1, true, false,false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));

            this.firstTurn = false;
        } else if (state == STATE_USED) {
            flash();
            AbstractDungeon.actionManager.addToTop(new GainEnergyAction(2));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MantleLazz();
    }
}

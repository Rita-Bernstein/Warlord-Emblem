package WarlordEmblem.relics.mantle;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.mantle.MantleCardBaku;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;


import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

//噬月者巴库的斗篷
public class MantleBaku extends CustomRelic {
    public static final String ID = WarlordEmblem.makeID("MantleBaku");

    private boolean firstTurn;

    public MantleBaku() {
        super(ID, new Texture(WarlordEmblem.assetPath("img/relics/mantle_baku.png")), AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public void atPreBattle() {
        this.firstTurn = true;
    }

    @Override
    public void atTurnStart() {
        if (this.firstTurn) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new MantleCardBaku(),
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
        return new MantleBaku();
    }
}

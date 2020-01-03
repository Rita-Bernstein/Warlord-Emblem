package WarlordEmblem.relics.mantle;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.mantle.MantleCardCaireseth;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.Settings;


import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

//凯雷塞斯王子 二王子
public class MantleCaireseth extends CustomRelic {
    public static final String ID = WarlordEmblem.makeID("MantleCaireseth");


    private boolean firstTurn;

    public MantleCaireseth() {
        super(ID, new Texture(WarlordEmblem.assetPath("img/relics/mantle_caireseth.png")), AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public void atPreBattle() {
        this.firstTurn = true;
    }

    @Override
    public void atTurnStart() {
        if (this.firstTurn) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new  MantleCardCaireseth(),
                    1, true, false,false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
            this.firstTurn = false;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new MantleCaireseth();
    }

}

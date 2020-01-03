package WarlordEmblem.relics.mantle;

import WarlordEmblem.WarlordEmblem;

import WarlordEmblem.cards.mantle.MantleCardReynold;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.Settings;


import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/**
 * 雷诺的斗篷
 * 
 * @author 王凯迪
 *
 */
public class MantleReynold extends CustomRelic {
    public static final String ID = WarlordEmblem.makeID("MantleReynold");

    private boolean firstTurn;
    public int battleStartHP = 0;

    public MantleReynold() {
        super(ID,  new Texture(WarlordEmblem.assetPath("img/relics/mantle_reynold.png")), AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public void atPreBattle() {
        this.firstTurn = true;
        this.battleStartHP = AbstractDungeon.player.currentHealth;
    }

    @Override
    public void atTurnStart() {
        if (this.firstTurn) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new MantleCardReynold(),
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
        return new MantleReynold();
    }
}

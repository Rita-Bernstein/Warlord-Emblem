package WarlordEmblem.relics.mantle;

import WarlordEmblem.WarlordEmblem;

import WarlordEmblem.cards.mantle.MantleCardWalana;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.Settings;


import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class MantleWalana extends CustomRelic {
    public static final String ID = WarlordEmblem.makeID("MantleWalana");
    private static Texture texture = new Texture(WarlordEmblem.assetPath("img/relics/mantle_walana.png")) ;

    private boolean firstTurn;
    public final static int STATE_INIT = 0;
    public final static int STATE_USED = 1;
    public int state = STATE_INIT;

    public MantleWalana() {
        super(ID, texture, AbstractRelic.RelicTier.COMMON, AbstractRelic.LandingSound.FLAT);
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
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new MantleCardWalana(),
                    1, true, false,false, Settings.WIDTH * 0.2F, Settings.HEIGHT / 2.0F));
            this.firstTurn = false;
        }
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (state == STATE_USED) {
            AbstractDungeon.player.heal(damageAmount * 3 / 10);
        }
    }

    public void onVictory() {
        this.pulse = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MantleWalana();
    }
}

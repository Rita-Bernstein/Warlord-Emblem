package WarlordEmblem.relics;

import WarlordEmblem.WarlordEmblem;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class RuneSword extends CustomRelic {

    public static final String ID = WarlordEmblem.makeID("RuneSword");

    public final static int MAX = 100;
    private final int INITIAL_MAX = 6;
    private final int INITIAL_REGEN = 2;
    private int max = 0;
    private int regen = 0;

    public RuneSword() {
        super(ID,new Texture( WarlordEmblem.assetPath("img/relics/rune_sword.png")), AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        flash();
        max = INITIAL_MAX;
        regen = INITIAL_REGEN;
        counter = 0;
    }

    @Override
    public void onVictory() {
        counter = 0;
    }

    @Override
    public void atTurnStart() {
        plusRune(regen);
    }




    public boolean isRuneFull() {
        return counter == max;
    }




//符文管理==========================
    public void plusRune(int amount) {
        flash();
        // AbstractDungeon.actionManager.addToTop(new
        // RelicAboveCreatureAction(AbstractDungeon.player, this));
        counter += amount;
        if (counter > max)
            counter = max;
    }

    public void plusMax(int amount) {
        flash();
        max += amount;
    }

    public void useRune(int amount) {
        if (amount <= 0)
            return;
        if (amount > counter)
            amount = counter;
        flash();
        counter -= amount;
    }
    //符文管理==========================

    public AbstractRelic makeCopy() {
        return new RuneSword();
    }
}

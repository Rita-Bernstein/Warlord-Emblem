package WarlordEmblem.relics.quest;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;


public abstract class QuestBase extends CustomRelic {

    protected final static int STATE_NOT_STARTED = 0;
    protected final static int STATE_IN_PROGRESS = 1;
    protected final static int STATE_FINISHED = 2;
    protected final static int STATE_USED = 3;

    protected boolean firstTurn = false;
    protected int state = STATE_NOT_STARTED;

    public QuestBase(String id, Texture texture, RelicTier tier, LandingSound sound) {
        super(id, texture, tier, sound);
    }

    @Override
    public void atPreBattle() {
        this.counter = 0;
        this.firstTurn = true;
        this.state = STATE_NOT_STARTED;
        this.pulse = false;
    }

    public void startQuest() {
        flash();
        this.counter = 0;
        this.state = STATE_IN_PROGRESS;
        this.beginPulse();
        this.pulse = true;
    }

    public void useReward() {
        flash();
        this.state = STATE_USED;
        this.beginPulse();
        this.pulse = true;
    }

    public void onVictory() {
        this.pulse = false;
    }
}

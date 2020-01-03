package WarlordEmblem.relics.quest;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.quest.QuestCardWarlock;
import WarlordEmblem.cards.quest.QuestCardWarlockReward;
import com.badlogic.gdx.graphics.Texture;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class QuestWarlock extends QuestBase {
    public static final String ID = WarlordEmblem.makeID("QuestWarlock");

    private final static int COUNT = 6;

    public QuestWarlock() {
        super(ID,  new Texture(WarlordEmblem.assetPath("img/relics/quest_warlock.png")), AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new QuestWarlock();
    }

    @Override
    public void onManualDiscard() {
        if (state != STATE_IN_PROGRESS)
            return;
        this.counter++;
        String display = QuestCardWarlock.NAME + counter + "/" + COUNT;
        AbstractDungeon.actionManager.addToBottom(new TextAboveCreatureAction(AbstractDungeon.player, display));
        if (this.counter >= COUNT) {
            state = STATE_FINISHED;
            AbstractDungeon.actionManager
                    .addToBottom(new MakeTempCardInHandAction(new QuestCardWarlockReward(), false));
        }
    }

    @Override
    public void atTurnStart() {
        if (this.firstTurn) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new QuestCardWarlock(), false));
            this.firstTurn = false;
            return;
        }
        if (state == STATE_USED) {
            flash();
            AbstractDungeon.actionManager
                    .addToBottom(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(6, true),
                            DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            AbstractDungeon.actionManager
                    .addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 4));
        }
    }
}

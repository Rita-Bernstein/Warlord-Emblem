package WarlordEmblem.relics.quest;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.quest.QuestCardHunter;
import WarlordEmblem.cards.quest.QuestCardHunterReward;
import com.badlogic.gdx.graphics.Texture;


import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class QuestHunter extends QuestBase {
    public static final String ID = WarlordEmblem.makeID("QuestHunter");

    private final static int COUNT = 7;

    public QuestHunter() {
        super(ID,  new Texture(WarlordEmblem.assetPath("img/relics/quest_hunter.png")), AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new QuestHunter();
    }

    @Override
    public void atTurnStart() {
        if (this.firstTurn) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new QuestCardHunter(), false));
            this.firstTurn = false;
        }
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster monster) {
        if (state != STATE_IN_PROGRESS)
            return;
        if ((card.costForTurn == 0) || ((card.cost == -1) && (card.energyOnUse == 0))) {
            this.counter++;
            String display = QuestCardHunter.NAME + counter + "/" + COUNT;
            AbstractDungeon.actionManager.addToBottom(new TextAboveCreatureAction(AbstractDungeon.player, display));
            if (this.counter >= COUNT) {
                state = STATE_FINISHED;
                AbstractDungeon.actionManager
                        .addToBottom(new MakeTempCardInHandAction(new QuestCardHunterReward(), false));
            }
        }
    }
}

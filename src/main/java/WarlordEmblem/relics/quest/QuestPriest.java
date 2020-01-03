package WarlordEmblem.relics.quest;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.quest.QuestCardMask;
import WarlordEmblem.cards.quest.QuestCardPriest;
import WarlordEmblem.cards.quest.QuestCardPriestReward;
import com.badlogic.gdx.graphics.Texture;


import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class QuestPriest extends QuestBase {
    public static final String ID = WarlordEmblem.makeID("QuestPriest");

    private final static int COUNT = 7;
    public int battleStartHP = 0;

    public QuestPriest() {
        super(ID,  new Texture(WarlordEmblem.assetPath("img/relics/quest_priest.png")), AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new QuestPriest();
    }

    @Override
    public void atTurnStart() {
        if (this.firstTurn) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new QuestCardPriest(), false));
            this.firstTurn = false;
            battleStartHP = AbstractDungeon.player.currentHealth;
        }
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (state != STATE_IN_PROGRESS)
            return;
        if (card instanceof QuestCardMask)
            return;
        this.counter++;
        String display = QuestCardPriest.NAME + counter + "/" + COUNT;
        AbstractDungeon.actionManager.addToBottom(new TextAboveCreatureAction(AbstractDungeon.player, display));
        if (this.counter >= COUNT) {
            state = STATE_FINISHED;
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new QuestCardPriestReward(), false));
        }
    }
}

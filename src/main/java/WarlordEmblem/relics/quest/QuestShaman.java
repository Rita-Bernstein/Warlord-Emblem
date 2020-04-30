package WarlordEmblem.relics.quest;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.quest.QuestCardMask;
import WarlordEmblem.cards.quest.QuestCardShaman;
import WarlordEmblem.cards.quest.QuestCardShamanReward;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;


import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class QuestShaman extends QuestBase {
    public static final String ID = WarlordEmblem.makeID("QuestShaman");

    private final static int COUNT = 10;

    public QuestShaman() {
        super(ID,  new Texture(WarlordEmblem.assetPath("img/relics/quest_shaman.png")), AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new QuestShaman();
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new BorderLongFlashEffect(Color.BLUE), 0.0F, true));
        this.startQuest();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster monster) {
        if (state != STATE_IN_PROGRESS)
            return;
        if (card.type == CardType.SKILL && !(card instanceof QuestCardMask)) {
            this.counter++;
            String display = QuestCardShaman.NAME + counter + "/" + COUNT;
            AbstractDungeon.actionManager.addToBottom(new TextAboveCreatureAction(AbstractDungeon.player, display));
            if (this.counter >= COUNT) {
                state = STATE_FINISHED;
                AbstractDungeon.actionManager
                        .addToBottom(new MakeTempCardInHandAction(new QuestCardShamanReward(), false));
            }
        }
    }
}

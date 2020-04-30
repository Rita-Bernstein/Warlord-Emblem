package WarlordEmblem.relics.quest;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.quest.QuestCardMage;
import WarlordEmblem.cards.quest.QuestCardMageReward;
import WarlordEmblem.cards.quest.QuestCardMask;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;


import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class QuestMage extends QuestBase {
    public static final String ID = WarlordEmblem.makeID("QuestMage");

    private final static int COUNT = 6;

    public QuestMage() {
        super(ID,  new Texture(WarlordEmblem.assetPath("img/relics/quest_mage.png")), AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new QuestMage();
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
        if (card.color == CardColor.COLORLESS && !(card instanceof QuestCardMask) && !(card instanceof Shiv)) {
            this.counter++;
            String display = QuestCardMage.NAME + counter + "/" + COUNT;
            AbstractDungeon.actionManager.addToBottom(new TextAboveCreatureAction(AbstractDungeon.player, display));
            if (this.counter >= COUNT) {
                state = STATE_FINISHED;
                AbstractDungeon.actionManager
                        .addToBottom(new MakeTempCardInHandAction(new QuestCardMageReward(), false));
            }
        }
    }

}

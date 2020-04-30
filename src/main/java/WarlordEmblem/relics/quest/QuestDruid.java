package WarlordEmblem.relics.quest;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.quest.QuestCardDruid;
import WarlordEmblem.cards.quest.QuestCardDruidReward;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;


import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class QuestDruid extends QuestBase {
    public static final String ID = WarlordEmblem.makeID("QuestDruid");

    private final static int COUNT = 5;

    public QuestDruid() {
        super(ID,  new Texture(WarlordEmblem.assetPath("img/relics/quest_druid.png")), AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new QuestDruid();
    }


    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new BorderLongFlashEffect(Color.GREEN), 0.0F, true));
        this.startQuest();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster monster) {
        if (state != STATE_IN_PROGRESS)
            return;
        if ((card.costForTurn >= 2) || ((card.cost == -1) && (card.energyOnUse >= 2))) {
            this.counter++;
            String display = QuestCardDruid.NAME + counter + "/" + COUNT;
            AbstractDungeon.actionManager.addToBottom(new TextAboveCreatureAction(AbstractDungeon.player, display));
            if (this.counter >= COUNT) {
                state = STATE_FINISHED;
                AbstractDungeon.actionManager
                        .addToBottom(new MakeTempCardInHandAction(new QuestCardDruidReward(), false));
            }
        }
    }
}

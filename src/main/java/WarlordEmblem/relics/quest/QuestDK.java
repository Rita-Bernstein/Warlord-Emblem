package WarlordEmblem.relics.quest;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.quest.QuestCardDK;
import WarlordEmblem.cards.quest.QuestCardDKReward;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;


import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class QuestDK extends QuestBase {
    public static final String ID = WarlordEmblem.makeID("QuestDK");

    private final static int COUNT = 5;

    public QuestDK() {
        super(ID,  new Texture(WarlordEmblem.assetPath("img/relics/quest_dk.png")), AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new QuestDK();
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new BorderLongFlashEffect(Color.GREEN), 0.0F, true));
        this.startQuest();
    }

    public void onCardDraw(AbstractCard card) {
        if (state == STATE_IN_PROGRESS) {
            if (card.type == CardType.CURSE || card.type == CardType.STATUS) {
                this.counter++;
                String display = QuestCardDK.NAME + counter + "/" + COUNT;
                AbstractDungeon.actionManager.addToBottom(new TextAboveCreatureAction(AbstractDungeon.player, display));
                if (this.counter >= COUNT) {
                    state = STATE_FINISHED;
                    AbstractDungeon.actionManager
                            .addToBottom(new MakeTempCardInHandAction(new QuestCardDKReward(), false));
                }
            }
        } else if (state == STATE_USED) {
            if (card.type == CardType.CURSE || card.type == CardType.STATUS) {
                flash();
                AbstractPlayer p = AbstractDungeon.player;
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 8));
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
            }
        }
    }

}

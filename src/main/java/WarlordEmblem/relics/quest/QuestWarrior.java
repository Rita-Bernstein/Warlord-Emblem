package WarlordEmblem.relics.quest;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.quest.QuestCardWarrior;
import WarlordEmblem.cards.quest.QuestCardWarriorReward;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class QuestWarrior extends QuestBase {
    public static final String ID = WarlordEmblem.makeID("QuestWarrior");

    private final static int COUNT = 7;

    public QuestWarrior() {
        super(ID,  new Texture(WarlordEmblem.assetPath("img/relics/quest_warrior.png")), AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new QuestWarrior();
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new BorderLongFlashEffect(Color.RED), 0.0F, true));
        this.startQuest();
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        if (blockAmount != 0 && state == STATE_IN_PROGRESS) {
            this.counter++;
            String display = QuestCardWarrior.NAME + counter + "/" + COUNT;
            AbstractDungeon.actionManager.addToBottom(new TextAboveCreatureAction(AbstractDungeon.player, display));
            if (this.counter >= COUNT) {
                state = STATE_FINISHED;
                AbstractDungeon.actionManager
                        .addToBottom(new MakeTempCardInHandAction(new QuestCardWarriorReward(), false));
            }
        }
        return super.onPlayerGainedBlock(blockAmount);
    }

    @Override
    public void onPlayerEndTurn() {
        if (state == STATE_USED) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(
                    new DamageInfo(AbstractDungeon.player, 16, DamageInfo.DamageType.THORNS),
                    AbstractGameAction.AttackEffect.FIRE));
        }
    }
}

package WarlordEmblem.relics.quest;

import java.util.ArrayList;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.cards.quest.QuestCardPaladin;
import WarlordEmblem.cards.quest.QuestCardPaladinReward;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;


import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class QuestPaladin extends QuestBase {
    public static final String ID = WarlordEmblem.makeID("QuestPaladin");

    private final static int COUNT = 6;

    public QuestPaladin() {
        super(ID,  new Texture(WarlordEmblem.assetPath("img/relics/quest_paladin.png")), AbstractRelic.RelicTier.RARE, AbstractRelic.LandingSound.FLAT);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new QuestPaladin();
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new BorderLongFlashEffect(Color.GREEN), 0.0F, true));
        this.startQuest();
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (state != STATE_IN_PROGRESS)
            return;
        int currentBuffCount = countBuff();
        if (currentBuffCount > counter) {
            flash();
            this.counter = currentBuffCount;
            String display = QuestCardPaladin.NAME + counter + "/" + COUNT;
            AbstractDungeon.actionManager.addToBottom(new TextAboveCreatureAction(AbstractDungeon.player, display));
            if (this.counter >= COUNT) {
                state = STATE_FINISHED;
                AbstractDungeon.actionManager
                        .addToBottom(new MakeTempCardInHandAction(new QuestCardPaladinReward(), false));
            }
        }
    }

    private int countBuff() {
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<AbstractPower> powerList = p.powers;
        int count = 0;
        for (@SuppressWarnings("unused")
        AbstractPower power : powerList) {
            count++;
        }
        return count;
    }
}

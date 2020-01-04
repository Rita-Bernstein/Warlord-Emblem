package WarlordEmblem.cards.quest;

import java.util.ArrayList;

import WarlordEmblem.WarlordEmblem;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class QuestCardDruidReward extends AbstractQuestCard {
    public static final String ID = WarlordEmblem.makeID("QuestCardDruidReward");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/Quest/quest_card_druid_reward.png");
    private static final int COST = 0;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;



    public QuestCardDruidReward() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 8;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new BorderLongFlashEffect(Color.GREEN), 0.0F, true));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        CardGroup handGroup = p.hand;
        ArrayList<AbstractCard> cardList = handGroup.group;
        for (AbstractCard card : cardList) {
            if (card == this)
                continue;
            if (card.type != CardType.ATTACK)
                continue;
            if (card.cost == -1)
                continue;
            card.cost = 0;
            card.costForTurn = 0;
            card.isCostModified = true;
            card.superFlash(Color.GOLD.cpy());
        }

        CardGroup drawGroup = p.drawPile;
        cardList = drawGroup.group;
        for (AbstractCard card : cardList) {
            if (card.type != CardType.ATTACK)
                continue;
            if (card.cost == -1)
                continue;
            card.cost = 0;
            card.costForTurn = 0;
            card.isCostModified = true;
        }

        CardGroup discardGroup = p.discardPile;
        cardList = discardGroup.group;
        for (AbstractCard card : cardList) {
            if (card.type != CardType.ATTACK)
                continue;
            if (card.cost == -1)
                continue;
            card.cost = 0;
            card.costForTurn = 0;
            card.isCostModified = true;
        }
    }

    public AbstractCard makeCopy() {
        return new QuestCardDruidReward();
    }
}

package WarlordEmblem.cards.quest;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.relics.quest.QuestWarlock;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;


import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class QuestCardWarlockReward extends AbstractQuestCard {
    public static final String ID = WarlordEmblem.makeID("QuestCardWarlockReward");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/Quest/quest_card_warlock_reward.png");
    private static final int COST = 0;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;



    public QuestCardWarlockReward() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    public AbstractCard makeCopy() {
        return new QuestCardWarlockReward();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager
                .addToBottom(new VFXAction(p, new BorderLongFlashEffect(Color.PURPLE), 0.0F, true));
        if (p.hasRelic(WarlordEmblem.makeID("QuestWarlock"))) {
            QuestWarlock relic = (QuestWarlock) p.getRelic(WarlordEmblem.makeID("QuestWarlock"));
            relic.useReward();
        }
    }

}

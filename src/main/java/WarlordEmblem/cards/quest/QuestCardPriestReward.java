package WarlordEmblem.cards.quest;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.relics.quest.QuestPriest;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;


import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;

public class QuestCardPriestReward extends AbstractQuestCard {
    public static final String ID = WarlordEmblem.makeID("QuestCardPriestReward");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/Quest/quest_card_priest_reward.png");
    private static final int COST = 0;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;



    public QuestCardPriestReward() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 20;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager
                .addToBottom(new VFXAction(p, new BorderLongFlashEffect(Color.MAGENTA), 0.0F, true));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        if (p.hasRelic(WarlordEmblem.makeID("QuestPriest"))) {
            QuestPriest relic = (QuestPriest) p.getRelic(WarlordEmblem.makeID("QuestPriest"));
            int battleStartHP = relic.battleStartHP;
            int current = p.currentHealth;
            if (current >= battleStartHP)
                return;
            int toHeal = battleStartHP - current;
            if (toHeal > 20)
                toHeal = 20;
            p.heal(toHeal);
        }
    }

    public AbstractCard makeCopy() {
        return new QuestCardPriestReward();
    }

}

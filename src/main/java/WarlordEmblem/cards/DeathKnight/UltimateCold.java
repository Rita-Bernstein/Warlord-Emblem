package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class UltimateCold extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("UltimateCold");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/ultimate_cold.png");
    private static final int COST = 2;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL;



    public UltimateCold() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower("Strength")) {
                StrengthPower sp = (StrengthPower) mo.getPower("Strength");
                int amount = sp.amount;
                if (amount > 0) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p,
                            new StrengthPower(mo, -amount), -amount, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
        }

        if (!hasIceRealm()) {
            if (p.hasPower("Strength")) {
                StrengthPower sp = (StrengthPower) p.getPower("Strength");
                int amount = sp.amount;
                if (amount > 0) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -amount),
                            -amount, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
        }
    }

    public AbstractCard makeCopy() {
        return new UltimateCold();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }
}

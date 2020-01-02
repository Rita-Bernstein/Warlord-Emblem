package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

public class BloodBoiling extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("BloodBoiling");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("/img/cards/DeathKnight/blood_boiling.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int UPGRADE_BONUS = 1;

    public BloodBoiling() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 6;
        this.magicNumber = baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = super.getRuneCount();
        if (amount > this.magicNumber)
            amount = this.magicNumber;

        AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.RED)));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, 3));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, amount), amount));

        super.useRune(amount);
    }

    public AbstractCard makeCopy() {
        return new BloodBoiling();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BONUS);
        }
    }
}
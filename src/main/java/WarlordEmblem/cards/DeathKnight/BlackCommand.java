package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

public class BlackCommand extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("BlackCommand");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/black_command.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int UPGRADE_BONUS = 0;
    public BlackCommand() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!m.hasPower("Poison")) {
            return;
        }
        int amount = m.getPower("Poison").amount;
        // AbstractDungeon.actionManager.addToBottom(new
        // RemoveSpecificPowerAction(m, p, "Poison"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.25F));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.25F));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new InflameEffect(p), 0.25F));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, amount), amount));
    }

    public AbstractCard makeCopy() {
        return new BlackCommand();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_BONUS);
        }
    }

}

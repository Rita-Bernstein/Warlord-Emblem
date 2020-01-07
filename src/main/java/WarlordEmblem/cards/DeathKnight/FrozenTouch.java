package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

public class FrozenTouch extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("FrozenTouch");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/frozen_touch.png");
    private static final int COST = 2;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;



    public FrozenTouch() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 2;
        this.magicNumber = baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster am) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p,
                new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.BLUE_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC),
                1.5F));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!hasIceRealm())
                AbstractDungeon.actionManager
                        .addToBottom(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.magicNumber),
                                -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            else
                AbstractDungeon.actionManager
                        .addToBottom(new ApplyPowerAction(mo, p, new StrengthPower(mo, -this.magicNumber - AbstractDKCard.RealmMagicNumber),
                                -this.magicNumber - AbstractDKCard.RealmMagicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    public AbstractCard makeCopy() {
        return new FrozenTouch();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}

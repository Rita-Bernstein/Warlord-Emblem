package WarlordEmblem.cards.DeathKnight;

import WarlordEmblem.WarlordEmblem;
import WarlordEmblem.patches.CardColorEnum;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;

public class Hanging extends AbstractDKCard {
    public static final String ID = WarlordEmblem.makeID("Hanging");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/DeathKnight/hanging.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColorEnum.DeathKnight_LIME;
    private static final CardRarity RARITY = CardRarity.RARE    ;
    private static final CardTarget TARGET = CardTarget.ENEMY;


    public Hanging() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.RED)));
        int actual = this.damage;
        if (m.hasPower("Weakened"))
            actual *= 2;
        if (m.hasPower("Vulnerable"))
            actual *= 2;
        if (m.hasPower("Poison"))
            actual *= 2;
        if (m.hasPower("Strength")) {
            StrengthPower sp = (StrengthPower) (m.getPower("Strength"));
            if (sp.amount < 0) {
                actual *= 2;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new CollectorCurseEffect(m.hb.cX, m.hb.cY), 2.0F));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, actual, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    public AbstractCard makeCopy() {
        return new Hanging();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
        }
    }

}

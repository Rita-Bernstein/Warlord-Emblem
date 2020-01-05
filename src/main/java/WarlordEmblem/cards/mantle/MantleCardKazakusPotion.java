package WarlordEmblem.cards.mantle;

import java.util.ArrayList;

import WarlordEmblem.WarlordEmblem;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class MantleCardKazakusPotion extends AbstractMantleCard {
    public static final String ID = WarlordEmblem.makeID("MantleCardKazakusPotion");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = WarlordEmblem.assetPath("img/cards/Mantle/mantle_card_kazakus_potion.png");
    private static final int COST = 1;
    public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ALL;


    public int value1;
    public int value2;
    public int value3;

    public MantleCardKazakusPotion() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    public MantleCardKazakusPotion(int v1, int v2, int v3) {
        super(ID, NAME, IMG, COST,
                MantleCardKazakusPotion.EXTENDED_DESCRIPTION[v1] + MantleCardKazakusPotion.EXTENDED_DESCRIPTION[10]
                + MantleCardKazakusPotion.EXTENDED_DESCRIPTION[v2] + MantleCardKazakusPotion.EXTENDED_DESCRIPTION[10]
                + MantleCardKazakusPotion.EXTENDED_DESCRIPTION[v3] + MantleCardKazakusPotion.EXTENDED_DESCRIPTION[11]
                , TYPE, COLOR, RARITY, TARGET);
        this.value1 = v1;
        this.value2 = v2;
        this.value3 = v3;
        //this.DESCRIPTION = EXTENDED_DESCRIPTION[value1] + EXTENDED_DESCRIPTION[value2] +EXTENDED_DESCRIPTION[value3];
        this.baseDamage = 12;
        this.baseBlock = 20;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        usePart(p, m, value1);
        usePart(p, m, value2);
        usePart(p, m, value3);
    }

    public void usePart(AbstractPlayer p, AbstractMonster m, int value) {
        if (value == 0) {
            AbstractDungeon.actionManager
                    .addToBottom(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.damage, true),
                            DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        } else if (value == 1) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        } else if (value == 2) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new PoisonPower(mo, p, 6), 6));
            }
        } else if (value == 3) {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));
        } else if (value == 4) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 3), 3));
        } else if (value == 5) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, 3), 3));
        } else if (value == 6) {
            p.heal(10);
        } else if (value == 7) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 4));
        } else if (value == 8) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(mo, AbstractDungeon.player, new VulnerablePower(mo, 2, false), 2, true));
            }
        } else if (value == 9) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(mo, AbstractDungeon.player, new WeakPower(mo, 2, false), 2, true));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new MantleCardKazakusPotion(value1, value2, value3);
    }
}
